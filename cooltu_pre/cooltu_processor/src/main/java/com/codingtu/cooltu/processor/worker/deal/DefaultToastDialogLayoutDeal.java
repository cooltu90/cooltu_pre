package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.processor.annotation.ui.DefaultToastDialogLayout;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;

public class DefaultToastDialogLayoutDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DefaultToastDialogLayout layout = element.getAnnotation(DefaultToastDialogLayout.class);
        Constant.DEFAULT_TOAST_DIALOG_LAYOUT = IdTools.elementToId(element, DefaultToastDialogLayout.class, layout.value()).toString();
    }
}
