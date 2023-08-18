package com.codingtu.cooltu.lib4a.connect;

import com.codingtu.cooltu.lib4j.data.bean.CoreBean;

public class ConnectDeviceBaseData extends CoreBean {

    public int connectType;
    public int deviceType;
    public String name;
    public String mac;

    public ConnectDeviceBaseData() {
    }

    public ConnectDeviceBaseData(int connectType, int deviceType, String name, String mac) {
        this.connectType = connectType;
        this.deviceType = deviceType;
        this.name = name;
        this.mac = mac;
    }
}
