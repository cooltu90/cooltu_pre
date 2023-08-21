package com.codingtu.cooltu.lib4j.ls.impl;

public interface Ts<T> {

    T get(int position);

    int count();

    void set(int index, T target);
}
