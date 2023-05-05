package com.codingtu.cooltu.lib4a.form;

public class DefaultRadioGroupFormCheck implements FormCheck<Integer> {
    @Override
    public boolean check(Integer integer) {
        return integer != null && integer >= 0;
    }
}
