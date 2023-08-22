package com.codingtu.cooltu.lib4j.ls.impl.basic;

import com.codingtu.cooltu.lib4j.ls.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ls.impl.ListSymbolTs;
import com.codingtu.cooltu.lib4j.ls.impl.SymbolTs;
import com.codingtu.cooltu.lib4j.tools.CountTool;

import java.util.List;

public class TListTs<T> extends BaseTs<T> {

    private List<T> ts;

    public TListTs(List<T> ts) {
        this.ts = ts;
    }

    @Override
    public T get(int position) {
        return ts.get(position);
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, T target) {
        ts.set(index, target);
    }

    public void replaceOrAdd(T target, IsThisOne<T> isThisOne) {
        if (target == null || isThisOne == null)
            return;

        int index = index(isThisOne);
        if (index >= 0) {
            set(index, target);
        } else {
            ts.add(target);
        }
    }

    public ListSymbolTs<T> symbol() {
        return new ListSymbolTs<>(this);
    }

    public List<T> get() {
        return ts;
    }
}
