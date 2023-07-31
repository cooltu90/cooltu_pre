package com.codingtu.cooltu.lib4a.connect;

import com.codingtu.cooltu.lib4a.connect.device.ConnectDevice;

public interface ConnectCallBack {
    void noDevice();

    void connecting(ConnectDevice connectDevice);

    void connectSuccess(ConnectDevice connectDevice);

    void connectFail();

    void read(ConnectDevice connectDevice, ResponseData data);

    void disconnect(ConnectDevice connectDevice);
}
