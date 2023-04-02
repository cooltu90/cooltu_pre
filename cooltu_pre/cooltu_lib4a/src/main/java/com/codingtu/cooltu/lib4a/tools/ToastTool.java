package com.codingtu.cooltu.lib4a.tools;

import android.widget.Toast;

import com.codingtu.cooltu.lib4a.CoreApp;

public class ToastTool {
    public static void toast(String msg) {
        Toast.makeText(CoreApp.APP, msg, Toast.LENGTH_SHORT).show();
    }
}
