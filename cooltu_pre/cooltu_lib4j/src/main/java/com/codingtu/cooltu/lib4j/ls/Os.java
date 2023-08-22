package com.codingtu.cooltu.lib4j.ls;

import com.codingtu.cooltu.lib4j.data.bean.Symbol;
import com.codingtu.cooltu.lib4j.ls.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.BooleanTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.ByteTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.CharTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.DoubleTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.FloatTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.IntTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.LongTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.ShortTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.TArrayTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.TListTs;

import java.util.List;

public class Os {
    public static <T> TListTs<T> os(List<T> ts) {
        return new TListTs<>(ts);
    }

    public static <T> TArrayTs<T> os(T... ts) {
        return new TArrayTs<>(ts);
    }

    public static ByteTs os(byte... ts) {
        return new ByteTs(ts);
    }

    public static CharTs os(char... ts) {
        return new CharTs(ts);
    }

    public static ShortTs os(short... ts) {
        return new ShortTs(ts);
    }

    public static IntTs os(int... ts) {
        return new IntTs(ts);
    }

    public static LongTs os(long... ts) {
        return new LongTs(ts);
    }

    public static FloatTs os(float... ts) {
        return new FloatTs(ts);
    }

    public static DoubleTs os(double... ts) {
        return new DoubleTs(ts);
    }

    public static BooleanTs os(boolean... ts) {
        return new BooleanTs(ts);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public static <T> void ls(List<T> ts, BaseTs.EachTs<T> eachTs) {
        os(ts).ls(eachTs);
    }

    public static <T> void ls(T[] ts, BaseTs.EachTs<T> eachTs) {
        os(ts).ls(eachTs);
    }

    public static <T> void ls(BaseTs.EachTs<T> eachTs, T... ts) {
        os(ts).ls(eachTs);
    }


    /**************************************************
     *
     *
     *
     **************************************************/

    public static <T extends Symbol> T get(T target, List<T> ts) {
        return os(ts).symbol().get(target);
    }

    public static <T extends Symbol> T get(T target, T... ts) {
        return os(ts).symbol().get(target);
    }

    public static <T extends Symbol> T get(String symbol, List<T> ts) {
        return os(ts).symbol().get(symbol);
    }

    public static <T extends Symbol> T get(String symbol, T... ts) {
        return os(ts).symbol().get(symbol);
    }


    /**************************************************
     *
     *
     *
     **************************************************/

    public static <T extends Symbol> boolean has(T target, List<T> ts) {
        return os(ts).symbol().has(target);
    }

    public static <T extends Symbol> boolean has(T target, T... ts) {
        return os(ts).symbol().has(target);
    }

    public static <T extends Symbol> boolean has(String symbol, List<T> ts) {
        return os(ts).symbol().has(symbol);
    }

    public static <T extends Symbol> boolean has(String symbol, T... ts) {
        return os(ts).symbol().has(symbol);
    }


    /**************************************************
     *
     *
     *
     **************************************************/
    public static <T extends Symbol> int index(T target, List<T> ts) {
        return os(ts).symbol().index(target);
    }

    public static <T extends Symbol> int index(T target, T... ts) {
        return os(ts).symbol().index(target);
    }

    public static <T extends Symbol> int index(String symbol, List<T> ts) {
        return os(ts).symbol().index(symbol);
    }

    public static <T extends Symbol> int index(String symbol, T... ts) {
        return os(ts).symbol().index(symbol);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public static <T extends Symbol> void replace(T target, List<T> users) {
        os(users).symbol().replace(target);
    }

    public static <T extends Symbol> void replace(T target, T... users) {
        os(users).symbol().replace(target);
    }

    public static <T extends Symbol> void replaceOrAdd(T target, List<T> users) {
        os(users).symbol().replaceOrAdd(target);
    }
}
