package com.codingtu.cooltu_pre.ui;

import android.widget.TextView;

import com.codingtu.cooltu.lib4a.act.CoreActivity;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.InBaseClickView;
import com.codingtu.cooltu_pre.R;

public class CoreTestActivity extends CoreActivity {

    @InBase
    public String name;
    @InBase
    public TextView tv;


    @InBaseClickView(value = R.id.tv,inAct = false)
    public void tvClick() {
        toast("tv");
    }

}
