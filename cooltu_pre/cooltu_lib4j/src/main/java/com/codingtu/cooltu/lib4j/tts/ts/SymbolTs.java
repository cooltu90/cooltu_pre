package com.codingtu.cooltu.lib4j.tts.ts;

import com.codingtu.cooltu.lib4j.data.bean.Symbol;
import com.codingtu.cooltu.lib4j.tts.Ts;

public class SymbolTs<T extends Symbol> extends Ts<T> {

    protected Ts<T> ts;

    public SymbolTs(Ts<T> ts) {
        this.ts = ts;
    }

    @Override
    public T get(int position) {
        return ts.get(position);
    }

    @Override
    public int count() {
        return ts.count();
    }

    @Override
    public void set(int index, T target) {
        ts.set(index, target);
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
