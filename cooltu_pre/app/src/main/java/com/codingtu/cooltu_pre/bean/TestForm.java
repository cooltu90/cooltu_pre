package com.codingtu.cooltu_pre.bean;

import com.codingtu.cooltu.lib4a.form.DefaultRadioGroupFormCheck;
import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.form.FormItem;
import com.codingtu.cooltu.processor.annotation.form.FormRadioGroup;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu_pre.R;
import com.codingtu.cooltu_pre.form.DefaultOnSetItem;
import com.codingtu.cooltu_pre.form.RgFormCheck;
import com.codingtu.cooltu_pre.form.SeekBarCheck;
import com.codingtu.cooltu_pre.form.SeekBarParse;

import cooltu.lib4j.data.bean.CoreBean;

@FormBean
public class TestForm extends CoreBean {

    @FormItem(viewId = R.id.rgLl, type = FormType.RADIO_GROUP, prompt = "请选择", check = DefaultRadioGroupFormCheck.class)
    @FormRadioGroup(onSetItem = DefaultOnSetItem.class)
    public int rg = -1;

    @FormItem(viewId = R.id.seekBar, type = FormType.SEEK_BAR, parse = SeekBarParse.class, prompt = "请选择时间", check = SeekBarCheck.class)
    public int time;

    @FormItem(viewId = R.id.et)
    public String name;
}
