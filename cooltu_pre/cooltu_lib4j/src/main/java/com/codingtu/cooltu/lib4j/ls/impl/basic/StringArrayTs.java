package com.codingtu.cooltu.lib4j.ls.impl.basic;

import com.codingtu.cooltu.lib4j.ls.impl.Ts;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class StringArrayTs implements Ts<String> {

    private String[] ts;

    public StringArrayTs(String... ts) {
        this.ts = ts;
    }

    @Override
    public String get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, String target) {
        ts[index] = target;
    }
}
