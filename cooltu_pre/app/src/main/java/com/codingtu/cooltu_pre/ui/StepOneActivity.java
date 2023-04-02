package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu_pre.R;

import core.actbase.StepOneActivityBase;
import core.processor.annotation.tools.To;
import core.processor.annotation.ui.ActBase;
import core.actres.StepOneActivityRes;

@To(StepOneActivityRes.class)
@ActBase(layout = R.layout.activity_step_one)
public class StepOneActivity extends StepOneActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
