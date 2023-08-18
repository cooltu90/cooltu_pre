package com.codingtu.cooltu.lib4a.form;

import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.getter.Getter;

public abstract class RadioGroupToString implements FormParse<String, Integer> {
    @Override
    public Integer toView(String s) {
        return Ts.index(strs(), new Getter<Integer, String>() {
            @Override
            public boolean get(Integer integer, String str) {
                return str.equals(s);
            }
        });
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
