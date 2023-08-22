package com.codingtu.cooltu.lib4j.ts.impl;

import com.codingtu.cooltu.lib4j.ts.impl.basic.TListTs;

public class ListSymbolTs<T> extends SymbolTs<T> {
    public ListSymbolTs(BaseTs<T> ts) {
        super(ts);
    }

    public void replaceOrAdd(T target) {
        if (target == null)
            return;
        ((TListTs) ts).replaceOrAdd(target, symbolIsThisOne(target));
    }

    public void delete(T target) {
        ((TListTs) ts).delete(symbolIsThisOne(target));
    }

    public void deleteAll(T target) {
        ((TListTs) ts).deleteAll(symbolIsThisOne(target));
    }

    public void delete(String symbol) {
        ((TListTs) ts).delete(stringSymbolIsThisOne(symbol));
    }

    public void deleteAll(String symbol) {
        ((TListTs) ts).deleteAll(stringSymbolIsThisOne(symbol));
    }
}
