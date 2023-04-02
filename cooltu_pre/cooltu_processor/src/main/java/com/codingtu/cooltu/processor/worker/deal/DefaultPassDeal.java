package com.codingtu.cooltu.processor.worker.deal;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import com.codingtu.cooltu.processor.annotation.ui.DefaultPass;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.PassModel;

public class DefaultPassDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DefaultPass defaultPass = element.getAnnotation(DefaultPass.class);
        boolean param = defaultPass.isParams();
        if (param) {
            dealParam(element);
        } else {
            String defaultPassClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return defaultPass.value();
                }
            });

            if (ClassTool.isVoid(defaultPassClass)) {
                if (CountTool.count(defaultPass.paramNames()) > 0) {
                    List<String> classes = ClassTool.getAnnotationClasses(new ClassTool.AnnotationClassGetter() {
                        @Override
                        public Object get() {
                            return defaultPass.paramClasses();
                        }
                    });
                    PassModel.model.add(classes, defaultPass.paramNames());
                }
            }
        }
    }

    private void dealParam(Element element) {
        Ts.ls(element.getEnclosedElements(), new Each<Element>() {
            @Override
            public boolean each(int position, Element element) {
                if (element instanceof VariableElement) {
                    PassModel.model.add(ElementTools.getFiledKv((VariableElement) element));
                }
                return false;
            }
        });
    }
}
