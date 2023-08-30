package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.processor.modelinterface.DialogForNoticeMethodModelInterface;
import com.codingtu.cooltu.processor.modelinterface.DialogForToastMethodModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.SubBaseModel;

import java.util.List;

public class DialogForNoticeMethodModel extends SubBaseModel implements DialogForNoticeMethodModelInterface {

    private boolean isAct;

    public DialogForNoticeMethodModel(boolean isAct) {
        this.isAct = isAct;
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    public void setTagFor_className(StringBuilder sb) {
        addTag(sb, FullName.NOTICE_DIALOG_VIEW);
    }

    @Override
    public void setTagFor_act(StringBuilder sb) {
        if (isAct) {
            addTag(sb, "getThis()");
        } else {
            addTag(sb, "getActivity()");
        }
    }

    @Override
    public void setTagFor_layout(StringBuilder sb) {
        addTag(sb, Constant.DEFAULT_NOTICE_DIALOG_LAYOUT);
    }

}
/* model_temp_start
    private [[className]] noticeDialog;

    protected void noticeShow(String msg) {
        if (noticeDialog == null)
            noticeDialog = new [[className]]([[act]])
                    .setLayout([[layout]])
                    .build();
        noticeDialog.setContent(msg);
        noticeDialog.show();
    }
model_temp_end */
