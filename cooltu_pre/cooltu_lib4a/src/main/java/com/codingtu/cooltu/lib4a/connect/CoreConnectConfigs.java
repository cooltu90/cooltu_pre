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

    private Map<String, String> connectLinkCacheKeyMap;

    public Map<String, String> connectLinkCacheKeyMap() {
        if (connectLinkCacheKeyMap == null) {
            connectLinkCacheKeyMap = new HashMap<>();
            connectLinkCacheKeyMap(connectLinkCacheKeyMap);
        }
        return connectLinkCacheKeyMap;
    }

    public String cacheKey(String connectType) {
        return connectLinkCacheKeyMap().get(connectType);
    }

    protected abstract void connectLinkCacheKeyMap(Map<String, String> map);

    /**************************************************
     *
     *
     *
     **************************************************/
    protected abstract ConnectDevice createConnectDevice(ConnectDeviceBaseData baseData);

    private void cacheConnectDeviceBaseData(ConnectDevice connectDevice) {
        PfTool.cacheLastConnectDeviceBaseData(cacheKey(connectDevice.baseData.connectType), connectDevice.baseData);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public ConnectDevice getLocalCachedConnectDevice(String connectType) {
        ConnectDeviceBaseData baseData = PfTool.getLastConnectDeviceBaseData(cacheKey(connectType));
        if (baseData != null) {
            return createConnectDevice(baseData);
        }
        return null;
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public boolean isBluetoothBondDirect(String type) {
        return Ts.has(getBluetoothBondDirect(), new Getter<Integer, String>() {
            @Override
            public boolean get(Integer integer, String s) {
                return s.equals(type);
            }
        });
    }

    public abstract String[] getBluetoothBondDirect();

    public boolean isBluetoothBondPair(String type) {
        return Ts.has(getBluetoothBondDirect(), new Getter<Integer, String>() {
            @Override
            public boolean get(Integer integer, String s) {
                return s.equals(type);
            }
        });
    }

    public abstract String[] getBluetoothBondPair();


}
