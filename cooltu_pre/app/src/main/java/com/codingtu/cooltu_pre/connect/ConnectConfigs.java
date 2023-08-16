package com.codingtu.cooltu_pre.connect;

import com.codingtu.cooltu.lib4a.connect.BondMethod;
import com.codingtu.cooltu.lib4a.connect.CoreConnectConfigs;
import com.codingtu.cooltu.lib4a.connect.TypeInfoMap;
import com.codingtu.cooltu_pre.connect.device.RfCrazy;

import java.util.Map;

public class ConnectConfigs extends CoreConnectConfigs {

    @Override
    public void connectLinkCacheKeyMap(Map<Integer, String> map) {
        map.put(ConnectType.UFO, "PfName.LAST_UFO_CONNNECT_DEVICE");
    }

    @Override
    public void typeInfo(TypeInfoMap typeInfoMap) {
        typeInfoMap.add(ConnectType.UFO, ConnectDeviceType.RF_CRAZY, BondMethod.DIRECT, RfCrazy.class);
    }


}
