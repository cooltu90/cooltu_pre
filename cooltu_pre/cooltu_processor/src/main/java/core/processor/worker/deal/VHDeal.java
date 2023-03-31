package core.processor.worker.deal;

import javax.lang.model.element.Element;

import core.processor.lib.tools.ElementTools;
import core.processor.lib.tools.NameTools;
import core.processor.worker.deal.base.BaseDeal;
import core.processor.worker.model.VHModel;

public class VHDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        String typeBaseName = NameTools.getAdapterTypeBaseName(ElementTools.simpleName(element));
        new VHModel(NameTools.getVHInfo(typeBaseName), typeBaseName, true);
    }
}
