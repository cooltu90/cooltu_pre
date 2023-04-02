package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu_pre.R;

import core.actbase.ThreeActivityBase;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import core.actres.ThreeActivityRes;

@To(ThreeActivityRes.class)
@ActBase(layout = R.layout.activity_three)
public class ThreeActivity extends ThreeActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
