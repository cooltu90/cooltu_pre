package com.codingtu.cooltu_pre.form;

import com.codingtu.cooltu.lib4a.form.FormCheck;
import com.codingtu.cooltu_pre.bean.TestForm;

public class SeekBarCheck implements FormCheck<TestForm, Integer> {
    @Override
    public boolean check(TestForm testForm, Integer integer) {
        return true;
    }
}
