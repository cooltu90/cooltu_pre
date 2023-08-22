package com.codingtu.cooltu.lib4j.ts.impl.basic;

import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class ShortTs extends BaseTs<Short> {

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
