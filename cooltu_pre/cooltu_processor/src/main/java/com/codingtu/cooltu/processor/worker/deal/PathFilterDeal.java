package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.lib.bean.PathFilterInfo;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

public class PathFilterDeal extends BaseDeal {

    public static Map<String, PathFilterInfo> map = new HashMap<>();


    @Override
    public void deal(Element element) {
        String type = ElementTools.getType(element);
        PathFilterInfo info = new PathFilterInfo();
        info.params = Ts.ts(element.getEnclosedElements()).convert((index, e) -> {
            if (e instanceof VariableElement) {
                return ElementTools.getFiledKv((VariableElement) e);
            }
            return null;
        }).get();
        map.put(type, info);
    }
}
