package com.codingtu.cooltu.lib4j.os.impl;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.os.Os;

public class DoubleOs extends Os<Double> {

    private double[] ts;

    public DoubleOs(double... ts) {
        this.ts = ts;
    }

    @Override
    public Double get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, Double target) {
        ts[index] = target;
    }
}