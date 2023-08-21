package com.codingtu.cooltu.lib4j.os.impl;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.os.Os;

public class CharOs extends Os<Character> {

    private char[] ts;

    public CharOs(char... ts) {
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
