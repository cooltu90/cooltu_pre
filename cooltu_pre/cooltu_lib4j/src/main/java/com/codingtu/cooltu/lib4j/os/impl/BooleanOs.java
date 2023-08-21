package com.codingtu.cooltu.lib4j.os.impl;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.os.Os;

public class BooleanOs extends Os<Boolean> {

    private boolean[] ts;

    public BooleanOs(boolean... ts) {
        this.ts = ts;
    }

    @Override
    public Boolean get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, Boolean target) {
        ts[index] = target;
    }
}
