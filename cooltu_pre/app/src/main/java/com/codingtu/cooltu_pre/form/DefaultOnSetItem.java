package com.codingtu.cooltu_pre.form;

import android.graphics.Color;
import android.view.View;

import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;

public class DefaultOnSetItem implements RadioGroup.OnSetItem {
    @Override
    public void setSelected(View view) {
        view.setBackgroundColor(Color.parseColor("#ff0000"));
    }

    @Override
    public void setSelectno(View view) {
        view.setBackgroundColor(Color.parseColor("#cccccc"));
    }
}
