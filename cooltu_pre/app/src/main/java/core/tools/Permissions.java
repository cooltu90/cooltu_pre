package core.tools;

import android.Manifest;
import android.app.Activity;

public class Permissions {

    public static final int CODE_CHECK = 0;


    public static void check(Activity act) {
        com.codingtu.cooltu.lib4a.permission.PermissionTool.check(act, CODE_CHECK , "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT");
    }


}
