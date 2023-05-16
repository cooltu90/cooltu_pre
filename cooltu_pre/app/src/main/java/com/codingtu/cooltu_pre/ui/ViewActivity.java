package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.tools.SDCardTool;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu_pre.R;

import core.actbase.ViewActivityBase;
import core.actres.ViewActivityRes;

@To(ViewActivityRes.class)
@ActBase(layout = R.layout.activity_view)
public class ViewActivity extends ViewActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String file = SDCardTool.getSDCard() + "/testimg/5.jpg";
        iv.setFile(file);
    }
}
