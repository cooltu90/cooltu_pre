package com.codingtu.cooltu.lib4a.tools;

import com.codingtu.cooltu.lib4a.CorePreferences;
import com.codingtu.cooltu.lib4a.connect.ConnectDeviceBaseData;

import com.codingtu.cooltu.lib4j.json.JsonTool;

public class PfTool {

    private static CorePreferences pf;

    public static CorePreferences pf() {
        if (pf == null)
            pf = new CorePreferences();
        return pf;
    }

    public static void cacheLastConnectDeviceBaseData(String key, ConnectDeviceBaseData device) {
        pf().putString(key, device == null ? "" : device.toJson());
    }

    public static ConnectDeviceBaseData getLastConnectDeviceBaseData(String key) {
        return JsonTool.toBean(ConnectDeviceBaseData.class, pf().getString(key));
    }


}
