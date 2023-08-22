package com.codingtu.cooltu.lib4j.ts.impl.basic;


import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class LongTs extends BaseTs<Long> {

    private long[] ts;

    public LongTs(long... ts) {
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