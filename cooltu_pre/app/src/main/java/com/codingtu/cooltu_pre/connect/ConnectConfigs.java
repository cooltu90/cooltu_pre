package com.codingtu.cooltu_pre.connect;

import com.codingtu.cooltu.lib4a.connect.BondMethod;
import com.codingtu.cooltu.lib4a.connect.BondMethodMap;
import com.codingtu.cooltu.lib4a.connect.ConnectDeviceBaseData;
import com.codingtu.cooltu.lib4a.connect.CoreConnectConfigs;
import com.codingtu.cooltu.lib4a.connect.device.ConnectDevice;

import java.util.Map;

public class ConnectConfigs extends CoreConnectConfigs {

    @Override
    public void connectLinkCacheKeyMap(Map<Integer, String> map) {
        map.put(ConnectType.UFO, "PfName.LAST_UFO_CONNNECT_DEVICE");
    }

    @Override
    public ConnectDevice createConnectDevice(ConnectDeviceBaseData baseData) {
        return null;
    }

    @Override
    public void bondMethod(BondMethodMap map) {
        map.direct(ConnectType.UFO, ConnectDeviceType.RF_CRAZY);
    }


}
