package cooltu.lib4j.ts;

import cooltu.lib4j.data.bean.Symbol;
import cooltu.lib4j.data.map.ListValueMap;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.tools.OtherTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.lib4j.ts.each.Each;
import cooltu.lib4j.ts.each.MapEach;
import cooltu.lib4j.ts.eachgetter.EachGetter;
import cooltu.lib4j.ts.getter.Getter;
import cooltu.lib4j.ts.getter.SameGetter;

import java.util.*;

public class Ts {

    /**************************************************
     *
     * tsGetter
     *
     **************************************************/
    public static EachGetter tsGetter(Object obj) {
        Class aClass = obj.getClass();
        if (aClass.isArray()) {
            if (aClass == int[].class) {
                return tsGetter((int[]) obj);
            } else if (aClass == byte[].class) {
                return tsGetter((byte[]) obj);
            } else if (aClass == long[].class) {
                return tsGetter((long[]) obj);
            } else if (aClass == char[].class) {
                return tsGetter((char[]) obj);
            } else if (aClass == float[].class) {
                return tsGetter((float[]) obj);
            } else if (aClass == double[].class) {
                return tsGetter((double[]) obj);
            } else if (aClass == boolean[].class) {
                return tsGetter((boolean[]) obj);
            } else if (aClass == short[].class) {
                return tsGetter((short[]) obj);
            } else {
                return tsGetter((Object[]) obj);
            }
        }
        return null;
    }

    public static <T> EachGetter<T> tsGetter(List<? extends T> ls) {
        return new EachGetter<T>() {
            @Override
            public T get(int position) {
                return ls.get(position);
            }

            @Override
            public int count() {
                return CountTool.count(ls);
            }
        };
    }

    public static <T> EachGetter<T> tsGetter(T... ls) {
        return new EachGetter<T>() {
            @Override
            public T get(int position) {
                return ls[position];
            }

            @Override
            public int count() {
                return CountTool.count(ls);
            }
        };
    }

    public static EachGetter<Integer> tsGetter(int... ls) {
        return new EachGetter<Integer>() {
            @Override
            public Integer get(int position) {
                return ls[position];
            }

            @Override
            public int count() {
                return CountTool.count(ls);
            }
        };
    }

    public static EachGetter<Boolean> tsGetter(boolean... ls) {
        return new EachGetter<Boolean>() {
            @Override
            public Boolean get(int position) {
                return ls[position];
            }

            @Override
            public int count() {
                return CountTool.count(ls);
            }
        };
    }

    public static EachGetter<Long> tsGetter(long... ls) {
        return new EachGetter<Long>() {
            @Override
            public Long get(int position) {
                return ls[position];
            }

            @Override
            public int count() {
                return CountTool.count(ls);
            }
        };
    }

    public static EachGetter<Byte> tsGetter(byte... ls) {
        return new EachGetter<Byte>() {
            @Override
            public Byte get(int position) {
                return ls[position];
            }

            @Override
            public int count() {
                return CountTool.count(ls);
            }
        };
    }

    public static EachGetter<Double> tsGetter(double... ls) {
        return new EachGetter<Double>() {
            @Override
            public Double get(int position) {
                return ls[position];
            }

            @Override
            public int count() {
                return CountTool.count(ls);
            }
        };
    }

    public static EachGetter<Float> tsGetter(float... ls) {
        return new EachGetter<Float>() {
            @Override
            public Float get(int position) {
                return ls[position];
            }

            @Override
            public int count() {
                return CountTool.count(ls);
            }
        };
    }

    public static EachGetter<Character> tsGetter(char... ls) {
        return new EachGetter<Character>() {
            @Override
            public Character get(int position) {
                return ls[position];
            }

            @Override
            public int count() {
                return CountTool.count(ls);
            }
        };
    }

    public static EachGetter<Short> tsGetter(short... ls) {
        return new EachGetter<Short>() {
            @Override
            public Short get(int position) {
                return ls[position];
            }

            @Override
            public int count() {
                return CountTool.count(ls);
            }
        };
    }

    /**************************************************
     *
     * 遍历
     *
     **************************************************/
    public static <T> void ls(EachGetter<T> eachGetter, int step, Each<T> each) {
        if (each == null || eachGetter == null)
            return;

        int count = eachGetter.count();
        for (int i = 0; i < count; i += step) {
            if (each.each(i, eachGetter.get(i))) {
                return;
            }
        }
    }

    public static <T> void ls(EachGetter<T> eachGetter, Each<T> each) {
        ls(eachGetter, 1, each);
    }

    public static <T> void rls(EachGetter<T> eachGetter, int step, Each<T> each) {
        if (each == null || eachGetter == null)
            return;

        int count = eachGetter.count();
        for (int i = count - 1; i >= 0; i -= step) {
            if (each.each(i, eachGetter.get(i))) {
                return;
            }
        }
    }

    public static <T> void rls(EachGetter<T> eachGetter, Each<T> each) {
        rls(eachGetter, 1, each);
    }

    // List遍历
    public static <T> void ls(List<? extends T> ts, int step, Each<T> each) {
        ls(tsGetter(ts), step, each);
    }

    public static <T> void ls(List<? extends T> ts, Each<T> each) {
        ls(tsGetter(ts), 1, each);
    }

    public static <T> void rls(List<? extends T> ts, int step, Each<T> each) {
        rls(tsGetter(ts), step, each);
    }

    public static <T> void rls(List<? extends T> ts, Each<T> each) {
        rls(tsGetter(ts), 1, each);
    }

    // Set遍历
    public static <T> void ls(Set<? extends T> ts, Each<T> each) {
        if (each == null)
            return;

        if (CountTool.count(ts) > 0) {
            int index = 0;
            Iterator<? extends T> iterator = ts.iterator();
            while (iterator.hasNext()) {
                if (each.each(index, iterator.next())) {
                    return;
                }
                index++;
            }
        }
    }

    // Map遍历
    public static <K, V> void ls(Map<K, V> map, MapEach<K, V> mapEach) {
        if (map == null || mapEach == null)
            return;

        ls(map.keySet(), new Each<K>() {
            public boolean each(int position, K k) {
                return mapEach.each(position, k, map.get(k));
            }
        });
    }

    // 数组遍历
    public static <T> void ls(T[] ts, int step, Each<T> each) {
        ls(tsGetter(ts), step, each);
    }

    public static <T> void ls(int step, Each<T> each, T... ts) {
        ls(tsGetter(ts), step, each);
    }

    public static <T> void ls(T[] ts, Each<T> each) {
        ls(tsGetter(ts), 1, each);
    }

    public static <T> void ls(Each<T> each, T... ts) {
        ls(tsGetter(ts), 1, each);
    }

    public static <T> void rls(T[] ts, int step, Each<T> each) {
        rls(tsGetter(ts), step, each);
    }

    public static <T> void rls(int step, Each<T> each, T... ts) {
        rls(tsGetter(ts), step, each);
    }

    public static <T> void rls(T[] ts, Each<T> each) {
        rls(tsGetter(ts), 1, each);
    }

    public static <T> void rls(Each<T> each, T... ts) {
        rls(tsGetter(ts), 1, each);
    }

    // int数组遍历
    public static void ls(int[] ts, Each<Integer> each) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(Each<Integer> each, int... ts) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(int[] ts, int step, Each<Integer> each) {
        ls(tsGetter(ts), step, each);
    }

    public static void ls(int step, Each<Integer> each, int... ts) {
        ls(tsGetter(ts), step, each);
    }

    public static void rls(int[] ts, Each<Integer> each) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(Each<Integer> each, int... ts) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(int[] ts, int step, Each<Integer> each) {
        rls(tsGetter(ts), step, each);
    }

    public static void rls(int step, Each<Integer> each, int... ts) {
        rls(tsGetter(ts), step, each);
    }

    // boolean数组遍历
    public static void ls(boolean[] ts, Each<Boolean> each) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(Each<Boolean> each, boolean... ts) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(boolean[] ts, int step, Each<Boolean> each) {
        ls(tsGetter(ts), step, each);
    }

    public static void ls(int step, Each<Boolean> each, boolean... ts) {
        ls(tsGetter(ts), step, each);
    }

    public static void rls(boolean[] ts, Each<Boolean> each) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(Each<Boolean> each, boolean... ts) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(boolean[] ts, int step, Each<Boolean> each) {
        rls(tsGetter(ts), step, each);
    }

    public static void rls(int step, Each<Boolean> each, boolean... ts) {
        rls(tsGetter(ts), step, each);
    }

    // double数组遍历
    public static void ls(double[] ts, Each<Double> each) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(Each<Double> each, double... ts) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(double[] ts, int step, Each<Double> each) {
        ls(tsGetter(ts), step, each);
    }

    public static void ls(int step, Each<Double> each, double... ts) {
        ls(tsGetter(ts), step, each);
    }

    public static void rls(double[] ts, Each<Double> each) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(Each<Double> each, double... ts) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(double[] ts, int step, Each<Double> each) {
        rls(tsGetter(ts), step, each);
    }

    public static void rls(int step, Each<Double> each, double... ts) {
        rls(tsGetter(ts), step, each);
    }


    // float数组遍历
    public static void ls(float[] ts, Each<Float> each) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(Each<Float> each, float... ts) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(float[] ts, int step, Each<Float> each) {
        ls(tsGetter(ts), step, each);
    }

    public static void ls(int step, Each<Float> each, float... ts) {
        ls(tsGetter(ts), step, each);
    }

    public static void rls(float[] ts, Each<Float> each) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(Each<Float> each, float... ts) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(float[] ts, int step, Each<Float> each) {
        rls(tsGetter(ts), step, each);
    }

    public static void rls(int step, Each<Float> each, float... ts) {
        rls(tsGetter(ts), step, each);
    }

    // char数组遍历
    public static void ls(char[] ts, Each<Character> each) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(Each<Character> each, char... ts) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(char[] ts, int step, Each<Character> each) {
        ls(tsGetter(ts), step, each);
    }

    public static void ls(int step, Each<Character> each, char... ts) {
        ls(tsGetter(ts), step, each);
    }

    public static void rls(char[] ts, Each<Character> each) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(Each<Character> each, char... ts) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(char[] ts, int step, Each<Character> each) {
        rls(tsGetter(ts), step, each);
    }

    public static void rls(int step, Each<Character> each, char... ts) {
        rls(tsGetter(ts), step, each);
    }

    // long数组遍历
    public static void ls(long[] ts, Each<Long> each) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(Each<Long> each, long... ts) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(long[] ts, int step, Each<Long> each) {
        ls(tsGetter(ts), step, each);
    }

    public static void ls(int step, Each<Long> each, long... ts) {
        ls(tsGetter(ts), step, each);
    }

    public static void rls(long[] ts, Each<Long> each) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(Each<Long> each, long... ts) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(long[] ts, int step, Each<Long> each) {
        rls(tsGetter(ts), step, each);
    }

    public static void rls(int step, Each<Long> each, long... ts) {
        rls(tsGetter(ts), step, each);
    }

    // byte数组遍历
    public static void ls(byte[] ts, Each<Byte> each) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(Each<Byte> each, byte... ts) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(byte[] ts, int step, Each<Byte> each) {
        ls(tsGetter(ts), step, each);
    }

    public static void ls(int step, Each<Byte> each, byte... ts) {
        ls(tsGetter(ts), step, each);
    }

    public static void rls(byte[] ts, Each<Byte> each) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(Each<Byte> each, byte... ts) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(byte[] ts, int step, Each<Byte> each) {
        rls(tsGetter(ts), step, each);
    }

    public static void rls(int step, Each<Byte> each, byte... ts) {
        rls(tsGetter(ts), step, each);
    }

    // short数组遍历
    public static void ls(short[] ts, Each<Short> each) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(Each<Short> each, short... ts) {
        ls(tsGetter(ts), 1, each);
    }

    public static void ls(short[] ts, int step, Each<Short> each) {
        ls(tsGetter(ts), step, each);
    }

    public static void ls(int step, Each<Short> each, short... ts) {
        ls(tsGetter(ts), step, each);
    }

    public static void rls(short[] ts, Each<Short> each) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(Each<Short> each, short... ts) {
        rls(tsGetter(ts), 1, each);
    }

    public static void rls(short[] ts, int step, Each<Short> each) {
        rls(tsGetter(ts), step, each);
    }

    public static void rls(int step, Each<Short> each, short... ts) {
        rls(tsGetter(ts), step, each);
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public static <T extends Symbol> SameGetter<T, T> symbolSameGetter(T target) {
        if (target == null)
            return null;

        return new SameGetter<T, T>(target) {
            @Override
            public boolean same(Integer index, T t, T target) {
                return t.obtainSymbol().equals(target.obtainSymbol());
            }
        };
    }

    public static <T extends Symbol> SameGetter<String, T> stringSymbolSameGetter(String symbol) {
        if (symbol == null)
            return null;
        return new SameGetter<String, T>(symbol) {

            @Override
            public boolean same(Integer index, T t, String symbol) {
                return t.obtainSymbol().equals(symbol);
            }
        };
    }

    public static SameGetter<String, String> stringSameGetter(String str) {
        if (str == null)
            return null;

        return new SameGetter<String, String>(str) {

            @Override
            public boolean same(Integer index, String s, String target) {
                return s.equals(target);
            }
        };
    }

    /***********************************
     *
     * 获取符合条件的元素
     *
     ***********************************/
    public static <K, V> V get(Map<K, V> map, Getter<K, V> getter) {
        if (getter == null || map == null)
            return null;

        Set<K> ks = map.keySet();
        for (K k : ks) {
            V v = map.get(k);
            if (getter.get(k, v)) {
                return v;
            }
        }
        return null;
    }

    public static <T> T get(EachGetter<T> eachGetter, Getter<Integer, T> getter) {
        if (eachGetter == null || getter == null)
            return null;

        int count = eachGetter.count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = eachGetter.get(i);
            if (getter.get(i, t)) {
                return t;
            }
        }
        return null;
    }

    public static <T> T get(T[] ts, Getter<Integer, T> getter) {
        return get(tsGetter(ts), getter);
    }

    public static <T> T get(Getter<Integer, T> getter, T... ts) {
        return get(tsGetter(ts), getter);
    }

    public static <T> T get(List<? extends T> ts, Getter<Integer, T> getter) {
        return get(tsGetter(ts), getter);
    }

    public static <T> T get(Set<? extends T> ts, Getter<Integer, T> getter) {
        if (getter == null || CountTool.count(ts) <= 0) {
            return null;
        }
        Iterator<? extends T> iterator = ts.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (getter.get(index, next)) {
                return next;
            }
            index++;
        }
        return null;
    }

    public static String get(String symbol, String... strs) {
        return get(tsGetter(strs), stringSameGetter(symbol));
    }

    public static <T extends Symbol> T get(String symbol, T... ts) {
        return get(tsGetter(ts), stringSymbolSameGetter(symbol));
    }

    public static <T extends Symbol> T get(T target, T... ts) {
        return get(tsGetter(ts), symbolSameGetter(target));
    }

    public static <T extends Symbol> T get(String symbol, List<? extends T> ts) {
        return get(tsGetter(ts), stringSymbolSameGetter(symbol));
    }

    public static <T extends Symbol> T get(T target, List<? extends T> ts) {
        return get(tsGetter(ts), symbolSameGetter(target));
    }

    public static <T extends Symbol> T get(String symbol, Set<? extends T> ts) {
        return get(ts, stringSymbolSameGetter(symbol));
    }

    public static <T extends Symbol> T get(T target, Set<? extends T> ts) {
        return get(ts, symbolSameGetter(target));
    }

    /**************************************************
     *
     * has
     *
     **************************************************/
    public static <K, V> boolean has(Map<K, V> map, Getter<K, V> getter) {
        return get(map, getter) != null;
    }

    public static <T> boolean has(EachGetter<T> eachGetter, Getter<Integer, T> getter) {
        return get(eachGetter, getter) != null;
    }

    public static <T> boolean has(T[] ts, Getter<Integer, T> getter) {
        return get(ts, getter) != null;
    }

    public static <T> boolean has(Getter<Integer, T> getter, T... ts) {
        return get(getter, ts) != null;
    }

    public static <T> boolean has(List<? extends T> ts, Getter<Integer, T> getter) {
        return get(ts, getter) != null;
    }

    public static <T> boolean has(Set<? extends T> ts, Getter<Integer, T> getter) {
        return get(ts, getter) != null;
    }

    public static boolean has(String symbol, String... strs) {
        return get(symbol, strs) != null;
    }

    public static <T extends Symbol> boolean has(String symbol, T... ts) {
        return get(symbol, ts) != null;
    }

    public static <T extends Symbol> boolean has(T target, T... ts) {
        return get(target, ts) != null;
    }

    public static <T extends Symbol> boolean has(String symbol, List<? extends T> ts) {
        return get(symbol, ts) != null;
    }

    public static <T extends Symbol> boolean has(T target, List<? extends T> ts) {
        return get(target, ts) != null;
    }

    public static <T extends Symbol> boolean has(String symbol, Set<? extends T> ts) {
        return get(symbol, ts) != null;
    }

    public static <T extends Symbol> boolean has(T target, Set<? extends T> ts) {
        return get(target, ts) != null;
    }

    /**************************************************
     *
     * index
     *
     **************************************************/
    public static <T> int index(EachGetter<T> eachGetter, Getter<Integer, T> getter) {
        if (eachGetter == null || getter == null)
            return -1;
        int count = eachGetter.count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = eachGetter.get(i);
            if (getter.get(i, t)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int index(Getter<Integer, T> getter, T... ts) {
        return index(tsGetter(ts), getter);
    }

    public static <T> int index(T[] ts, Getter<Integer, T> getter) {
        return index(tsGetter(ts), getter);
    }

    public static <T> int index(List<? extends T> ts, Getter<Integer, T> getter) {
        return index(tsGetter(ts), getter);
    }

    public static int index(String symbol, String... strs) {
        return index(tsGetter(strs), stringSameGetter(symbol));
    }

    public static <T extends Symbol> int index(String symbol, T... ts) {
        return index(tsGetter(ts), stringSymbolSameGetter(symbol));
    }

    public static <T extends Symbol> int index(T target, T... ts) {
        return index(tsGetter(ts), symbolSameGetter(target));
    }


    public static <T extends Symbol> int index(String symbol, List<? extends T> ts) {
        return index(tsGetter(ts), stringSymbolSameGetter(symbol));
    }

    public static <T extends Symbol> int index(T target, List<? extends T> ts) {
        return index(tsGetter(ts), symbolSameGetter(target));
    }

    /**************************************************
     *
     * replace
     *
     **************************************************/
    public static <T> void replace(List<T> ts, SameGetter<T, T> getter) {
        int index = index(ts, getter);
        if (index >= 0) {
            ts.set(index, getter.target);
        }
    }


    public static <T> void replace(T[] ts, SameGetter<T, T> getter) {
        int index = index(ts, getter);
        if (index >= 0) {
            ts[index] = getter.target;
        }
    }

    public static <T> void replace(SameGetter<T, T> getter, T... ts) {
        replace(ts, getter);
    }

    public static <T extends Symbol> void replace(T target, List<T> ts) {
        replace(ts, symbolSameGetter(target));
    }

    public static <T extends Symbol> void replace(T target, T... ts) {
        replace(ts, symbolSameGetter(target));
    }

    public static <T> void replaceOrAdd(List<T> ts, SameGetter<T, T> getter) {
        int index = index(ts, getter);
        if (index >= 0) {
            ts.set(index, getter.target);
        } else {
            ts.add(getter.target);
        }
    }

    public static <T extends Symbol> void replaceOrAdd(T target, List<T> ts) {
        replaceOrAdd(ts, symbolSameGetter(target));
    }


    /**************************************************
     *
     * delete
     *
     **************************************************/
    public static <S, T> void delete(List<? extends T> ts, SameGetter<S, T> getter) {
        int index = index(ts, getter);
        if (index >= 0) {
            ts.remove(index);
        }
    }

    public static <T extends Symbol> void delete(T target, List<? extends T> ts) {
        delete(ts, symbolSameGetter(target));
    }

    public static <T extends Symbol> void delete(String symbol, List<? extends T> ts) {
        delete(ts, stringSymbolSameGetter(symbol));
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public static <T> List<T> toList(T... ts) {
        ArrayList<T> ls = new ArrayList<>();
        for (int i = 0; i < CountTool.count(ts); i++) {
            ls.add(ts[i]);
        }
        return ls;
    }

    public static <T> T[] toArray(List<T> lines) {
        int count = CountTool.count(lines);
        if (count <= 0) {
            return null;
        }
        T[] newArray = (T[]) java.lang.reflect.Array.newInstance
                (lines.get(0).getClass(), count);
        return lines.toArray(newArray);
    }

    public static <T> List<T> getList(EachGetter<T> getter) {
        ArrayList<T> ts = new ArrayList<T>();
        int count = getter.count();
        for (int i = 0; i < count; i++) {
            ts.add(getter.get(i));
        }
        return ts;
    }

    public static <T> T[] getArray(EachGetter<T> getter) {
        int count = getter.count();
        T t = getter.get(0);
        T[] newArray = (T[]) java.lang.reflect.Array.newInstance
                (t.getClass(), count);
        newArray[0] = t;
        if (count > 1) {
            for (int i = 1; i < count; i++) {
                newArray[i] = getter.get(i);
            }
        }
        return newArray;
    }

    public static List<Boolean> getBooleans(int size) {
        return getList(new EachGetter<Boolean>() {
            @Override
            public Boolean get(int position) {
                return false;
            }

            @Override
            public int count() {
                return size;
            }
        });
    }

    public static <T> T[] objsToArrays(Object[] objs) {
        int count = CountTool.count(objs);
        if (count > 0) {
            T[] newArray = (T[]) java.lang.reflect.Array.newInstance
                    (objs[0].getClass(), count);
            for (int i = 0; i < count; i++) {
                newArray[i] = (T) objs[i];
            }
            return newArray;
        }
        return null;
    }

    public static <T> void clear(T[] ets) {
        if (ets != null) {
            for (int i = 0; i < CountTool.count(ets); i++) {
                ets[i] = null;
            }
        }
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public static <E> E getT(Inject<E> tt) {
        E t = tt.newT();
        tt.inject(t);
        return t;
    }

    public static <E> List<E> getTs(int size, Inject<E> tt) {
        ArrayList<E> ts = new ArrayList<E>();
        for (int i = 0; i < size; i++) {
            ts.add(getT(tt));
        }
        return ts;
    }

    public static abstract class Inject<E> {
        E newT() {
            try {
                return ((Class<E>) OtherTool.getParameterizedType(this, 0)).newInstance();
            } catch (Exception e) {
                return null;
            }
        }

        public abstract void inject(E t);
    }

    /**************************************************
     *
     * 获取List的最后一个元素
     *
     **************************************************/
    public static <E> E last(List<E> list) {
        int count = CountTool.count(list);
        return count > 0 ? list.get(count - 1) : null;
    }

    /**************************************************
     *
     * 对List中添加元素
     *
     **************************************************/
    public static <E> List<E> add(List<E> list, E e) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(e);
        return list;
    }

    /**************************************************
     *
     * 转换
     *
     **************************************************/
    public static interface Convert<S, T> {
        T convert(S s);
    }

    public static <T, S> List<T> convert(EachGetter<S> getter, Convert<S, T> convert) {
        ArrayList<T> ts = new ArrayList<>();
        ls(getter, new Each<S>() {
            @Override
            public boolean each(int position, S s) {
                T t = convert.convert(s);
                if (t != null) {
                    ts.add(t);
                }
                return false;
            }
        });
        return ts;
    }

    public static <T, S> List<T> convert(List<S> ss, Convert<S, T> convert) {
        return convert(tsGetter(ss), convert);
    }

    public static <T, S> List<T> convert(S[] ss, Convert<S, T> convert) {
        return convert(tsGetter(ss), convert);
    }

    public static <T, S> List<T> convert(Convert<S, T> convert, S... ss) {
        return convert(tsGetter(ss), convert);
    }

    public static <T> List<T> convert(Convert<Integer, T> convert, int... ss) {
        return convert(tsGetter(ss), convert);
    }

    public static <T> List<T> convert(int[] ss, Convert<Integer, T> convert) {
        return convert(tsGetter(ss), convert);
    }


    /**************************************************
     *
     * 分组排序
     *
     **************************************************/
    public static <T> List<T> groupSort(T[] ts, GroupSortGetter<T> getter) {
        return groupSort(toList(ts), getter);
    }

    public static <T> List<T> groupSort(List<T> ts, GroupSortGetter<T> getter) {
        ListValueMap<String, String> totalMap = new ListValueMap<>();
        Map<String, T> tMap = new HashMap<>();

        Collections.sort(ts, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return getter.compare(o1, o2);
            }
        });

        Ts.ls(ts, new Each<T>() {
            @Override
            public boolean each(int i, T t) {
                tMap.put(getter.getGroup(getter.getLevels() - 1, t), t);

                String[] gs = new String[getter.getLevels()];
                for (int j = 0; j < gs.length; j++) {
                    gs[j] = getter.getGroup(j, t);
                }

                List<String> list = totalMap.get(getRootGroupKey());

                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < gs.length; j++) {
                    if (j < gs.length - 1) {
                        sb.append(gs[j]);
                        List<String> subList = totalMap.get(sb.toString());
                        if (CountTool.isNull(subList)) {
                            list.add(gs[j]);
                        }
                        list = subList;
                    } else {
                        list.add(gs[j]);
                    }

                }
                return false;
            }
        });

        List<T> as = new ArrayList<>();
        groupSort(as, getter.getLevels(), 0, totalMap, tMap, getRootGroupKey());
        return as;
    }

    private static <T> void groupSort(List<T> container, int levels, int level, ListValueMap<String, String> categorgMap, Map<String, T> tMap, String key) {
        Ts.ls(categorgMap.get(key), new Each<String>() {
            @Override
            public boolean each(int i, String s) {
                if (level < levels - 1) {
                    groupSort(container, levels, level + 1, categorgMap, tMap, (getRootGroupKey().equals(key) ? "" : key) + s);
                } else {
                    container.add(tMap.get(s));
                }
                return false;
            }
        });
    }

    private static String getRootGroupKey() {
        return "root";
    }

    public static interface GroupSortGetter<T> {
        String getGroup(int level, T t);

        int getLevels();

        int compare(T o1, T o2);

    }

}