package com.codingtu.cooltu.lib4j.ls.impl;

import com.codingtu.cooltu.lib4j.os.Os;

public abstract class TsParent<T> implements Ts<T> {

    private int step = 1;

    private Ts<T> ts;

    public TsParent(Ts<T> ts) {
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

    /**************************************************
     *
     *
     *
     **************************************************/

    public interface EachTs<T> {
        boolean each(int position, T t);
    }


}
