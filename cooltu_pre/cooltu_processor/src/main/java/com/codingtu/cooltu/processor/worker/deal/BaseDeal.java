package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.processor.annotation.ui.Base;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import javax.lang.model.element.Element;

import cooltu.lib4j.data.map.ListValueMap;
import cooltu.lib4j.tools.ClassTool;

public class BaseDeal extends com.codingtu.cooltu.processor.worker.deal.base.BaseDeal {

    public static ListValueMap<String, String> map = new ListValueMap<>();

    @Override
    public void deal(Element element) {
        map.get(ElementTools.getType(element)).addAll(ClassTool.getAnnotationClasses(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return element.getAnnotation(Base.class).value();
            }
        }));
    }
}
