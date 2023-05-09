package com.codingtu.cooltu.processor.lib.tools;

import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.lib.bean.FormItemInfo;
import com.codingtu.cooltu.processor.lib.bean.FromItemInfoForRg;

public class FormTool {

    public static String toView(FormItemInfo info) {
        boolean hasParse = !ClassTool.isVoid(info.parse);
        if (hasParse) {
            return TagTools.getLine("new [YesNoMethods]([params]).toView([calibration.result])",
                    info.parse, info.parseParams, info.beanField);
        } else {
            return info.beanField;
        }
    }

    public static String toBean(FormItemInfo info) {
        boolean hasParse = !ClassTool.isVoid(info.parse);
        if (hasParse) {
            return TagTools.getLine("new [YesNoMethods]([params]).toBean(msg.obj)", info.parse, info.parseParams);
        } else {
            return TagTools.getLine("([String]) msg.obj", info.fieldType);
        }
    }

    public static String check(String beanName, FormItemInfo info) {
        boolean hasCheck = !ClassTool.isVoid(info.check);
        if (hasCheck) {
            return TagTools.getLine("        if (!new [check]().check([xxx], [field])) {"
                    , info.check, beanName, info.beanField);
        } else {
            return TagTools.getLine("        if ([StringTool].isBlank([calibration.apparatusCode])) {"
                    , FullName.STRING_TOOL, info.beanField);
        }
    }

    public static String check1(String beanName, FormItemInfo info) {
        boolean hasCheck = !ClassTool.isVoid(info.check);
        if (hasCheck) {
            return TagTools.getLine("            if (new [check]().check([xxx], [field])) {"
                    , info.check, beanName, info.beanField);
        } else {
            return TagTools.getLine("            if ([StringTool].isNotBlank([calibration.apparatusCode])) {"
                    , FullName.STRING_TOOL, info.beanField);
        }
    }

}
