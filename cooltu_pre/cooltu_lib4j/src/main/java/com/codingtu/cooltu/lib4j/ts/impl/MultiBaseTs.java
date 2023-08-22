package com.codingtu.cooltu.lib4j.ts.impl;

public class MultiBaseTs<T> extends BaseTs<T> {

    protected BaseTs<T> ts;

    public MultiBaseTs(BaseTs<T> ts) {
        this.ts = ts;
    }

    @Override
    public T get(int position) {
        return ts.get(position);
    }

    @Override
    public int count() {
        return ts.count();
    }

    @Override
    public void set(int index, T target) {
        ts.set(index, target);
    }

}
