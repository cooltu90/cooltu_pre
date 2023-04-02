package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;

import java.util.List;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.processor.lib.bean.EditDialogInfo;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.modelinterface.DialogForEditMethodModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.SubBaseModel;

public class DialogForEditMethodModel extends SubBaseModel implements DialogForEditMethodModelInterface {

    private final JavaInfo objInfo;
    private boolean isAct;
    private EditDialogInfo info;

    public DialogForEditMethodModel(EditDialogInfo info, boolean isAct) {
        this.info = info;
        this.isAct = isAct;
        objInfo = NameTools.getJavaInfoByName(info.objType);
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    public void setTagFor_className(StringBuilder sb) {
        addTag(sb, FullName.EDIT_DIALOG_VIEW);
    }

    @Override
    public void setTagFor_name(StringBuilder sb) {
        addTag(sb, info.name);
    }

    @Override
    public void setTagFor_nameClassType(StringBuilder sb) {
        addTag(sb, ConvertTool.toClassType(info.name));
    }

    @Override
    public void setTagFor_classType(StringBuilder sb) {
        if (ClassTool.isVoid(info.objType)) {
            addTag(sb, "");
        } else {
            sb.append(", ").append(info.objType);
        }
    }

    @Override
    public void setTagFor_classParam(StringBuilder sb) {
        if (ClassTool.isVoid(info.objType)) {
            addTag(sb, "");
        } else {
            addTag(sb, ConvertTool.toMethodType(objInfo.name));
        }
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
    public void setTagFor_title(StringBuilder sb) {
        addTag(sb, info.title);
    }

    @Override
    public void setTagFor_hint(StringBuilder sb) {
        addTag(sb, info.hint);
    }

    @Override
    public void setTagFor_inputType(StringBuilder sb) {
        sb.append(info.inputType);
    }

    @Override
    public void setTagFor_layout(StringBuilder sb) {
        addTag(sb, Constant.DEFAULT_EDIT_DIALOG_LAYOUT);
    }

    @Override
    public void setTagFor_reserve(StringBuilder sb) {
        sb.append(info.reserve);
    }

    @Override
    public void setTagFor_stopAnimation(StringBuilder sb) {
        if (info.stopAnimation) {
            sb.append("\n                    .stopAnimation()");
        }
    }

    @Override
    public void setTagFor_yesParam(StringBuilder sb) {
        if (ClassTool.isVoid(info.objType)) {
            addTag(sb, "");
        } else if (ClassTool.isObject(info.objType)) {
            addTag(sb, ", obj");
        } else {
            addTag(sb, ", obj != null ? ([classtype]) obj : null", info.objType);
        }
    }

    @Override
    public void setTagFor_obj(StringBuilder sb) {
        if (ClassTool.isVoid(info.objType)) {
            addTag(sb, "null");
        } else {
            addTag(sb, ConvertTool.toMethodType(objInfo.name));
        }
    }
}
/* model_temp_start
    private [[className]] [[name]];

    protected void show[[nameClassType]](String text[[classType]] [[classParam]]) {
        if ([[name]] == null)
            [[name]] = new [[className]]([[act]])
                    .setTitle("[[title]]")
                    .setHint("[[hint]]")
                    .setInputType([[inputType]])
                    .setLayout([[layout]])
                    .setReserveOriValue([[reserve]])[[stopAnimation]]
                    .setYes(new [[className]].Yes() {
                        @Override
                        public boolean yes(String text, Object obj) {
                            return [[name]]Yes(text[[yesParam]]);
                        }
                    })
                    .build();
        [[name]].setEditText(text);
        [[name]].setObject([[obj]]);
        [[name]].show();
    }

    protected boolean [[name]]Yes(String text[[classType]] [[classParam]]) {
        return false;
    }
model_temp_end */

