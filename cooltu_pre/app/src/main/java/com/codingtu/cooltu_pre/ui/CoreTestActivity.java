package com.codingtu.cooltu_pre.ui;

import android.widget.TextView;

import com.codingtu.cooltu.lib4a.act.CoreActivity;
import com.codingtu.cooltu.processor.annotation.resource.ColorStr;
import com.codingtu.cooltu.processor.annotation.resource.InBaseColorStr;
import com.codingtu.cooltu.processor.annotation.resource.ResForBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.InBaseClickView;
import com.codingtu.cooltu.processor.annotation.ui.InBaseStartGroup;
import com.codingtu.cooltu_pre.R;

@ResForBase
public class CoreTestActivity extends CoreActivity {

    @InBaseStartGroup
    public String id;

    @InBase
    public String name;
    @InBase
    public TextView tv;

    @InBaseColorStr("#ffffff")
    public int filterSelectedTextColor;

    @InBaseClickView(value = R.id.tv, inAct = false)
    public void tvClick() {
        toast("tv");
    }

}
