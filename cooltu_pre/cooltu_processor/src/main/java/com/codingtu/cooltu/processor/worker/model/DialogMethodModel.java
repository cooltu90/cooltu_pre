package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;

import java.util.List;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.ConvertTool;

import com.codingtu.cooltu.processor.lib.bean.DialogInfo;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.modelinterface.DialogMethodModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.SubBaseModel;

public class DialogMethodModel extends SubBaseModel implements DialogMethodModelInterface {
    private final JavaInfo objInfo;
    private final boolean isAct;
    private DialogInfo info;

    public DialogMethodModel(DialogInfo info, boolean isAct) {
        this.info = info;
        this.isAct = isAct;
        objInfo = NameTools.getJavaInfoByName(info.objType);
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    public void setTagFor_DialogView(StringBuilder sb) {
        addTag(sb, FullName.DIALOG_VIEW);
    }

    @Override
    public void setTagFor_name(StringBuilder sb) {
        addTag(sb, info.name);
    }

    @Override
    public void setTagFor_nameToClass(StringBuilder sb) {
        addTag(sb, ConvertTool.toClassType(info.name));
    }

    @Override
    public void setTagFor_classType(StringBuilder sb) {
        if (ClassTool.isVoid(info.objType)) {
            addTag(sb, "");
        } else {
            addTag(sb, info.objType);
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
    public void setTagFor_content(StringBuilder sb) {
        addTag(sb, info.content);
    }

    @Override
    public void setTagFor_layout(StringBuilder sb) {
        addTag(sb, Constant.DEFAULT_DIALOG_LAYOUT);
    }

    @Override
    public void setTagFor_yesParam(StringBuilder sb) {
        if (ClassTool.isVoid(info.objType)) {
            addTag(sb, "");
        } else if (ClassTool.isObject(info.objType)) {
            addTag(sb, "obj");
        } else {
            addTag(sb, "obj != null ? ([classtype]) obj : null", info.objType);
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

    @Override
    public void setTagFor_showOtherParamSign(StringBuilder sb) {
        if (!ClassTool.isVoid(info.objType)) {
            sb.append(" ,");
        }
    }
}
/* model_temp_start
    private [[DialogView]] [[name]];

    protected void show[[nameToClass]]([[classType]] [[classParam]]) {
        if ([[name]] == null) {
            [[name]] = new [[DialogView]]([[act]])
                    .setTitle("[[title]]")
                    .setContent("[[content]]")
                    .setLayout([[layout]])
                    .setYes(new [[DialogView]].Yes() {
                        @Override
                        public void yes(Object obj) {
                            [[name]]Yes([[yesParam]]);
                        }
                    })
                    .build();
        }
        [[name]].setObject([[obj]]);
        [[name]].show();
    }
    protected void show[[nameToClass]](String content[[showOtherParamSign]][[classType]] [[classParam]]) {
        if ([[name]] == null) {
            [[name]] = new [[DialogView]]([[act]])
                    .setTitle("[[title]]")
                    .setContent(content)
                    .setLayout([[layout]])
                    .setYes(new [[DialogView]].Yes() {
                        @Override
                        public void yes(Object obj) {
                            [[name]]Yes([[yesParam]]);
                        }
                    })
                    .build();
        }else{
            [[name]].updateContent(content);
        }
        [[name]].setObject([[obj]]);
        [[name]].show();
    }

    protected void [[name]]Yes([[classType]] [[classParam]]) {}
model_temp_end */
