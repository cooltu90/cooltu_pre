package com.codingtu.cooltu_pre.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.tools.TextViewSetter;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
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

        TextViewSetter.obtain(tv2)
                .setTextSizeBySp(12)
                .setBoldStyle()
                .setTextColorByRes(R.color.teal_200)
//                .setBackgroundColor("#000000")
                .setBackgroundResource(R.color.purple_200)
                .set();


    }

    @ActBack(ViewActivity.class)
    public void viewActivityBack(String name) {

    }

}
