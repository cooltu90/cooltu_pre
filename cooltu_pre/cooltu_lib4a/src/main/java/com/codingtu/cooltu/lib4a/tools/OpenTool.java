package com.codingtu.cooltu.lib4a.tools;

import android.content.Intent;
import android.net.Uri;

import com.codingtu.cooltu.lib4a.CoreApp;

public class OpenTool {

    public static void openTel(String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + tel);
        intent.setData(data);
        CoreApp.APP.startActivity(intent);
    }

}
