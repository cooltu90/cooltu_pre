package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.mock.MockModel;

public class NetBackDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        String simpleName = ElementTools.simpleName(typeElement);
        if (NameTools.isActivity(simpleName)) {
            ActBaseDeal.getActBaseModel(typeElement).addNetBack((ExecutableElement) element);
        } else if (NameTools.isFragment(simpleName)) {
            FragmentBaseDeal.getFragmentBaseModel(typeElement).addNetBack((ExecutableElement) element);
        }
        MockModel.model.add((ExecutableElement) element);
    }
}
