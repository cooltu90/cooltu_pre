package com.codingtu.cooltu.lib4j.ts.impl;

import com.codingtu.cooltu.lib4j.ts.Ts;

import java.util.Map;

public class MapTs<K, V> {

    private Map<K, V> ts;

    public MapTs(Map<K, V> ts) {
        this.ts = ts;
    }

    public interface MapEach<K, V> {
        public boolean each(K k, V v);
    }

    public void ls(MapEach<K, V> mapEach) {
        Ts.ts(ts.keySet()).ls(new SetTs.SetEach<K>() {
            @Override
            public boolean each(K k) {
                return mapEach.each(k, ts.get(k));
            }
        });
    }


}
