package com.codingtu.cooltu.lib4a.connect;

import java.util.HashMap;
import java.util.Map;

import cooltu.lib4j.data.map.ValueMap;
import cooltu.lib4j.tools.CountTool;

public class BondMethodMap {

    private ValueMap<Integer, Map<Integer, BondMethod>> map;

    private ValueMap<Integer, Map<Integer, BondMethod>> map() {
        if (map == null) {
            map = new ValueMap<Integer, Map<Integer, BondMethod>>() {
                @Override
                protected Map<Integer, BondMethod> newValue() {
                    return new HashMap<>();
                }
            };
            CoreConnectConfigs.configs().bondMethod(this);
        }
        return map;
    }

    private BondMethod getBondMethod(int connectType, int deviceType) {
        return map().get(connectType).get(deviceType);
    }

    public boolean is(int connectType, int deviceType, BondMethod method) {
        BondMethod bondMethod = getBondMethod(connectType, deviceType);
        return bondMethod != null && bondMethod == method;
    }

    public void add(BondMethod method, int connectType, int... deviceTypes) {
        if (method == null)
            return;
        int count = CountTool.count(deviceTypes);
        if (count > 0) {
            Map<Integer, BondMethod> integerBondMethodMap = map().get(connectType);
            for (int i = 0; i < count; i++) {
                integerBondMethodMap.put(deviceTypes[i], method);
            }
        }
    }

    public void direct(int connectType, int... deviceTypes) {
        add(BondMethod.DIRECT, connectType, deviceTypes);
    }

    public void pair(int connectType, int... deviceTypes) {
        add(BondMethod.PAIR, connectType, deviceTypes);
    }
}
