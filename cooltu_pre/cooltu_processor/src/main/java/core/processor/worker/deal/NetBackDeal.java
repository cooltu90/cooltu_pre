package core.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import core.processor.lib.tools.ElementTools;
import core.processor.lib.tools.NameTools;
import core.processor.worker.deal.base.BaseDeal;
import core.processor.worker.model.mock.MockModel;

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