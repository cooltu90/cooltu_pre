package com.codingtu.cooltu.lib4j.ls.impl.basic;


import com.codingtu.cooltu.lib4j.ls.impl.Ts;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class FloatTs implements Ts<Float> {

    private float[] ts;

    public FloatTs(float... ts) {
        this.ts = ts;
    }

    @Override
    public Float get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, Float target) {
        ts[index] = target;
    }
}