package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.data.bean.KV;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.BusModel;
import com.codingtu.cooltu.processor.worker.model.BusSendModel;

public class BusConfigDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        VariableElement ve = (VariableElement) element;
        KV<String, String> kv = ElementTools.getFiledKv(ve);
        //创建bus
        JavaInfo busInfo = NameTools.getBusInfo(kv.v);
        new BusModel(busInfo).add(kv);
        //创建bussend
        BusSendModel.model.add(kv);

    }
}
