package com.codingtu.cooltu.lib4a.connect;

import com.codingtu.cooltu.lib4a.CoreConfigs;
import com.codingtu.cooltu.lib4a.connect.device.ConnectDevice;

import java.util.Map;

public abstract class CoreConnectConfigs {

    private static CoreConnectConfigs CONFIGS;

    public static CoreConnectConfigs configs() {
        if (CONFIGS == null) {
            CONFIGS = CoreConfigs.configs().getConnectConfigs();
        }
        return CONFIGS;
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public abstract void connectLinkCacheKeyMap(Map<Integer, String> map);

    /**************************************************
     *
     *
     *
     **************************************************/
    public abstract ConnectDevice createConnectDevice(ConnectDeviceBaseData baseData);

    public abstract void bondMethod(BondMethodMap bondeds);

}
