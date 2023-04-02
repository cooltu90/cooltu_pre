package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;

import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.VHModel;

public class VHDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        String typeBaseName = NameTools.getAdapterTypeBaseName(ElementTools.simpleName(element));
        new VHModel(NameTools.getVHInfo(typeBaseName), typeBaseName, true);
    }
}
