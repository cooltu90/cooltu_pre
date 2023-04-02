package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;

public class ClickViewDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        String simpleName = ElementTools.simpleName(typeElement);
        if (NameTools.isActivity(simpleName)) {
            ActBaseDeal.getActBaseModel(typeElement).addClickView((ExecutableElement) element);
        } else if (NameTools.isFragment(simpleName)) {
            FragmentBaseDeal.getFragmentBaseModel(typeElement).addClickView((ExecutableElement) element);
        }
    }
}
