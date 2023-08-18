package cooltu.lib4j.tools;

import java.io.File;

public class ConvertTool {
    /**************************************************
     *
     * 字母大小写的操作
     *
     **************************************************/
    public static char toUpper(char c) {
        return (char) (c - 32);
    }

    public static char toLower(char c) {
        return (char) (c + 32);
    }

    /**************************************************
     *
     * 转换成小写加下划线类型
     * 比如 myName 转换后为 my_name
     *
     **************************************************/
    public static String toLayoutType(String str) {
        return toStaticType(str).toLowerCase();
    }

    /**************************************************
     *
     * 转换成静态变量的命名方式
     * 比如 myName 转换后为 MY_NAME
     *
     **************************************************/
    public static String toStaticType(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (CharTool.isLower(c)) {
                sb.append(toUpper(c));
            } else if (CharTool.isUpper(c) && i != 0) {
                sb.append("_" + c);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**************************************************
     *
     * 转换成类的命名方式
     *
     **************************************************/
    public static String toClassType(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && CharTool.isLower(c)) {
                c = toUpper(c);
            } else if (CharTool.isLowerLine(c)) {
                if (i == str.length() - 1) {
                    break;
                } else {
                    i++;
                    c = str.charAt(i);
                    if (CharTool.isLower(c)) {
                        c = toUpper(c);
                    }
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**************************************************
     *
     * 转换成函数的命名方式
     *
     **************************************************/
    public static String toMethodType(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && CharTool.isUpper(c)) {
                c = toLower(c);
            } else if (CharTool.isLowerLine(c)) {
                if (i == str.length() - 1) {
                    break;
                } else {
                    i++;
                    c = str.charAt(i);
                    if (CharTool.isLower(c)) {
                        c = toUpper(c);
                    }
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**************************************************
     *
     * 包名转路径，以\开始
     *
     **************************************************/
    public static String pkgToPath(String pkg) {
        if (pkg == null) return null;
        int length = pkg.length();
        if (length <= 0) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(File.separator);
        for (int i = 0; i < length; i++) {
            char c = pkg.charAt(i);
            if (c == '.') {
                sb.append(File.separator);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**************************************************
     *
     * 路径转包名
     *
     **************************************************/
    public static String pathToPkg(String path) {
        if (path == null) return null;
        int len = path.length();
        if (len <= 0) return null;
        char separatorChar = File.separatorChar;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = path.charAt(i);
            if (c == separatorChar || c == '/') {
                c = '.';
            }
            if (i == 0 || i == len - 1) {
                if (c == '.') {
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

}
