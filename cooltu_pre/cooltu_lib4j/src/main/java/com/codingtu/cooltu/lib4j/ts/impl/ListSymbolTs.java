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
}
