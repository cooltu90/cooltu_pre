package com.codingtu.cooltu.lib4j.tts.ts;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tts.Ts;

public class LongTs extends Ts<Long> {

    private long[] ts;

    public LongTs(long... ts) {
        this.ts = ts;
    }

    @Override
    public Long get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, Long target) {
        ts[index] = target;
    }
}