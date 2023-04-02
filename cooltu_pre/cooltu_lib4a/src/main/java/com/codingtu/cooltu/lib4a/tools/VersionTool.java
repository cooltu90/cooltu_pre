package com.codingtu.cooltu.lib4a.tools;

import android.os.Build;

public class VersionTool {

    public static final int A11 = Build.VERSION_CODES.R;
    public static final int A12 = Build.VERSION_CODES.S;


    public static boolean isLess(int version) {
        return Build.VERSION.SDK_INT < version;
    }

    public static boolean isLessOrEqual(int version) {
        return Build.VERSION.SDK_INT <= version;
    }

    public static boolean isGreater(int version) {
        return Build.VERSION.SDK_INT > version;
    }

    public static boolean isGreaterOrEqual(int version) {
        return Build.VERSION.SDK_INT >= version;
    }

    public static boolean equal(int version) {
        return Build.VERSION.SDK_INT == version;
    }

}
