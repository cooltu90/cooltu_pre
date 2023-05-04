package com.codingtu.cooltu_pre.form;

import com.codingtu.cooltu.lib4a.form.FormCheck;

public class RgFormCheck implements FormCheck<Integer> {
    @Override
    public boolean check(Integer integer) {
        return integer != null && integer >= 0;
    }
}
