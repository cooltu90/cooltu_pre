package com.codingtu.cooltu.lib4j.os.impl;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.os.Os;

public class ByteOs extends Os<Byte> {

    private byte[] ts;

    public ByteOs(byte... ts) {
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