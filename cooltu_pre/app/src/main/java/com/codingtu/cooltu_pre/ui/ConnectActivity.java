package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.connect.ConnectCallBack;
import com.codingtu.cooltu.lib4a.connect.ConnectTool;
import com.codingtu.cooltu.lib4a.connect.ResponseData;
import com.codingtu.cooltu.lib4a.connect.device.ConnectDevice;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu_pre.R;

import core.actbase.ConnectActivityBase;

import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu_pre.connect.ConnectDeviceType;
import com.codingtu.cooltu_pre.connect.ConnectType;

import core.actres.ConnectActivityRes;

@To(ConnectActivityRes.class)
@ActBase(layout = R.layout.activity_connect)
public class ConnectActivity extends ConnectActivityBase implements ConnectCallBack {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectTool.connect(this, ConnectType.UFO, this);
    }

    @ClickView(R.id.bt)
    public void btClick() {
        ConnectTool.cacheConnectDeviceBaseData(ConnectType.UFO, ConnectDeviceType.RF_CRAZY, "DCWIFI", "92:38:C5:92:5C:75");

        ConnectTool.connect(this, ConnectType.UFO, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ConnectTool.removeCallBack(ConnectType.UFO, this);
    }

    @Override
    public void noDevice() {

    }

    @Override
    public void connecting(ConnectDevice connectDevice) {

    }

    @Override
    public void connectSuccess(ConnectDevice connectDevice) {
        Logs.i("connectSuccess:"+connectDevice.baseData.mac);
    }

    @Override
    public void connectFail() {

    }

    @Override
    public void read(ConnectDevice connectDevice, ResponseData data) {

    }

    @Override
    public void disconnect(ConnectDevice connectDevice) {
        Logs.i("disconnect");
    }
}
