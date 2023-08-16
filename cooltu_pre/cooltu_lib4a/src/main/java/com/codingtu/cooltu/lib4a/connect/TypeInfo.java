package com.codingtu.cooltu.lib4a.connect;

import com.codingtu.cooltu.lib4a.connect.device.ConnectDevice;

public class TypeInfo {

    public int connectType;
    public int deviceType;
    public BondMethod bondMethod;
    public Class<? extends ConnectDevice> deviceClass;

}
