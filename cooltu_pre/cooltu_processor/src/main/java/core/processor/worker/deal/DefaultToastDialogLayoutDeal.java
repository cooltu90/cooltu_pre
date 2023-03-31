package core.processor.worker.deal;

import javax.lang.model.element.Element;

import core.constant.Constant;
import core.processor.annotation.ui.DefaultToastDialogLayout;
import core.processor.lib.tools.IdTools;
import core.processor.worker.deal.base.BaseDeal;

public class DefaultToastDialogLayoutDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DefaultToastDialogLayout layout = element.getAnnotation(DefaultToastDialogLayout.class);
        Constant.DEFAULT_TOAST_DIALOG_LAYOUT = IdTools.elementToId(element, DefaultToastDialogLayout.class, layout.value()).toString();
    }
}
