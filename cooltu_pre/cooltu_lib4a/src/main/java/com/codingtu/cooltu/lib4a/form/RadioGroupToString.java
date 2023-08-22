package com.codingtu.cooltu.lib4a.form;

import com.codingtu.cooltu.lib4j.ts.Ts;

public abstract class RadioGroupToString implements FormParse<String, Integer> {
    @Override
    public Integer toView(String s) {
        return Ts.ts(strs()).index(s);
    }

    @Override
    public String toBean(Object o) {
        if (o != null && o instanceof Integer) {
            try {
                return strs()[(int) o];
            } catch (Exception e) {
            }
        }
        return null;
    }

    protected abstract String[] strs();

}
