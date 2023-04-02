package com.codingtu.cooltu.lib4a.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import com.codingtu.cooltu.lib4a.CoreApp;
import com.codingtu.cooltu.lib4a.act.CoreActivity;

public class PermissionTool {
    public static void check(Activity act, int code, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!allow(permissions)) {
                act.requestPermissions(permissions, code);
            } else if (act instanceof PermissionBack) {
                act.onRequestPermissionsResult(code, permissions, getAllows(permissions));
                //((PermissionBack) act).back(code, permissions, getAllows(permissions));
            }
        } else if (act instanceof CoreActivity) {
            //act.onRequestPermissionsResult(code,permissions,getAllows(permissions));
            ((CoreActivity) act).onRequestPermissionsResultInCore(code, permissions, getAllows(permissions));
        }
    }

    public static boolean allow(int... grantResults) {
        for (int grantResult : grantResults) {
            if (!allow(grantResult)) {
                return false;
            }
        }
        return true;
    }

    public static boolean allow(int grantResult) {
        return grantResult == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean allow(String... permissions) {
        for (String permission : permissions) {
            if (!allow(permission)) {
                return false;
            }
        }
        return true;
    }

    public static boolean allow(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return CoreApp.APP.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private static int[] getAllows(String[] permissions) {
        int[] ints = new int[permissions.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = PackageManager.PERMISSION_GRANTED;
        }
        return ints;
    }
}
