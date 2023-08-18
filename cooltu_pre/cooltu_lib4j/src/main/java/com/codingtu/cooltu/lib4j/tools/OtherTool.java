package com.codingtu.cooltu.lib4j.tools;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class OtherTool {

    public static Type getParameterizedType(Class clazz, int index) {
        return ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[index];
    }

    public static Type getParameterizedType(Object obj, int index) {
        return getParameterizedType(obj.getClass(), index);
    }

    public static Class getFanxing(Object obj, int index) {
        Type type = getParameterizedType(obj.getClass(), index);
        return type instanceof Class ? (Class) type : null;
    }

    public static Class getFanxing(Object obj) {
        return getFanxing(obj, 0);
    }
}
