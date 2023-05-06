package com.codingtu.cooltu_pre.form;

import com.codingtu.cooltu.lib4a.form.FormParse;

public class SeekBarParse implements FormParse<Integer, Integer> {
    @Override
    public Integer toView(Integer integer) {
        return integer;
    }

    @Override
    public Integer toBean(Object obj) {
        return (Integer) obj;
    }
}
