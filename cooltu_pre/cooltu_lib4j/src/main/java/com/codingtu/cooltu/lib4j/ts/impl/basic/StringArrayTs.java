package com.codingtu.cooltu.lib4j.ts.impl.basic;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

public class StringArrayTs extends BaseTs<String> {

    private String[] ts;

    public StringArrayTs(String[] ts) {
        this.ts = ts;
    }

    @Override
    public String get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, String target) {
        ts[index] = target;
    }

    private IsThisOne<String> stringIsThisOne(String str) {
        return new IsThisOne<String>() {
            @Override
            public boolean isThisOne(int position, String s) {
                return str.equals(s);
            }
        };
    }

    public String get(String str) {
        if (str == null)
            return null;
        return get(stringIsThisOne(str));
    }

    public boolean has(String str) {
        return get(str) != null;
    }

    public int index(String str) {
        if (str == null)
            return -1;
        return index(stringIsThisOne(str));
    }
}
