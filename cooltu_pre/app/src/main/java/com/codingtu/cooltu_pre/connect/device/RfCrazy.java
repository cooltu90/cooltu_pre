package com.codingtu.cooltu_pre.connect.device;

import android.annotation.SuppressLint;

import com.codingtu.cooltu.lib4a.connect.ConnectDeviceBaseData;
import com.codingtu.cooltu.lib4a.connect.ResponseData;
import com.codingtu.cooltu.lib4a.connect.device.WifiConnectDevice;
import com.codingtu.cooltu.lib4a.log.Logs;

@SuppressLint("MissingPermission")
public class RfCrazy extends WifiConnectDevice {
    public RfCrazy(ConnectDeviceBaseData baseData) {
        super(baseData);
    }

    @Override
    protected int getPort() {
        return 45689;
    }

    @Override
    protected String getIp() {
        return "192.168.59.203";
    }

    @Override
    public ResponseData[] parseResponseDatas(byte[] bytes) {
        Logs.i("read");
        return null;
    }
}