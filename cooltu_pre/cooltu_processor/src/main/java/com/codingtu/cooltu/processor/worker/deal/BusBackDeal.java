package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.BaseParentModel;

public class BusBackDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        //com.envcheck3.ui.WifiConnectActivity
        String parentType = ElementTools.getParentType(element);
        BaseParentModel baseParentModel = null;
        if (NameTools.isActivity(parentType)) {
            baseParentModel = ActBaseDeal.getActBaseModel(parentType);
        } else if (NameTools.isFragment(parentType)) {
            baseParentModel = FragmentBaseDeal.getFragmentBaseModel(parentType);
        }

        if(baseParentModel!=null){
            baseParentModel.addBusBack((ExecutableElement) element);
        }

    }
}
