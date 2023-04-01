package core.processor.worker.deal;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import com.codingtu.cooltu.constant.FullName;
import core.processor.lib.ls.EachType;
import core.processor.lib.ls.TypeLss;
import core.processor.lib.tools.NameTools;
import core.processor.worker.deal.base.BaseDeal;
import core.processor.worker.model.ActBackIntentModel;
import core.processor.worker.model.BaseParentModel;
import core.processor.worker.model.PassModel;

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
