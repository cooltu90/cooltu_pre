package com.codingtu.cooltu.lib4j.tts.ts;

import com.codingtu.cooltu.lib4j.data.bean.Symbol;

public class ListSymbolTs<T extends Symbol> extends SymbolTs<T> {

    public ListSymbolTs(ListTs<T> ts) {
        super(ts);
    }

    public void replaceOrAdd(T target) {
        if (target == null)
            return;

        ((ListTs<T>) ts).replaceOrAdd(target, symbolFilter(target));
    }

    public void delete(T target) {
        if (target == null)
            return;

        ((ListTs<T>) ts).delete(symbolFilter(target));
    }

    public void delete(String symbol) {
        if (symbol == null)
            return;

        ((ListTs<T>) ts).delete(stringSymbolFilter(symbol));
    }
}
