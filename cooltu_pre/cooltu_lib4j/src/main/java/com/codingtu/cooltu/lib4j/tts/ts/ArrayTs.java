package com.codingtu.cooltu.lib4j.tts.ts;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tts.Ts;

public class ArrayTs<T> extends Ts<T> {

    private T[] ts;

    public ArrayTs(T... ts) {
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
