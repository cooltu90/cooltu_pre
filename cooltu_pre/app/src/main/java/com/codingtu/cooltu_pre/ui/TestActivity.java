package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.view.dialogview.NoticeDialog;
import com.codingtu.cooltu_pre.R;

import core.actbase.TestActivityBase;

import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu_pre.bean.User;

import core.actres.TestActivityRes;

@To(TestActivityRes.class)
@ActBase(layout = R.layout.activity_test, baseClass = BaseTestActivity.class)
public class TestActivity extends TestActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void tvClick() {
        super.tvClick();
        User user = new User();
        user.name = "lisi";
        user.age = 23;
        showDialog(user);
    }

    @Override
    protected void dialogLeft(User user) {
        super.dialogLeft(user);
        Logs.i("clickLeft1:" + user);
    }

    @Override
    protected void dialogRight(User user) {
        super.dialogRight(user);
        Logs.i("clickRight1:" + user);
    }
}
