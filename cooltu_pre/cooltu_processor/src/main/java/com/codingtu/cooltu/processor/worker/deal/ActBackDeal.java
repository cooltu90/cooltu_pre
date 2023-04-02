package com.codingtu.cooltu.processor.worker.deal;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.processor.lib.ls.EachType;
import com.codingtu.cooltu.processor.lib.ls.TypeLss;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.ActBackIntentModel;
import com.codingtu.cooltu.processor.worker.model.BaseParentModel;
import com.codingtu.cooltu.processor.worker.model.PassModel;

public class ActBackDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        TypeElement typeElement = getTypeElement(element);

        List<? extends VariableElement> parameters = ((ExecutableElement) element).getParameters();
        TypeLss.ls(parameters, new EachType() {
            @Override
            public void each(int position, String type, String name) {
                if (!FullName.INTENT.equals(type)) {
                    PassModel.model.add(type, name);
                }
            }
        });

        BaseParentModel baseParentModel = null;
        if (NameTools.isActivity(typeElement)) {
            baseParentModel = ActBaseDeal.getActBaseModel(typeElement);
        } else if (NameTools.isFragment(typeElement)) {
            baseParentModel = FragmentBaseDeal.getFragmentBaseModel(typeElement);
        }
        baseParentModel.addActBack((ExecutableElement) element);
        
        ActBackIntentModel.model.add((ExecutableElement) element);
    }
}
