package core.processor.worker.deal;

import javax.lang.model.element.Element;

import com.codingtu.cooltu.constant.Constant;
import core.processor.annotation.ui.DefaultEditDialogLayout;
import core.processor.lib.tools.IdTools;
import core.processor.worker.deal.base.BaseDeal;

public class DefaultEditDialogLayoutDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DefaultEditDialogLayout layout = element.getAnnotation(DefaultEditDialogLayout.class);
        Constant.DEFAULT_EDIT_DIALOG_LAYOUT = IdTools.elementToId(element, DefaultEditDialogLayout.class, layout.value()).toString();
    }
}
