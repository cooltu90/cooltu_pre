package com.codingtu.cooltu.lib4j.ls.impl.basic;

import com.codingtu.cooltu.lib4j.ls.impl.BaseTs;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class TArrayTs<T> extends BaseTs<T> {

    private T[] ts;

    public TArrayTs(T... ts) {
        this.ts = ts;
    }

    @Override
    public T get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, T target) {
        ts[index] = target;
    }

}
