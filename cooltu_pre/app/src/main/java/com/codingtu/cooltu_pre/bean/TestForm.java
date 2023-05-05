package com.codingtu.cooltu_pre.bean;

import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.form.FormItem;
import com.codingtu.cooltu.processor.annotation.form.FormRadioGroup;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu_pre.R;
import com.codingtu.cooltu_pre.form.DefaultOnSetItem;
import com.codingtu.cooltu_pre.form.RgFormCheck;

import cooltu.lib4j.data.bean.CoreBean;

@FormBean
public class TestForm extends CoreBean {

    @FormItem(viewId = R.id.rgLl, type = FormType.RADIO_GROUP, prompt = "请选择")
    @FormRadioGroup(onSetItem = DefaultOnSetItem.class)
    public int rg = -1;
}
