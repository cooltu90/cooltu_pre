package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;

import javax.lang.model.element.Element;

import cooltu.lib4j.data.map.ListValueMap;

public class InBaseDeal extends BaseDeal {

    public static ListValueMap<String, String> inBaseMap = new ListValueMap<>();

    @Override
    public void deal(Element element) {
        inBaseMap.get( ElementTools.getParentType(element)).add(ElementTools.simpleName(element));
    }
}
