package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;

import javax.lang.model.element.Element;

import cooltu.lib4j.data.map.ListValueMap;

public class InBaseStartGroupDeal extends BaseDeal {

    public static ListValueMap<String, Element> map = new ListValueMap<>();

    @Override
    public void deal(Element element) {

        String parentType = ElementTools.getParentType(element);

        map.get(parentType).add(element);
        InBaseDeal.inBaseMap.get(parentType).add(ElementTools.simpleName(element));
    }
}
