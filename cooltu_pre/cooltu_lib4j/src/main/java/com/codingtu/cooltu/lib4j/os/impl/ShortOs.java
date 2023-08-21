package com.codingtu.cooltu.lib4j.os.impl;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.os.Os;

public class ShortOs extends Os<Short> {

    private short[] ts;

    public ShortOs(short... ts) {
        this.ts = ts;
    }

    @Override
    public Short get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, Short target) {
        ts[index] = target;
    }
}
