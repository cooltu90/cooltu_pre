package com.codingtu.cooltu.lib4j.ts.impl;

public interface TsInterface<T> {

    T get(int position);

    int count();

    void set(int index, T target);
}
