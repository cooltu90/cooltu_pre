package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import cooltu.lib4j.data.map.ListValueMap;

public class ResForBaseDeal extends BaseDeal {

    public static ListValueMap<String, String> baseMap = new ListValueMap<>();

    @Override
    public void deal(Element element) {
        if (!(element instanceof TypeElement)) {
            return;
        }

        TypeElement te = (TypeElement) element;

        String classFullName = ElementTools.getType(te);

        String parentClass = te.getSuperclass().toString();

        baseMap.get(classFullName).add(parentClass);
    }
}
