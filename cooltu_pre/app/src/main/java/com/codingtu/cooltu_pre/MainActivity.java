package com.codingtu.cooltu_pre;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.codingtu.cooltu.lib4a.connect.ConnectTool;
import com.codingtu.cooltu.lib4a.download.Download;
import com.codingtu.cooltu.processor.annotation.permission.Permission;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu_pre.connect.ConnectDeviceType;
import com.codingtu.cooltu_pre.connect.ConnectType;

import core.actbase.MainActivityBase;
import core.tools.ActStart;
import core.tools.Permissions;

@ActBase(layout = R.layout.activity_main)
public class MainActivity extends MainActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Permissions.check(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Permission({
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
    })
    public void check(boolean isAllow) {
        ConnectTool.cacheConnectDeviceBaseData(ConnectType.UFO, ConnectDeviceType.RF_CRAZY, "DCWIFI", "92:38:C5:92:5C:74");
        toast("完成权限");

        Download.url("").progress(new Download.OnProgress() {
            @Override
            public void onProgress(long totalLen, long currentSize) {

            }
        }).download();
    }

    @ClickView(R.id.bt1)
    public void bt1Click() {
        ActStart.connectActivity(this);
    }

    @ClickView(R.id.bt2)
    public void bt2Click() {
        ActStart.connectActivity(this);
    }
}