package com.codingtu.cooltu.lib4j.ls.impl.basic;

import com.codingtu.cooltu.lib4j.ls.impl.Ts;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class ShortTs implements Ts<Short> {

    private short[] ts;

    public ShortTs(short... ts) {
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
