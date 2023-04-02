package com.codingtu.cooltu.lib4a.bus;

import cooltu.lib4j.data.map.ListValueMap;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;

public class BusStation {

    private static ListValueMap<String, Bus> map = new ListValueMap<>();

    public static void add(Bus bus) {
        map.get(bus.getTask()).add(bus);
    }

    public static void remove(Bus bus) {
        map.get(bus.getTask()).remove(bus);
    }

    public static void send(String task, Object obj) {
        Ts.ls(map.get(task), new Each<Bus>() {
            @Override
            public boolean each(int position, Bus bus) {
                bus.back(obj);
                return false;
            }
        });
    }
}
