package core.processor.worker.deal;

import javax.lang.model.element.Element;

import com.codingtu.cooltu.constant.Constant;
import core.processor.annotation.ui.DefaultDialogLayout;
import core.processor.lib.tools.IdTools;
import core.processor.worker.deal.base.BaseDeal;

public class DefaultDialogLayoutDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DefaultDialogLayout layout = element.getAnnotation(DefaultDialogLayout.class);
        Constant.DEFAULT_DIALOG_LAYOUT = IdTools.elementToId(element, DefaultDialogLayout.class, layout.value()).toString();
    }
}
