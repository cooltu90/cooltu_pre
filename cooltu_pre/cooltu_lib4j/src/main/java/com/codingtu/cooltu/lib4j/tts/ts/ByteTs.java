package com.codingtu.cooltu.lib4j.tts.ts;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tts.Ts;

public class ByteTs extends Ts<Byte> {

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