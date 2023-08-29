package com.codingtu.cooltu_pre.ui;

import android.widget.TextView;

import com.codingtu.cooltu.lib4a.act.CoreActivity;
import com.codingtu.cooltu.processor.annotation.resource.ColorRes;
import com.codingtu.cooltu.processor.annotation.resource.ColorStr;
import com.codingtu.cooltu.processor.annotation.resource.Dimen;
import com.codingtu.cooltu.processor.annotation.resource.Dp;
import com.codingtu.cooltu.processor.annotation.resource.ResForBase;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.InBaseActBack;
import com.codingtu.cooltu.processor.annotation.ui.InBaseClickView;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu_pre.R;

@ResForBase
public class CoreTestActivity extends CoreActivity {

    @StartGroup
    public String id;
    @StartGroup
    public String name;

    @InBase
    public TextView tv;

    @ColorStr("#ffffff")
    public int color1;

    @ColorRes(R.color.black)
    public int color2;

    @Dp(2.5f)
    public int dp1;

    @Dimen(R.dimen.d1)
    public int dp2;

    @InBaseClickView(value = R.id.tv)
    public void tvClick() {
    }

    @InBaseActBack(StepOneActivity.class)
    public void stepOneActivityBack(int age) {

    }

}
