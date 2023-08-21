package com.codingtu.cooltu.lib4j.ls.impl.basic;

import com.codingtu.cooltu.lib4j.ls.impl.Ts;
import com.codingtu.cooltu.lib4j.tools.CountTool;

import java.util.List;

public class TListTs<T> implements Ts<T> {

    private List<T> ts;

    public TListTs(List<T> ts) {
        this.ts = ts;
    }

    @Override
    public T get(int position) {
        return ts.get(position);
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, T target) {
        ts.set(index, target);
    }
}
