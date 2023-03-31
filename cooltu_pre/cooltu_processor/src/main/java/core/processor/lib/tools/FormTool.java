package core.processor.lib.tools;

import cooltu.lib4j.tools.ClassTool;
import core.constant.FullName;
import core.processor.lib.bean.FormItemInfo;

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
            return "(String) msg.obj";
        }
    }

    public static String check(FormItemInfo info) {
        boolean hasCheck = !ClassTool.isVoid(info.check);
        if (hasCheck) {
            return TagTools.getLine("        if (!new [YesNoMethods]().check([calibration.isEquipTimeRight])) {"
                    , info.check, info.beanField);
        } else {
            return TagTools.getLine("        if ([StringTool].isBlank([calibration.apparatusCode])) {"
                    , FullName.STRING_TOOL, info.beanField);
        }
    }

}
