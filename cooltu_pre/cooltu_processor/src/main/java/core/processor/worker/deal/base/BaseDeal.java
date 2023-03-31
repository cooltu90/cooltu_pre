package core.processor.worker.deal.base;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import core.constant.Constant;
import core.processor.lib.tools.ElementTools;
import core.processor.lib.tools.NameTools;
import core.processor.worker.deal.ActBaseDeal;
import core.processor.worker.deal.FragmentBaseDeal;
import core.processor.worker.model.BaseParentModel;

public abstract class BaseDeal {

    /**************************************************
     *
     * deal方法
     *
     **************************************************/
    public void dealElement(Element element) {
        if (element instanceof TypeElement) {
            String fullName = ElementTools.getType((TypeElement) element);
            if (Constant.DELETE_ACTS != null && Constant.DELETE_ACTS.contains(fullName)) {
                return;
            }
        }
        deal(element);
    }

    public abstract void deal(Element element);

    protected TypeElement getTypeElement(Element element) {
        if (element instanceof ExecutableElement) {
            return (TypeElement) element.getEnclosingElement();
        } else if (element instanceof TypeElement) {
            return (TypeElement) element;
        } else {
            return null;
        }
    }


    protected BaseParentModel getBaseParentModel(String classFullName) {
        BaseParentModel baseModel = null;
        if (NameTools.isActivity(classFullName)) {
            baseModel = ActBaseDeal.getActBaseModel(classFullName);
        } else if (NameTools.isFragment(classFullName)) {
            baseModel = FragmentBaseDeal.getFragmentBaseModel(classFullName);
        }
        return baseModel;
    }

}
