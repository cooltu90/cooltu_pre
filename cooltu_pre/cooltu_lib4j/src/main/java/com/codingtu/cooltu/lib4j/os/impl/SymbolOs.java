package com.codingtu.cooltu.lib4j.os.impl;

import com.codingtu.cooltu.lib4j.data.bean.Symbol;
import com.codingtu.cooltu.lib4j.os.Os;

public class SymbolOs<T extends Symbol> extends Os<T> {

    protected Os<T> os;

    public SymbolOs(Os<T> os) {
        this.os = os;
    }

    @Override
    public T get(int position) {
        return os.get(position);
    }

    @Override
    public int count() {
        return os.count();
    }

    @Override
    public void set(int index, T target) {
        os.set(index, target);
    }

    protected Filter<T> symbolFilter(T target) {
        if (target == null)
            return null;
        return new Filter<T>() {
            @Override
            public boolean get(int position, T t) {
                return t != null && t.obtainSymbol().equals(target.obtainSymbol());
            }
        };
    }

    protected Filter<T> stringSymbolFilter(String symbol) {
        if (symbol == null)
            return null;
        return new Filter<T>() {
            @Override
            public boolean get(int position, T t) {
                return t != null && t.obtainSymbol().equals(symbol);
            }
        };
    }

    public T get(T target) {
        if (target == null) {
            return null;
        }
        return get(symbolFilter(target));
    }

    public T get(String symbol) {
        if (symbol == null)
            return null;
        return get(stringSymbolFilter(symbol));
    }

    public boolean has(T target) {
        return get(target) != null;
    }

    public boolean has(String symbol) {
        return get(symbol) != null;
    }

    public int index(T target) {
        if (target == null)
            return -1;
        return index(symbolFilter(target));
    }

    public int index(String symbol) {
        if (symbol == null)
            return -1;
        return index(stringSymbolFilter(symbol));
    }

    public void replace(T target) {
        if (target == null)
            return;
        replace(target, symbolFilter(target));
    }
}
