package com.codingtu.cooltu.processor.lib.tools;

import java.io.File;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Path;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

public class NameTools extends StringTool {

    /**********************************************
     *
     * 获取java类的类名信息
     *
     **********************************************/
    //通过文件路径和java路径获取
    public static JavaInfo getJavaInfo(String filePath, String javaPath) {
        JavaInfo info = new JavaInfo();
        int lastIndexOf = filePath.lastIndexOf(File.separator) + 1;
        info.name = filePath.substring(lastIndexOf, filePath.length() - ".java".length());
        String subPath = getSub(filePath, null, javaPath, info.name);
        info.pkg = ConvertTool.pathToPkg(subPath);
        if (isNotBlank(info.pkg)) {
            info.fullName = info.pkg + "." + info.name;
        } else {
            info.fullName = info.name;
        }
        return info;
    }

    //通过包名和类名获取
    public static JavaInfo getJavaInfoByName(String packages, String typeName) {
        JavaInfo info = new JavaInfo();
        info.name = typeName;
        info.fullName = packages + "." + info.name;
        info.pkg = packages;
        String javaPath = getJavaDir();
        String pkgPath = ConvertTool.pkgToPath(packages);
        info.path = javaPath + pkgPath + Constant.SEPARATOR + info.name + FileType.d_JAVA;
        return info;
    }

    //根据全类名获取
    public static JavaInfo getJavaInfoByName(String fullName) {
        int lastIndexOf = fullName.lastIndexOf(".");
        return getJavaInfoByName(fullName.substring(0, lastIndexOf), fullName.substring(lastIndexOf + 1));
    }

    //根据包名+基础名+后缀获取,基础名（test，user_name,UserName等类型，除去后缀的名字）
    public static JavaInfo getJavaInfoByName(String packages, String allBaseName, String suffix) {
        return getJavaInfoByName(packages, ConvertTool.toClassType(allBaseName) + suffix);
    }

    public static String getJavaSimpleName(String fullName) {
        int lastIndexOf = fullName.lastIndexOf(".");
        return fullName.substring(lastIndexOf + 1);
    }

    /**********************************************
     *
     * 是否为activity
     *
     **********************************************/
    public static boolean isActivity(String typeName) {
        return typeName.endsWith(Suffix.ACTIVITY);
    }

    public static boolean isActivity(ExecutableElement element) {
        return isActivity(ElementTools.simpleName(element.getEnclosingElement()));
    }

    public static boolean isActivity(TypeElement element) {
        return isActivity(ElementTools.simpleName(element));
    }

    public static boolean isActivity(Element element) {
        if (element instanceof TypeElement) {
            return isActivity((TypeElement) element);
        }
        if (element instanceof ExecutableElement) {
            return isActivity((ExecutableElement) element);
        }
        return false;
    }

    public static boolean isFragment(String typeName) {
        return typeName.endsWith(Suffix.FRAGMENT);
    }

    public static boolean isFragment(ExecutableElement element) {
        return isFragment(ElementTools.simpleName(element.getEnclosingElement()));
    }

    public static boolean isFragment(TypeElement element) {
        return isFragment(ElementTools.simpleName(element));
    }

    public static boolean isFragment(Element element) {
        if (element instanceof TypeElement) {
            return isFragment((TypeElement) element);
        }
        if (element instanceof ExecutableElement) {
            return isFragment((ExecutableElement) element);
        }
        return false;
    }

    /**************************************************
     *
     * 获取Model的类基础名
     *
     **************************************************/
    public static String getModelTypeBaseName(Class modelClass) {
        return cutSuffix(modelClass.getSimpleName(), Suffix.MODEL);
    }

    public static JavaInfo adapterToVH(JavaInfo adapterInfo) {
        return getVHInfo(getAdapterTypeBaseName(adapterInfo.name));
    }

    //获取 VH 的 JavaInfo
    public static JavaInfo getVHInfo(String allBaseName) {
        return getJavaInfoByName(Pkg.VH, allBaseName, Suffix.VH);
    }


    public static String getPathPath() {
        String s = ConvertTool.pkgToPath(Pkg.PATH);
        return getJavaDir() + s;
    }

    public static String getModelPath(BaseModel baseModel) {
        String canonicalName = baseModel.getClass().getCanonicalName();
        String s = ConvertTool.pkgToPath(canonicalName);
        return FileTool.getProjectDir()
                + Constant.SEPARATOR
                + Module.CORE_PROCESSOR
                + Path.SRC_MAIN_JAVA
                + s
                + FileType.d_JAVA;
    }

    public static String getModelPath() {
        String s = ConvertTool.pkgToPath(Pkg.MODEL);
        return FileTool.getProjectDir()
                + Constant.SEPARATOR
                + Module.CORE_PROCESSOR
                + Path.SRC_MAIN_JAVA
                + s;
    }

    public static String getModelInterPath(String typeName) {
        String s = ConvertTool.pkgToPath(Pkg.MODEL_INTERFACE);
        return FileTool.getProjectDir()
                + Constant.SEPARATOR
                + Module.CORE_PROCESSOR_MODEL_INTERFACE
                + Path.SRC_MAIN_JAVA
                + s
                + Constant.SEPARATOR
                + typeName
                + FileType.d_JAVA;
    }

    /**************************************************
     *
     * 分割线，上面的是不变的
     *
     **************************************************/

    /**************************************************
     *
     * 获取具体的JavaInfo
     *
     **************************************************/
    //获取 Activity 的 JavaInfo
    public static JavaInfo getActInfo(String pkg, String allBaseName) {
        return getJavaInfoByName(pkg, allBaseName, Suffix.ACTIVITY);
    }

    //获取 Fragment 的 JavaInfo
    public static JavaInfo getFragmentInfo(String pkg, String allBaseName) {
        return getJavaInfoByName(pkg, allBaseName, Suffix.FRAGMENT);
    }

    //获取 ActBase 的 JavaInfo
    public static JavaInfo getActBaseInfo(String allBaseName) {
        return getJavaInfoByName(Pkg.ACT_BASE, allBaseName, Suffix.ACTIVITY_BASE);
    }

    //获取 ActRes 的 JavaInfo
    public static JavaInfo getActResInfo(String allBaseName) {
        return getJavaInfoByName(Pkg.ACT_RES, allBaseName, Suffix.ACTIVITY_RES);
    }

    //获取 ActRes 的 JavaInfo
    public static JavaInfo getFragmentResInfo(String allBaseName) {
        return getJavaInfoByName(Pkg.FRAGMENT_RES, allBaseName, Suffix.FRAGMENT_RES);
    }

    //获取 Path 的 JavaInfo
    public static JavaInfo getPathInfo(String allBaseName) {
        return getJavaInfoByName(Pkg.PATH, allBaseName, Suffix.PATH);
    }

    public static JavaInfo getActBaseInfoByActFullName(String fullName) {
        return getActBaseInfo(NameTools.getActivityTypeBaseName(fullName));
    }

    //获取 FragmentBase 的 JavaInfo
    public static JavaInfo getFragmentBaseInfo(String allBaseName) {
        return getJavaInfoByName(Pkg.FRAGMENT_BASE, allBaseName, Suffix.FRAGMENT_BASE);
    }

    public static JavaInfo getFragmentBaseInfoByFragmentFullName(String fullName) {
        return getFragmentBaseInfo(NameTools.getFragmentTypeBaseName(fullName));
    }

    //获取 ApiService 的 JavaInfo
    public static JavaInfo getApiServiceInfo(String allBaseName) {
        return getJavaInfoByName(Pkg.API_SERVICE, allBaseName, Suffix.API_SERVICE);
    }

    //获取 SendParams 的 JavaInfo
    public static JavaInfo getSendParamsInfo(String allBaseName) {
        return getJavaInfoByName(Pkg.SEND_PARAMS, allBaseName, Suffix.SEND_PARAMS);
    }

    //获取 NetBack 的 JavaInfo
    public static JavaInfo getNetBackInfo(String allBaseName) {
        return getJavaInfoByName(Pkg.NET_BACK, allBaseName, Suffix.NET_BACK);
    }

    //获取 Adapter 的 JavaInfo
    public static JavaInfo getAdapterInfo(String pkg, String allBaseName) {
        return getJavaInfoByName(pkg, allBaseName, Suffix.ADAPTER);
    }

    //获取 core.tools 包下类的 JavaInfo
    public static JavaInfo getCoreToolsInfo(String typeName) {
        return getJavaInfoByName(Pkg.CORE_TOOLS, typeName);
    }

    /**********************************************
     *
     * 获取各种dir
     *
     **********************************************/
    //获取当前模块中的java路径
    public static String getJavaDir() {
        return getJavaDir(Module.CURRENT);
    }

    //通过模块名获取java路径
    public static String getJavaDir(String module) {
        return FileTool.getProjectDir() + Constant.SEPARATOR + module + Path.SRC_MAIN_JAVA;
    }

    //获取当前模块中的布局文件路径
    public static String getLayoutDir() {
        return getLayoutDir(Module.CURRENT);
    }

    //通过模块名获取布局文件路径
    public static String getLayoutDir(String module) {
        return FileTool.getProjectDir() + Constant.SEPARATOR + module + Path.SRC_MAIN_RES_LAYOUT;
    }

    //获取当前模块中的清单文件路径
    public static String getManifestPath() {
        return getManifestPath(Module.CURRENT);
    }

    //通过模块名获取清单文件路径
    public static String getManifestPath(String module) {
        return FileTool.getProjectDir() + Constant.SEPARATOR + module + Path.SRC_MAIN + Constant.SEPARATOR + Constant.FILE_NAME_ANDROID_MANIFEST;
    }

    //获取当前模块中的布局文件路径
    public static String getLayoutPath(String layoutName) {
        return getLayoutPath(Module.CURRENT, layoutName);
    }

    //通过模块名获取布局文件路径
    public static String getLayoutPath(String module, String layoutName) {
        return NameTools.getLayoutDir(module) + Constant.SEPARATOR + layoutName + FileType.d_XML;
    }

    //获取当前模块中的activity布局文件
    public static String getActivityLayoutPath(String layoutBaseName) {
        return getActivityLayoutPath(Module.CURRENT, layoutBaseName);
    }

    //通过模块名获取activity布局文件
    public static String getActivityLayoutPath(String module, String layoutBaseName) {
        return getLayoutPath(module, Constant.LAYOUT_PREFIX_ACTIVITY + "_" + layoutBaseName);
    }

    //获取当前模块中的item布局文件
    public static String getItemLayoutPath(String layoutBaseName) {
        return getItemLayoutPath(Module.CURRENT, layoutBaseName);
    }

    //通过模块名获取item布局文件
    public static String getItemLayoutPath(String module, String layoutBaseName) {
        return getLayoutPath(module, Constant.LAYOUT_PREFIX_ITEM + "_" + layoutBaseName);
    }

    //获取当前模块中的fragment布局文件
    public static String getFragmentLayoutPath(String layoutBaseName) {
        return getFragmentLayoutPath(Module.CURRENT, layoutBaseName);
    }

    //通过模块名获取afragment布局文件
    public static String getFragmentLayoutPath(String module, String layoutBaseName) {
        return getLayoutPath(module, Constant.LAYOUT_PREFIX_FRAGMENT + "_" + layoutBaseName);
    }

    /**********************************************
     *
     * 获取Activity的基础的类名
     *
     **********************************************/
    public static String getActivityTypeBaseName(TypeElement element) {
        return cutSuffix(ElementTools.simpleName(element), Suffix.ACTIVITY);
    }

    public static String getActivityTypeBaseName(String fullName) {
        return cutSuffix(getJavaSimpleName(fullName), Suffix.ACTIVITY);
    }

    public static String getAdapterTypeBaseName(String typeName) {
        return cutSuffix(typeName, Suffix.ADAPTER);
    }

    /**********************************************
     *
     * 获取Fragment的基础的类名
     *
     **********************************************/
    public static String getFragmentTypeBaseName(TypeElement element) {
        return cutSuffix(ElementTools.simpleName(element), Suffix.FRAGMENT);
    }


    public static String getFragmentTypeBaseName(String fullName) {
        return cutSuffix(getJavaSimpleName(fullName), Suffix.FRAGMENT);
    }

    public static String getActivityLayoutName(String layoutBaseName) {
        return Constant.LAYOUT_PREFIX_ACTIVITY + "_" + layoutBaseName;
    }

    public static String getFragmentLayoutName(String layoutBaseName) {
        return Constant.LAYOUT_PREFIX_FRAGMENT + "_" + layoutBaseName;
    }

    public static String getItemLayoutName(String layoutBaseName) {
        return Constant.LAYOUT_PREFIX_ITEM + "_" + layoutBaseName;
    }

    public static JavaInfo getBusInfo(String name) {
        return getJavaInfoByName(Pkg.BUS, name, Suffix.BUS);
    }
}
