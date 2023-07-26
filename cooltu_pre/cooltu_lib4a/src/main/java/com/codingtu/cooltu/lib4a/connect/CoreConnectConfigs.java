package com.codingtu.cooltu.lib4a.connect;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;

import com.codingtu.cooltu.lib4a.CoreConfigs;
import com.codingtu.cooltu.lib4a.tools.PfTool;

import java.util.HashMap;
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
    @SuppressLint("MissingPermission")
    protected ConnectDevice bonded(BluetoothDevice device) {
        ConnectDevice connectDevice = createConnectDevice(device.getName(), device.getAddress());
        if (connectDevice != null) {
            cacheConnectDeviceBaseData(connectDevice);
        }
        return connectDevice;
    }

    protected abstract ConnectDevice createConnectDevice(String name, String mac);

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
            return createConnectDevice(baseData.name, baseData.mac);
        }
        return null;
    }


}
