package com.codingtu.cooltu_pre.ui;

import android.widget.TextView;

import com.codingtu.cooltu.lib4a.act.CoreActivity;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.InBase;

public class BaseTestActivity extends CoreActivity {

    @InBase
    public String name;
    @InBase
    public TextView tv;

    @Override
    protected void onCreateComplete() {
        super.onCreateComplete();
        ViewTool.setText(tv, "sdfsdfdfs");
    }

}
