package com.codingtu.cooltu.lib4j.os.impl;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.os.Os;

public class FloatOs extends Os<Float> {

    private float[] ts;

    public FloatOs(float... ts) {
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
