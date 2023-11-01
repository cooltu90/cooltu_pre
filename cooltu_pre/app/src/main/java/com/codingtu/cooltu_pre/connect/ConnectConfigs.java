package com.codingtu.cooltu_pre.connect;

import com.codingtu.cooltu.lib4a.connect.BondMethod;
import com.codingtu.cooltu.lib4a.connect.CoreConnectConfigs;
import com.codingtu.cooltu.lib4a.connect.TypeInfoMap;
import com.codingtu.cooltu_pre.connect.device.RfCrazy;

import java.util.Map;

public class ConnectConfigs extends CoreConnectConfigs {

    public static ConnectConfigs configs() {
        return (ConnectConfigs) CoreConnectConfigs.configs();
    }

    @Override
    public void connectLinkCacheKeyMap(Map<Integer, String> map) {
        map.put(ConnectType.UFO, "last_ufo_connnect_device");
    }

    @Override
    public void typeInfo(TypeInfoMap typeInfoMap) {
        typeInfoMap.add(ConnectType.UFO, ConnectDeviceType.RF_CRAZY, BondMethod.WIFI, RfCrazy.class);
    }

    public boolean isWifi(int deviceType) {
        return false;
    }

    public boolean isShowBettery(int deviceType) {
        return true;
    }

    public boolean isShowMoreBt(int deviceType) {
        return true;
    }

}
