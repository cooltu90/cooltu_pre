package com.codingtu.cooltu.processor.lib.tools;

import cooltu.lib4j.tools.ClassTool;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.lib.bean.FormItemInfo;

public class FormTool {

    public static String toView(FormItemInfo info) {
        boolean hasParse = !ClassTool.isVoid(info.parse);
        if (hasParse) {
            return TagTools.getLine("new [YesNoMethods]().toView([calibration.result])", info.parse, info.beanField);
        } else {
            return info.beanField;
        }
    }

    public static String toBean(FormItemInfo info) {
        boolean hasParse = !ClassTool.isVoid(info.parse);
        if (hasParse) {
            return TagTools.getLine("new [YesNoMethods]().toBean(msg.obj)", info.parse);
        } else {
            return TagTools.getLine("([String]) msg.obj", info.fieldType);
        }
    }

    public static String check(FormItemInfo info) {
        boolean hasCheck = !ClassTool.isVoid(info.check);
        if (hasCheck) {
            return TagTools.getLine("        if (!new [check]().check([field])) {"
                    , info.check, info.beanField);
        } else {
            return TagTools.getLine("        if ([StringTool].isBlank([calibration.apparatusCode])) {"
                    , FullName.STRING_TOOL, info.beanField);
        }
    }

}
