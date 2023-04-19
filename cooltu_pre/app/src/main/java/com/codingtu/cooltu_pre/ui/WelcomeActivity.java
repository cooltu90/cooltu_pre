package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu_pre.R;
import com.codingtu.cooltu_pre.bean.MyLabel;

import java.util.List;

import cooltu.lib4j.fake.Fake;
import cooltu.lib4j.ts.Ts;
import core.actbase.WelcomeActivityBase;
import core.actres.WelcomeActivityRes;

@To(WelcomeActivityRes.class)
@ActBase(layout = R.layout.activity_welcome)
public class WelcomeActivity extends WelcomeActivityBase {

    private List<MyLabel> ts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ts = Ts.getTs(20, new Ts.Inject<MyLabel>() {
            @Override
            public void inject(MyLabel t) {
                t.label = Fake.nameInNet();
            }
        });
        lv.setLable(R.id.labelTv, ts);
    }

    @ClickView(R.id.lv)
    public void lvClick() {

    }

    @ClickView(value = R.id.labelTv, inAct = false)
    public void labelTvClick(MyLabel label) {
        toast(label.label);
    }

}
