package com.codingtu.cooltu.constant;

import java.io.File;
import java.util.List;

public class Constant {
    /**********************************************
     * name名类型：以（com.a.b.c.TestClassActivity为例）
     * allName：
     *     说明：什么类型的名字都行
     *     例子：test_class_activity,TestClassActivity,TEST_CLASS_ACTIVITY,testClassActivity
     * allBaseName：
     *     说明：什么类型的基础名字都行,不包括后缀
     *     例子：test_class,TestClass,TEST_CLASS,testClass
     * typeName：
     *     说明：类型名
     *     例子：TestClassActivity
     * typeBaseName：
     *     说明：基础类型名
     *     列子：TestClass
     * staticName：
     *     说明：静态名字
     *     例子：TEST_CLASS_ACTIVITY
     * staticBaseName：
     *     说明：基础静态名字
     *     例子：TEST_CLASS
     * methodName：
     *     说明：方法名字
     *     例子：testClassActivity
     * methodBaseName：
     *     说明：方法基础名字
     *     例子：testClass
     * layoutName：
     *     说明：布局样式名字
     *     例子：test_class_activity
     * layoutBaseName：
     *     说明：布局样式基础名字
     *     例子：test_class
     * fullName：
     *     说明：全名，包括包名
     *     例子：com.a.b.c.TestClassActivity
     **********************************************/
    //文件分割
    public static final String SEPARATOR = File.separator;
    //各种类名
    public static final String NAME_ACT_START = "ActStart";
    public static final String NAME_ACT_BACK_INTENT = "ActBackIntent";
    public static final String NAME_PASS = "Pass";
    public static final String NAME_BUS_SEND = "BusSend";
    public static final String NAME_NET = "Net";
    public static final String NAME_MOCKS = "Mocks";
    public static final String NAME_PERMISSION = "Permissions";
    public static final String NAME_CODE_4_REQUEST = "Code4Request";
    //from名
    public static final String FROM_ACT = "fromAct";
    //布局前缀
    public static final String LAYOUT_PREFIX_ACTIVITY = "activity";
    public static final String LAYOUT_PREFIX_FRAGMENT = "fragment";
    public static final String LAYOUT_PREFIX_ITEM = "item";
    //Intent的key名
    public static final String INTENT_NAME_RESULT_NAME = "resultName";
    //rType
    public static final String R_TYPE_LAYOUT = "layout";
    //delete_acts
    public static List<String> DELETE_ACTS;
    public static List<String> RENAME_ACTS;
    //package
    public static String DEFAULT_DIALOG_LAYOUT = "com.codingtu.cooltu.lib4a.R.layout.default_dialog";
    public static String DEFAULT_TOAST_DIALOG_LAYOUT = "com.codingtu.cooltu.lib4a.R.layout.default_dialog_toast";
    public static String DEFAULT_NOTICE_DIALOG_LAYOUT = "com.codingtu.cooltu.lib4a.R.layout.default_dialog_notice";
    public static String DEFAULT_EDIT_DIALOG_LAYOUT = "com.codingtu.cooltu.lib4a.R.layout.default_dialog_edit";
    public static String DEFAULT_TEMP_LAYOUT;

    //文件名
    public static final String FILE_NAME_ANDROID_MANIFEST = "AndroidManifest.xml";
    //方法名前缀
    public static final String PREFIX_METHOD_SET_TAG_FOR = "setTagFor_";

    public static final String LAYOUT_NAME_DEFAULT_TEMP = "default_temp_layout";
}
