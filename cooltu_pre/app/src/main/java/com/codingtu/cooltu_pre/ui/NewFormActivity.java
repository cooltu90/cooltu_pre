package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu_pre.R;

import core.actbase.NewFormActivityBase;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import core.actres.NewFormActivityRes;

@To(NewFormActivityRes.class)
@ActBase(layout = R.layout.activity_new_form)
public class NewFormActivity extends NewFormActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
