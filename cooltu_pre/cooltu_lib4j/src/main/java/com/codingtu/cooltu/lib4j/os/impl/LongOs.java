package com.codingtu.cooltu.lib4j.os.impl;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.os.Os;

public class LongOs extends Os<Long> {

    private long[] ts;

    public LongOs(long... ts) {
        this.ts = ts;
    }

    @Override
    public Long get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, Long target) {
        ts[index] = target;
    }
}