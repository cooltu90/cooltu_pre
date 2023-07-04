package com.codingtu.cooltu_pre.ui;

import android.widget.TextView;

import com.codingtu.cooltu.lib4a.act.CoreActivity;
import com.codingtu.cooltu.lib4a.tools.ViewTool;

public class BaseTestActivity extends CoreActivity {

    public String name;
    public TextView tv;

    @Override
    protected void onCreateComplete() {
        super.onCreateComplete();
        ViewTool.setText(tv,"sdfsdfdfs");
    }
}
