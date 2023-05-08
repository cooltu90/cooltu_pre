package com.codingtu.cooltu.lib4a.form;

public class DefaultRadioGroupFormCheck<BEAN> implements FormCheck<BEAN, Integer> {
    @Override
    public boolean check(BEAN bean, Integer integer) {
        return integer != null && integer >= 0;
    }
}
