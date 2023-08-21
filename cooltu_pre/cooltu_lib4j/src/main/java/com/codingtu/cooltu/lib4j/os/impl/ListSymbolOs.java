package com.codingtu.cooltu.lib4j.os.impl;

import com.codingtu.cooltu.lib4j.data.bean.Symbol;

public class ListSymbolOs<T extends Symbol> extends SymbolOs<T> {

    public ListSymbolOs(ListOs<T> ts) {
        super(ts);
    }

    public void replaceOrAdd(T target) {
        if (target == null)
            return;

        ((ListOs<T>) os).replaceOrAdd(target, symbolFilter(target));
    }

    public void delete(T target) {
        if (target == null)
            return;

        ((ListOs<T>) os).delete(symbolFilter(target));
    }

    public void delete(String symbol) {
        if (symbol == null)
            return;

        ((ListOs<T>) os).delete(stringSymbolFilter(symbol));
    }
}
