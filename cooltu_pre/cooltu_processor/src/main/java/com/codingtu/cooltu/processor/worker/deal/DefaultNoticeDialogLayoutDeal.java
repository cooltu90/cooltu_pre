package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.processor.annotation.ui.DefaultNoticeDialogLayout;
import com.codingtu.cooltu.processor.annotation.ui.DefaultToastDialogLayout;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;

import javax.lang.model.element.Element;

public class DefaultNoticeDialogLayoutDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DefaultNoticeDialogLayout layout = element.getAnnotation(DefaultNoticeDialogLayout.class);
        Constant.DEFAULT_NOTICE_DIALOG_LAYOUT = IdTools.elementToId(element, DefaultNoticeDialogLayout.class, layout.value()).toString();
    }
}
