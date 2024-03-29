package com.codingtu.cooltu.lib4j.ts.impl.basic;

import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class IntTs extends BaseTs<Integer> {

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
