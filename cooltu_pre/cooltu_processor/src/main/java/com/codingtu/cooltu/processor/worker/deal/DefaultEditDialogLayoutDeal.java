package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.processor.annotation.ui.DefaultEditDialogLayout;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;

public class DefaultEditDialogLayoutDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DefaultEditDialogLayout layout = element.getAnnotation(DefaultEditDialogLayout.class);
        Constant.DEFAULT_EDIT_DIALOG_LAYOUT = IdTools.elementToId(element, DefaultEditDialogLayout.class, layout.value()).toString();
    }
}
