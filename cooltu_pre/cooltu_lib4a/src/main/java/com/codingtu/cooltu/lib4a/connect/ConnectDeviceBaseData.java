package com.codingtu.cooltu.lib4a.connect;

import java.util.UUID;

import cooltu.lib4j.data.bean.CoreBean;

public class ConnectDeviceBaseData extends CoreBean {

    public String deviceType;
    public String connectType;
    public String name;
    public String mac;
    public UUID uuid;
    public boolean isWifi;
}
