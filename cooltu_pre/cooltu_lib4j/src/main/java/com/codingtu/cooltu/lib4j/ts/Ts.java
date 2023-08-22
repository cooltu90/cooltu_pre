package com.codingtu.cooltu.lib4j.ts;

import com.codingtu.cooltu.lib4j.data.bean.Symbol;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.MapTs;
import com.codingtu.cooltu.lib4j.ts.impl.SetTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.BooleanTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.ByteTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.CharTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.DoubleTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.FloatTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.IntTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.LongTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.ShortTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.StringArrayTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.TArrayTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.TListTs;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ts {

    public static BaseTs ts(Object obj) {
        Class aClass = obj.getClass();
        if (aClass.isArray()) {
            if (aClass == int[].class) {
                return ts((int[]) obj);
            } else if (aClass == byte[].class) {
                return ts((byte[]) obj);
            } else if (aClass == long[].class) {
                return ts((long[]) obj);
            } else if (aClass == char[].class) {
                return ts((char[]) obj);
            } else if (aClass == float[].class) {
                return ts((float[]) obj);
            } else if (aClass == double[].class) {
                return ts((double[]) obj);
            } else if (aClass == boolean[].class) {
                return ts((boolean[]) obj);
            } else if (aClass == short[].class) {
                return ts((short[]) obj);
            } else {
                return ts((Object[]) obj);
            }
        }
        return null;
    }

    public static <T> TListTs<T> ts(List<T> ts) {
        return new TListTs<>(ts);
    }

    public static <T> TArrayTs<T> ts(T... ts) {
        return new TArrayTs<>(ts);
    }

    public static StringArrayTs ts(String... ts) {
        return new StringArrayTs(ts);
    }

    public static ByteTs ts(byte... ts) {
        return new ByteTs(ts);
    }

    public static CharTs ts(char... ts) {
        return new CharTs(ts);
    }

    public static ShortTs ts(short... ts) {
        return new ShortTs(ts);
    }

    public static IntTs ts(int... ts) {
        return new IntTs(ts);
    }

    public static LongTs ts(long... ts) {
        return new LongTs(ts);
    }

    public static FloatTs ts(float... ts) {
        return new FloatTs(ts);
    }

    public static DoubleTs ts(double... ts) {
        return new DoubleTs(ts);
    }

    public static BooleanTs ts(boolean... ts) {
        return new BooleanTs(ts);
    }

    public static <T> SetTs<T> ts(Set<T> ts) {
        return new SetTs<>(ts);
    }

    public static <K, V> MapTs<K, V> ts(Map<K, V> ts) {
        return new MapTs<>(ts);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public static <T> void ls(List<T> ts, BaseTs.EachTs<T> eachTs) {
        ts(ts).ls(eachTs);
    }

    public static <T> void ls(T[] ts, BaseTs.EachTs<T> eachTs) {
        ts(ts).ls(eachTs);
    }

    public static <T> void ls(BaseTs.EachTs<T> eachTs, T... ts) {
        ts(ts).ls(eachTs);
    }


    /**************************************************
     *
     *
     *
     **************************************************/

    public static <T extends Symbol> T get(T target, List<T> ts) {
        return ts(ts).symbol().get(target);
    }

    public static <T extends Symbol> T get(T target, T... ts) {
        return ts(ts).symbol().get(target);
    }

    public static <T extends Symbol> T get(String symbol, List<T> ts) {
        return ts(ts).symbol().get(symbol);
    }

    public static <T extends Symbol> T get(String symbol, T... ts) {
        return ts(ts).symbol().get(symbol);
    }


    /**************************************************
     *
     *
     *
     **************************************************/

    public static <T extends Symbol> boolean has(T target, List<T> ts) {
        return ts(ts).symbol().has(target);
    }

    public static <T extends Symbol> boolean has(T target, T... ts) {
        return ts(ts).symbol().has(target);
    }

    public static <T extends Symbol> boolean has(String symbol, List<T> ts) {
        return ts(ts).symbol().has(symbol);
    }

    public static <T extends Symbol> boolean has(String symbol, T... ts) {
        return ts(ts).symbol().has(symbol);
    }


    /**************************************************
     *
     *
     *
     **************************************************/
    public static <T extends Symbol> int index(T target, List<T> ts) {
        return ts(ts).symbol().index(target);
    }

    public static <T extends Symbol> int index(T target, T... ts) {
        return ts(ts).symbol().index(target);
    }

    public static <T extends Symbol> int index(String symbol, List<T> ts) {
        return ts(ts).symbol().index(symbol);
    }

    public static <T extends Symbol> int index(String symbol, T... ts) {
        return ts(ts).symbol().index(symbol);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public static <T extends Symbol> void replace(T target, List<T> users) {
        ts(users).symbol().replace(target);
    }

    public static <T extends Symbol> void replace(T target, T... users) {
        ts(users).symbol().replace(target);
    }

    public static <T extends Symbol> void replaceOrAdd(T target, List<T> users) {
        ts(users).symbol().replaceOrAdd(target);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public static <T> T[] toArray(List<T> ts) {
        return ts(ts).toArray().get();
    }

    public static <T> List<T> toList(T... ts) {
        return ts(ts).toList().get();
    }

}
