package com.codingtu.cooltu_pre.connect;

import com.codingtu.cooltu.lib4a.connect.ConnectDeviceBaseData;
import com.codingtu.cooltu.lib4a.connect.CoreConnectConfigs;
import com.codingtu.cooltu.lib4a.connect.device.ConnectDevice;

import java.util.Map;

public class ConnectConfigs extends CoreConnectConfigs {
    @Override
    protected void connectLinkCacheKeyMap(Map<String, String> map) {
        map.put(ConnectType.LDAR, "PfName.LAST_LDAR_CONNNECT_DEVICE");
        map.put(ConnectType.UFO, "PfName.LAST_UFO_CONNNECT_DEVICE");
    }

    @Override
    protected ConnectDevice createConnectDevice(ConnectDeviceBaseData baseData) {
        return null;
    }

    @Override
    public String[] getBluetoothBondDirect() {
        return new String[]{ConnectDeviceType.EXPEC_BLUETOOTH};
    }

    @Override
    public String[] getBluetoothBondPair() {
        return new String[]{ConnectDeviceType.H5200};
    }

}
