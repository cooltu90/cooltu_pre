package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu_pre.R;

import core.actbase.ViewActivityBase;

import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actres.ViewActivityRes;

@To(ViewActivityRes.class)
@ActBase(layout = R.layout.activity_view)
public class ViewActivity extends ViewActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewTool.arrangeView(rv, 4, 10, 10, 10);

    }
}
