package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu_pre.R;

import core.actbase.TestActivityBase;

import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actres.TestActivityRes;

@To(TestActivityRes.class)
@ActBase(layout = R.layout.activity_test, baseClass = BaseTestActivity.class)
public class TestActivity extends TestActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @ActBack(ViewActivity.class)
    public void viewActivityBack(String name) {

    }

}
