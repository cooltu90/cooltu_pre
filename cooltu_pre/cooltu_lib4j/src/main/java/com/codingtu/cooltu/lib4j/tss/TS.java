package com.codingtu.cooltu.lib4j.tss;

import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tools.CountTool;

import java.util.List;

public class TS<T> {

    /**************************************************
     *
     * 所有getter方法
     *
     **************************************************/

    public interface Getter<T> {
        public T get(int position);

        public int count();
    }

    private Getter<T> getter;

    public static <T> Getter<T> getter(List<T> ts) {
        if (CountTool.isNull(ts))
            return null;
        return new Getter<T>() {
            @Override
            public T get(int position) {
                return ts.get(position);
            }

            @Override
            public int count() {
                return CountTool.count(ts);
            }
        };
    }

    public static <T> Getter<T> getter(T... ts) {
        if (CountTool.isNull(ts))
            return null;
        return new Getter<T>() {
            @Override
            public T get(int position) {
                return ts[position];
            }

            @Override
            public int count() {
                return CountTool.count(ts);
            }
        };
    }

    public static Getter<Byte> getter(byte... ts) {
        if (CountTool.isNull(ts))
            return null;
        return new Getter<Byte>() {
            @Override
            public Byte get(int position) {
                return ts[position];
            }

            @Override
            public int count() {
                return CountTool.count(ts);
            }
        };
    }

    public static Getter<Short> getter(short... ts) {
        if (CountTool.isNull(ts))
            return null;
        return new Getter<Short>() {
            @Override
            public Short get(int position) {
                return ts[position];
            }

            @Override
            public int count() {
                return CountTool.count(ts);
            }
        };
    }

    public static Getter<Character> getter(char... ts) {
        if (CountTool.isNull(ts))
            return null;
        return new Getter<Character>() {
            @Override
            public Character get(int position) {
                return ts[position];
            }

            @Override
            public int count() {
                return CountTool.count(ts);
            }
        };
    }

    public static Getter<Integer> getter(int... ts) {
        if (CountTool.isNull(ts))
            return null;
        return new Getter<Integer>() {
            @Override
            public Integer get(int position) {
                return ts[position];
            }

            @Override
            public int count() {
                return CountTool.count(ts);
            }
        };
    }

    public static Getter<Long> getter(long... ts) {
        if (CountTool.isNull(ts))
            return null;
        return new Getter<Long>() {
            @Override
            public Long get(int position) {
                return ts[position];
            }

            @Override
            public int count() {
                return CountTool.count(ts);
            }
        };
    }

    public static Getter<Float> getter(float... ts) {
        if (CountTool.isNull(ts))
            return null;
        return new Getter<Float>() {
            @Override
            public Float get(int position) {
                return ts[position];
            }

            @Override
            public int count() {
                return CountTool.count(ts);
            }
        };
    }

    public static Getter<Double> getter(double... ts) {
        if (CountTool.isNull(ts))
            return null;
        return new Getter<Double>() {
            @Override
            public Double get(int position) {
                return ts[position];
            }

            @Override
            public int count() {
                return CountTool.count(ts);
            }
        };
    }

    public static Getter<Boolean> getter(boolean... ts) {
        if (CountTool.isNull(ts))
            return null;
        return new Getter<Boolean>() {
            @Override
            public Boolean get(int position) {
                return ts[position];
            }

            @Override
            public int count() {
                return CountTool.count(ts);
            }
        };
    }

    /**************************************************
     *
     * 静态获取方法
     *
     **************************************************/
    private static <T> TS<T> ts(Getter<T> getter) {
        TS<T> tls = new TS<>();
        tls.getter = getter;
        tls.step = 1;
        return tls;
    }

    public static <T> TS<T> ts(List<T> ts) {
        return ts(getter(ts));
    }

    public static <T> TS<T> ts(T... ts) {
        return ts(getter(ts));
    }

    public static TS<Byte> ts(byte... ts) {
        return ts(getter(ts));
    }

    public static TS<Short> ts(short... ts) {
        return ts(getter(ts));
    }

    public static TS<Character> ts(char... ts) {
        return ts(getter(ts));
    }

    public static TS<Integer> ts(int... ts) {
        return ts(getter(ts));
    }

    public static TS<Long> ts(long... ts) {
        return ts(getter(ts));
    }

    public static TS<Float> ts(float... ts) {
        return ts(getter(ts));
    }

    public static TS<Double> ts(double... ts) {
        return ts(getter(ts));
    }

    public static TS<Boolean> ts(boolean... ts) {
        return ts(getter(ts));
    }

    /**************************************************
     *
     * 设置遍历的步数
     *
     **************************************************/

    private int step;

    public TS<T> step(int step) {
        this.step = step;
        return this;
    }


    /**************************************************
     *
     * 遍历接口
     *
     **************************************************/
    public interface Each<T> {
        public boolean each(int position, T t);
    }

    /**************************************************
     *
     * 正向遍历
     *
     **************************************************/

    public void ls(Each<T> each) {
        if (getter == null || each == null) {
            return;
        }

        int count = getter.count();
        for (int i = 0; i < count; i += step) {
            if (each.each(i, getter.get(i))) {
                return;
            }
        }
    }

    /**************************************************
     *
     * 反向遍历
     *
     **************************************************/
    public void rls(Each<T> each) {
        if (getter == null || each == null) {
            return;
        }

        int count = getter.count();
        for (int i = count - 1; i >= 0; i -= step) {
            if (each.each(i, getter.get(i))) {
                return;
            }
        }
    }

    /**************************************************
     *
     * log
     *
     **************************************************/
    public TS<T> log() {
        LibLogs.i(getter);
        return this;
    }

    /**************************************************
     *
     * 获取方法
     *
     **************************************************/

    public interface GetFilter<T> {
        public boolean get(int position, T t);
    }

    public T get(GetFilter<T> filter) {
        if (getter == null || filter == null)
            return null;

        int count = getter.count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = getter.get(i);
            if (filter.get(i, t)) {
                return t;
            }
        }
        return null;
    }

    /**************************************************
     *
     * 判断是否有
     *
     **************************************************/
    public boolean has(GetFilter<T> filter) {
        return get(filter) != null;
    }

    /**************************************************
     *
     * 获取下标
     *
     **************************************************/

    public int index(GetFilter<T> filter) {
        if (getter == null || filter == null)
            return -1;
        int count = getter.count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = getter.get(i);
            if (filter.get(i, t)) {
                return i;
            }
        }
        return -1;
    }

}
