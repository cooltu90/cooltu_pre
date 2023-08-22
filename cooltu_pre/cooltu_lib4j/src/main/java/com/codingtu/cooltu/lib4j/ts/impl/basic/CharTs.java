package com.codingtu.cooltu.lib4j.ts.impl.basic;

import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class CharTs extends BaseTs<Character> {

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