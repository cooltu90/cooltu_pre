package core.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.data.bean.KV;
import core.processor.lib.tools.ElementTools;
import core.processor.lib.tools.NameTools;
import core.processor.worker.deal.base.BaseDeal;
import core.processor.worker.model.BusModel;
import core.processor.worker.model.BusSendModel;

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
