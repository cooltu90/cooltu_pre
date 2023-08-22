package com.codingtu.cooltu.lib4j.ls.impl.basic;


import com.codingtu.cooltu.lib4j.ls.impl.BaseTs;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class DoubleTs extends BaseTs<Double> {

    private double[] ts;

    public DoubleTs(double... ts) {
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
