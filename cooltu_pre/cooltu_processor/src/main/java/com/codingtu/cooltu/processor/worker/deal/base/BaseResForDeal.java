package com.codingtu.cooltu.processor.worker.deal.base;

import com.codingtu.cooltu.lib4j.data.bean.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.ui.dialog.DialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.lib.bean.DialogInfo;
import com.codingtu.cooltu.processor.lib.bean.EditDialogInfo;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.Date;

import javax.lang.model.element.VariableElement;

public abstract class BaseResForDeal extends BaseDeal {

    protected EditDialogInfo toEditDialogInfo(VariableElement ve, EditDialogUse use) {
        EditDialogInfo info = new EditDialogInfo();
        KV<String, String> kv = ElementTools.getFiledKv(ve);
        info.name = kv.v;
        info.title = use.title();
        info.hint = use.hint();
        info.inputType = use.inputType();
        info.stopAnimation = use.stopAnimation();
        info.isUseTextwatcher = use.isUseTextWatcher();
        info.objType = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return use.objType();
            }
        });
        return info;
    }

    protected DialogInfo toDialogInfo(VariableElement ve, DialogUse use) {
        KV<String, String> kv = ElementTools.getFiledKv(ve);
        DialogInfo dialogInfo = new DialogInfo();
        dialogInfo.name = kv.v;
        dialogInfo.title = use.title();
        dialogInfo.content = use.content();
        dialogInfo.leftBtText = use.leftBtText();
        dialogInfo.rightBtText = use.rightBtText();
        dialogInfo.objType = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return use.objType();
            }
        });
        return dialogInfo;
    }

}
