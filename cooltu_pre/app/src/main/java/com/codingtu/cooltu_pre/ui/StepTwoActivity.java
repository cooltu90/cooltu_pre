package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu_pre.R;

import core.actbase.StepTwoActivityBase;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import core.actres.StepTwoActivityRes;

@To(StepTwoActivityRes.class)
@ActBase(layout = R.layout.activity_step_two)
public class StepTwoActivity extends StepTwoActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
