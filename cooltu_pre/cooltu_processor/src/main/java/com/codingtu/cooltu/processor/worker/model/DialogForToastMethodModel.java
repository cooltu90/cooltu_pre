package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;

import java.util.List;

import com.codingtu.cooltu.processor.modelinterface.DialogForToastMethodModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.SubBaseModel;

public class DialogForToastMethodModel extends SubBaseModel implements DialogForToastMethodModelInterface {

    private boolean isAct;

    public DialogForToastMethodModel(boolean isAct) {
        this.isAct = isAct;
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    public void setTagFor_className(StringBuilder sb) {
        addTag(sb, FullName.TOAST_DIALOG_VIEW);
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
        addTag(sb, Constant.DEFAULT_TOAST_DIALOG_LAYOUT);
    }

    @Override
    public void setTagFor_OnHiddenFinishedFullName(StringBuilder sb) {
        sb.append(FullName.ON_HIDDEN_FINISHED);
    }

    @Override
    public void setTagFor_HandlerToolFullName(StringBuilder sb) {
        sb.append(FullName.HANDLER_TOOL);
    }

}
/* model_temp_start
    private [[className]] toastDialog;

    protected [[className]] getToastDialog() {
        if (toastDialog == null)
            toastDialog = new [[className]]([[act]])
                    .setLayout([[layout]])
                    .build();
        return toastDialog;
    }

    protected void toastShow(String msg) {
        [[className]] td = getToastDialog();
        td.setContent(msg);
        td.show();
    }

    protected void toastShow(long time, String msg, [[OnHiddenFinishedFullName]] onHiddenFinished) {
        toastShow(msg);
        [[HandlerToolFullName]].getMainHandler().postDelayed(new java.lang.Runnable() {
            @Override
            public void run() {
                getToastDialog().hidden(onHiddenFinished);
            }
        }, time);
    }

    protected void toastShow(long time, String msg) {
        toastShow(time, msg, null);
    }

    protected void toastHidden(long time, String msg, [[OnHiddenFinishedFullName]] onHiddenFinished) {
        getToastDialog().setContent(msg);
        [[HandlerToolFullName]].getMainHandler().postDelayed(new java.lang.Runnable() {
            @Override
            public void run() {
                getToastDialog().hidden(onHiddenFinished);
            }
        }, time);
    }

    protected void toastHidden(long time, String msg) {
        toastHidden(time, msg, null);
    }
model_temp_end */
