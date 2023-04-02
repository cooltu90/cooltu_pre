package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.processor.annotation.ui.DefaultDialogLayout;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;

public class DefaultDialogLayoutDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DefaultDialogLayout layout = element.getAnnotation(DefaultDialogLayout.class);
        Constant.DEFAULT_DIALOG_LAYOUT = IdTools.elementToId(element, DefaultDialogLayout.class, layout.value()).toString();
    }
}
