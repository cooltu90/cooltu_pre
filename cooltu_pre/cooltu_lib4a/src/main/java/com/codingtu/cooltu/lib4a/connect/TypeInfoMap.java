package com.codingtu.cooltu.lib4a.connect;

import com.codingtu.cooltu.lib4a.connect.device.ConnectDevice;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import cooltu.lib4j.data.map.ValueMap;

public class TypeInfoMap {

    private ValueMap<Integer, Map<Integer, TypeInfo>> map;

    private ValueMap<Integer, Map<Integer, TypeInfo>> map() {
        if (map == null) {
            map = new ValueMap<Integer, Map<Integer, TypeInfo>>() {
                @Override
                protected Map<Integer, TypeInfo> newValue() {
                    return new HashMap<>();
                }
            };
            CoreConnectConfigs.configs().typeInfo(this);
        }
        return map;
    }


    public void add(int connectType, int deviceType, BondMethod bondMethod, Class clazz) {
        TypeInfo typeInfo = new TypeInfo();
        typeInfo.connectType = connectType;
        typeInfo.deviceType = deviceType;
        typeInfo.bondMethod = bondMethod;
        typeInfo.deviceClass = clazz;
        map.get(connectType).put(deviceType, typeInfo);
    }

    public TypeInfo get(int connectType, int deviceType) {
        return map().get(connectType).get(deviceType);
    }

    public boolean is(int connectType, int deviceType, BondMethod method) {
        TypeInfo typeInfo = get(connectType, deviceType);
        return typeInfo != null && typeInfo.bondMethod == method;

    }

    public ConnectDevice getConnectDevice(ConnectDeviceBaseData baseData) {
        try {
            return get(baseData.connectType, baseData.deviceType).deviceClass.getConstructor(ConnectDeviceBaseData.class).newInstance(baseData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
