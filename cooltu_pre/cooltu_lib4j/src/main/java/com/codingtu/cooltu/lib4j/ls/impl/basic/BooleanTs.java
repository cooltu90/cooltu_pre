package com.codingtu.cooltu.lib4j.ls.impl.basic;

import com.codingtu.cooltu.lib4j.ls.impl.BaseTs;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class BooleanTs extends BaseTs<Boolean> {

    private boolean[] ts;

    public BooleanTs(boolean... ts) {
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
