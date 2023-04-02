package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.BaseParentModel;

public class DialogUseForToastDeal extends BaseDeal {
    private BaseParentModel baseModel;

    @Override
    public void deal(Element element) {
        if (NameTools.isActivity((TypeElement) element)) {
            baseModel = ActBaseDeal.getActBaseModel((TypeElement) element);
        } else if (NameTools.isFragment((TypeElement) element)) {
            baseModel = FragmentBaseDeal.getFragmentBaseModel((TypeElement) element);
        }
        baseModel.addToastDialog();
    }
}
