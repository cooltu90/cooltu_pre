package com.codingtu.cooltu.lib4a.form;

public class DefaultRadioGroupToString extends RadioGroupToString {

    private String[] strs;

    public DefaultRadioGroupToString(String... strs) {
        this.strs = strs;
    }

    @Override
    protected String[] strs() {
        return strs;
    }
}
