package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.constant.InputType;
import com.codingtu.cooltu.processor.annotation.net.NetBack;
import com.codingtu.cooltu_pre.R;

import core.actbase.TestActivityBase;

import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu_pre.bean.User;

import core.actres.TestActivityRes;
import core.tools.Mocks;
import core.tools.Net;

@To(TestActivityRes.class)
@ActBase(layout = R.layout.activity_test, baseClass = BaseTestActivity.class)
public class TestActivity extends TestActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Net.getObj("id", "desc").main(this);
    }

    @NetBack(mock = Mocks.TEST_ACTIVITY_GET_OBJ_BACK)
    public void getObjBack(User user1) {

    }

}
