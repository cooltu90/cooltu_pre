package com.codingtu.cooltu.lib4j.ts.impl.basic;

import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class ByteTs extends BaseTs<Byte> {

    private byte[] ts;

    public ByteTs(byte... ts) {
        this.ts = ts;
    }

    @Override
    public Byte get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, Byte target) {
        ts[index] = target;
    }
}
