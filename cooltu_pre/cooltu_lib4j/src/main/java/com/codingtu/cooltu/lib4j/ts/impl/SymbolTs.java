package com.codingtu.cooltu.lib4j.ts.impl;

import com.codingtu.cooltu.lib4j.data.bean.Symbol;

public class SymbolTs<T> extends MultiBaseTs<T> {
    public SymbolTs(BaseTs<T> ts) {
        super(ts);
    }

    protected IsThisOne<T> symbolIsThisOne(T target) {
        return new IsThisOne<T>() {
            @Override
            public boolean isThisOne(int position, T t) {
                return t != null && getSymbol(t).equals(getSymbol(target));
            }
        };
    }

    protected IsThisOne<T> stringSymbolIsThisOne(String symbol) {
        return new IsThisOne<T>() {
            @Override
            public boolean isThisOne(int position, T t) {
                return t != null && getSymbol(t).equals(symbol);
            }
        };
    }


    public T get(T target) {
        if (target == null)
            return null;
        return get(symbolIsThisOne(target));
    }

    public T get(String symbol) {
        if (symbol == null)
            return null;
        return get(stringSymbolIsThisOne(symbol));
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
        return index(symbolIsThisOne(target));
    }

    public int index(String symbol) {
        if (symbol == null)
            return -1;
        return index(stringSymbolIsThisOne(symbol));
    }

    public void replace(T target) {
        replace(target, symbolIsThisOne(target));
    }


    private String getSymbol(T t) {
        return ((Symbol) t).obtainSymbol();
    }
}
