package com.codingtu.cooltu.lib4j.tts.ts;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tts.Ts;

public class CharTs extends Ts<Character> {

    private char[] ts;

    public CharTs(char... ts) {
        this.ts = ts;
    }

    @Override
    public Character get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, Character target) {
        ts[index] = target;
    }
}
