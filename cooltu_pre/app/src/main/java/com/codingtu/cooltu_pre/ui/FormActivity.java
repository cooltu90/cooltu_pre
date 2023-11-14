package com.codingtu.cooltu_pre.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu_pre.R;

import core.actbase.FormActivityBase;

import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu_pre.bean.TestForm;

import core.actres.FormActivityRes;

@To(value = FormActivityRes.class,ids = R.layout.activity_form)
@Form(TestForm.class)
@ActBase(layout = R.layout.activity_form)
public class FormActivity extends FormActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @ClickView(value = R.id.bt)
    public void btClick() {
        showEditDialog("");
    }
}
