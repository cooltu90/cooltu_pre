package com.codingtu.cooltu.lib4a.bus;

import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

public class BusStation {

    private static ListValueMap<String, Bus> map = new ListValueMap<>();

    public static void add(Bus bus) {
        map.get(bus.getTask()).add(bus);
    }

    public static void remove(Bus bus) {
        map.get(bus.getTask()).remove(bus);
    }

    public static void send(String task, Object obj) {
        Ts.ls(map.get(task), new BaseTs.EachTs<Bus>() {
            @Override
            public boolean each(int position, Bus bus) {
                bus.back(obj);
                return false;
            }
        });
    }
}
