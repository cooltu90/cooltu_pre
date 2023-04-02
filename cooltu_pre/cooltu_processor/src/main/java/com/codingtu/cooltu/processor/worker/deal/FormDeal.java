package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.lib.model.BaseParentMap;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;

public class FormDeal extends BaseDeal {
    public static BaseParentMap map = new BaseParentMap();


    @Override
    public void deal(Element element) {
        Form form = element.getAnnotation(Form.class);
        String formClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return form.value();
            }
        });
        map.add(formClass, (TypeElement) element);
    }

}
