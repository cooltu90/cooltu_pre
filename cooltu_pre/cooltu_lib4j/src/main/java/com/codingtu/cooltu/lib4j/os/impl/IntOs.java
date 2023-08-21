package com.codingtu.cooltu.lib4j.os.impl;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.os.Os;

public class IntOs extends Os<Integer> {

    private int[] ts;

    public IntOs(int... ts) {
        this.ts = ts;
    }

    @Override
    public Integer get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, Integer target) {
        ts[index] = target;
    }
}
