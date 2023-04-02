package com.codingtu.cooltu.processor.lib.tools;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ConvertTool;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.constant.Constant;

public class RenameTools {

    public static interface RenameLs {
        public String ls(String oldFullName, String newFullName);
    }

    public static String lsActs(String defaultStr, RenameLs ls) {
        for (int i = 0; i < CountTool.count(Constant.RENAME_ACTS); i += 2) {
            String str = ls.ls(Constant.RENAME_ACTS.get(i), Constant.RENAME_ACTS.get(i + 1));
            if (StringTool.isNotBlank(str)) {
                return str;
            }
        }
        return defaultStr;
    }

    public static String actFullNameToStaticName(String actFullName) {
        ////ONE_ACTIVITY
        return ConvertTool.toStaticType(NameTools.getJavaInfoByName(actFullName).name);
    }

    public static String actFullNameToMethodName(String actFullName) {
        ////ONE_ACTIVITY
        return ConvertTool.toMethodType(NameTools.getJavaInfoByName(actFullName).name);
    }

    public static JavaInfo actFullNameToActBaseInfo(String fullName) {
        return NameTools.getActBaseInfo(NameTools.getActivityTypeBaseName(fullName));
    }

    public static String actFullNameToLayout(String fullName) {
        String baseName = NameTools.getActivityTypeBaseName(fullName);
        return NameTools.getActivityLayoutName(ConvertTool.toStaticType(baseName).toLowerCase());
    }


}
