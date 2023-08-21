package com.codingtu.cooltu.lib4j.os.impl;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.os.Os;

import java.util.List;

public class ListOs<T> extends Os<T> {

    private List<T> ts;

    public ListOs(List<T> ts) {
        this.ts = ts;
    }

    public List<T> getTs() {
        return ts;
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

    public void replaceOrAdd(T target, Filter<T> filter) {
        if (target == null || filter == null)
            return;

        int index = index(filter);
        if (index >= 0) {
            set(index, target);
        } else {
            ts.add(target);
        }
    }

    public void delete(Filter<T> filter) {
        if (filter == null)
            return;

        int index = index(filter);
        if (index >= 0) {
            ts.remove(index);
        }
    }

    public void add(T target) {
        if (target == null || ts == null)
            return;
        ts.add(target);
    }
}
