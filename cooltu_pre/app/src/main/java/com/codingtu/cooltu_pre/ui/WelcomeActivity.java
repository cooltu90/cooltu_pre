package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu_pre.R;

import core.actbase.WelcomeActivityBase;
import core.processor.annotation.tools.To;
import core.processor.annotation.ui.ActBase;
import core.actres.WelcomeActivityRes;

@To(WelcomeActivityRes.class)
@ActBase(layout = R.layout.activity_welcome)
public class WelcomeActivity extends WelcomeActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
