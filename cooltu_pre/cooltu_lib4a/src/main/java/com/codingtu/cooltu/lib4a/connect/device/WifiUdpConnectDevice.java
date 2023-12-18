package com.codingtu.cooltu.lib4a.connect.device;

import com.codingtu.cooltu.lib4a.connect.ConnectDeviceBaseData;
import com.codingtu.cooltu.lib4a.connect.ResponseData;

public abstract class WifiUdpConnectDevice extends ConnectDevice {
    public WifiUdpConnectDevice(int connectType, int deviceType, String name, String mac) {
        super(connectType, deviceType, name, mac);
    }

    public WifiUdpConnectDevice(ConnectDeviceBaseData baseData) {
        super(baseData);
    }

    @Override
    public void connect() {




    }

}
