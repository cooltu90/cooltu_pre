package com.codingtu.cooltu_pre;

import android.Manifest;
import android.os.Bundle;

import com.codingtu.cooltu.processor.annotation.permission.Permission;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

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

    @Permission({Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void check() {
//        ActStart.viewActivity(this);
        ActStart.testActivity(this, 100, 10, "id", "lisi");
        finishToNewPage();
    }
}