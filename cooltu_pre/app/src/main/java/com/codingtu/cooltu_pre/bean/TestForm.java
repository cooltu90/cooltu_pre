package com.codingtu.cooltu_pre.bean;

import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.form.FormCheck;
import com.codingtu.cooltu.processor.annotation.form.FormParse;
import com.codingtu.cooltu.processor.annotation.form.item.EditTextFormItem;
import com.codingtu.cooltu.processor.annotation.form.item.RadioGroupFormItem;
import com.codingtu.cooltu.processor.annotation.form.item.SeekBarFormItem;
import com.codingtu.cooltu_pre.R;
import com.codingtu.cooltu_pre.form.DefaultOnSetItem;
import com.codingtu.cooltu_pre.form.FriutParse;
import com.codingtu.cooltu_pre.form.SeekBarParse;
import com.codingtu.cooltu_pre.form.SeekBarsCheck;

import cooltu.lib4j.data.bean.CoreBean;

@FormBean
public class TestForm extends CoreBean {

    @RadioGroupFormItem(value = R.id.rgLl, prompt = "请选择",
            onSetItem = DefaultOnSetItem.class, echoCheck = true,
            strItems = {"梨1", "苹果1", "芒果1"})
    public String rg;

    @SeekBarFormItem(value = R.id.seekBar, prompt = "请选择时间")
    @FormParse(SeekBarParse.class)
    @FormCheck(SeekBarsCheck.class)
    public int time;

    @EditTextFormItem(value = R.id.et, prompt = "请输入名字")
    public String name;
}
