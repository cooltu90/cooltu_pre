package com.codingtu.cooltu_pre.ui;

import android.widget.TextView;

import com.codingtu.cooltu.lib4a.act.CoreActivity;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.processor.annotation.ui.Base;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.InBase;

@Base(CoreTestActivity.class)
public class BaseTestActivity extends CoreTestActivity {
    @InBase
    public int age;

}
