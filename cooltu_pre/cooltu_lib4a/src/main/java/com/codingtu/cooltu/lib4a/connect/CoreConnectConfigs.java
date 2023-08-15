package com.codingtu.cooltu.lib4a.connect;

import com.codingtu.cooltu.lib4a.CoreConfigs;
import com.codingtu.cooltu.lib4a.connect.device.ConnectDevice;
import com.codingtu.cooltu.lib4a.tools.PfTool;

import java.util.HashMap;
import java.util.Map;

import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.eachgetter.EachGetter;
import cooltu.lib4j.ts.getter.Getter;

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

    private Map<Integer, String> connectLinkCacheKeyMap;

    public Map<Integer, String> connectLinkCacheKeyMap() {
        if (connectLinkCacheKeyMap == null) {
            connectLinkCacheKeyMap = new HashMap<>();
            connectLinkCacheKeyMap(connectLinkCacheKeyMap);
        }
        return connectLinkCacheKeyMap;
    }

    public String cacheKey(int connectType) {
        return connectLinkCacheKeyMap().get(connectType);
    }

    protected abstract void connectLinkCacheKeyMap(Map<Integer, String> map);

    /**************************************************
     *
     *
     *
     **************************************************/
    protected abstract ConnectDevice createConnectDevice(ConnectDeviceBaseData baseData);

    public void cacheConnectDeviceBaseData(ConnectDevice connectDevice) {
        PfTool.cacheLastConnectDeviceBaseData(cacheKey(connectDevice.baseData.connectType), connectDevice.baseData);
    }

    public ConnectDevice getLocalCachedConnectDevice(int connectType) {
        ConnectDeviceBaseData baseData = PfTool.getLastConnectDeviceBaseData(cacheKey(connectType));
        if (baseData != null) {
            return createConnectDevice(baseData);
        }
        return null;
    }

}
