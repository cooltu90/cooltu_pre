package com.codingtu.cooltu_pre;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.codingtu.cooltu.lib4a.connect.ConnectTool;
import com.codingtu.cooltu.processor.annotation.permission.Permission;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu_pre.path.test.CheckPath;

import core.actbase.MainActivityBase;
import core.tools.ActStart;
import core.tools.Permissions;

@ActBase(layout = R.layout.activity_main)
public class MainActivity extends MainActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Permissions.check(this);
        CheckPath checkPath = CheckPath.obtain("", "");
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
    public void check() {
        ActStart.testActivity(this, "id", "lisi", 100, 10);
        finishToNewPage();
    }
}