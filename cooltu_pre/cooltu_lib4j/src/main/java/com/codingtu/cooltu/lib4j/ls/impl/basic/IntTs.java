package com.codingtu.cooltu.lib4j.ls.impl.basic;

import com.codingtu.cooltu.lib4j.ls.impl.Ts;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class IntTs implements Ts<Integer> {

    private int[] ts;

    public IntTs(int... ts) {
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
