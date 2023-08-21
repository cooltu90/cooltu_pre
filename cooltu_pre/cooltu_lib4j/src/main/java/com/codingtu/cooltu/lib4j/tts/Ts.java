package com.codingtu.cooltu.lib4j.tts;

import com.codingtu.cooltu.lib4j.data.bean.Symbol;
import com.codingtu.cooltu.lib4j.data.bean.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tts.ts.ArrayTs;
import com.codingtu.cooltu.lib4j.tts.ts.BooleanTs;
import com.codingtu.cooltu.lib4j.tts.ts.ByteTs;
import com.codingtu.cooltu.lib4j.tts.ts.CharTs;
import com.codingtu.cooltu.lib4j.tts.ts.DoubleTs;
import com.codingtu.cooltu.lib4j.tts.ts.FloatTs;
import com.codingtu.cooltu.lib4j.tts.ts.IntTs;
import com.codingtu.cooltu.lib4j.tts.ts.ListSymbolTs;
import com.codingtu.cooltu.lib4j.tts.ts.ListTs;
import com.codingtu.cooltu.lib4j.tts.ts.LongTs;
import com.codingtu.cooltu.lib4j.tts.ts.ShortTs;
import com.codingtu.cooltu.lib4j.tts.ts.SymbolTs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Ts<T> {

    private int step = 1;

    public abstract T get(int position);

    public abstract int count();

    public abstract void set(int index, T target);

    public static <T extends Symbol> ListSymbolTs<T> tsSymbol(List<T> ts) {
        return new ListSymbolTs<>(ts(ts));
    }

    public static <T extends Symbol> SymbolTs<T> tsSymbol(T... ts) {
        return new SymbolTs<T>(ts(ts));
    }

    public static <T> ListTs<T> ts(List<T> ts) {
        return new ListTs(ts);
    }


    public static <T> Ts<T> ts(T... ts) {
        return new ArrayTs(ts);
    }

    public static Ts<Byte> ts(byte... ts) {
        return new ByteTs(ts);
    }

    public static Ts<Short> ts(short... ts) {
        return new ShortTs(ts);
    }

    public static Ts<Character> ts(char... ts) {
        return new CharTs(ts);
    }

    public static Ts<Integer> ts(int... ts) {
        return new IntTs(ts);
    }

    public static Ts<Long> ts(long... ts) {
        return new LongTs(ts);
    }

    public static Ts<Float> ts(float... ts) {
        return new FloatTs(ts);
    }

    public static Ts<Double> ts(double... ts) {
        return new DoubleTs(ts);
    }

    public static Ts<Boolean> ts(boolean... ts) {
        return new BooleanTs(ts);
    }

    /**************************************************
     *
     * 遍历方法
     *
     **************************************************/

    public interface Each<T> {
        boolean each(int position, T t);
    }

    public Ts<T> step(int step) {
        this.step = step;
        return this;
    }

    public void ls(Each<T> each) {
        if (each == null) {
            return;
        }

        int count = count();
        for (int i = 0; i < count; i += step) {
            if (each.each(i, get(i))) {
                return;
            }
        }
    }


    public void rls(Each<T> each) {
        if (each == null) {
            return;
        }

        int count = count();
        for (int i = count - 1; i >= 0; i -= step) {
            if (each.each(i, get(i))) {
                return;
            }
        }
    }


    /**************************************************
     *
     *
     *
     **************************************************/

    public interface Filter<T> {
        boolean get(int position, T t);
    }

    public T get(Filter<T> filter) {
        if (filter == null)
            return null;

        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = get(i);
            if (filter.get(i, t)) {
                return t;
            }
        }
        return null;
    }

    /**************************************************
     *
     * has
     *
     **************************************************/
    public boolean has(Filter<T> filter) {
        return get(filter) != null;
    }

    /**************************************************
     *
     * index
     *
     **************************************************/
    public int index(Filter<T> filter) {
        if (filter == null)
            return -1;
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = get(i);
            if (filter.get(i, t)) {
                return i;
            }
        }
        return -1;
    }

    /**************************************************
     *
     * replace
     *
     **************************************************/
    public void replace(T target, Filter<T> filter) {
        if (target == null || filter == null)
            return;

        int index = index(filter);
        if (index >= 0) {
            set(index, target);
        }
    }

    /**************************************************
     *
     * convert
     *
     **************************************************/

    public interface Convert<S, T> {
        T convert(S s);
    }

    public <S> ListTs<S> convert(Convert<T, S> convert) {
        List<S> list = new ArrayList<>();

        if (convert == null) {
            return null;
        }

        ls(new Each<T>() {
            @Override
            public boolean each(int position, T t) {
                S s = convert.convert(t);
                if (s != null) {
                    list.add(s);
                }
                return false;
            }
        });

        return Ts.ts(list);
    }

    public List<T> toList() {
        ArrayList<T> list = new ArrayList<>();
        int count = count();
        for (int i = 0; i < count; i++) {
            list.add(get(i));
        }
        return list;
    }

    public T[] toArray() {
        int count = count();
        if (count <= 0) {
            return null;
        }

        T[] newArray = (T[]) java.lang.reflect.Array.newInstance
                (get(0).getClass(), count);
        for (int i = 0; i < count; i++) {
            newArray[i] = get(i);
        }
        return newArray;
    }

    public void clear() {
        int count = count();
        for (int i = 0; i < count; i++) {
            set(i, null);
        }
    }

    public T last() {
        int count = count();
        return count > 0 ? get(count - 1) : null;
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public interface GroupSortGetter<T> {
        String getGroup(int level, T t);

        int getLevels();

        int compare(T o1, T o2);

    }

    private String getRootGroupKey() {
        return "root";
    }

    private void groupSort(List<T> container, int levels, int level, ListValueMap<String, String> categorgMap, Map<String, T> tMap, String key) {
        Ts.ts(categorgMap.get(key)).ls(new Each<String>() {
            @Override
            public boolean each(int position, String s) {
                if (level < levels - 1) {
                    groupSort(container, levels, level + 1, categorgMap, tMap, (getRootGroupKey().equals(key) ? "" : key) + s);
                } else {
                    container.add(tMap.get(s));
                }
                return false;
            }
        });
    }

    public List<T> groupSort(GroupSortGetter<T> getter) {
        ListValueMap<String, String> totalMap = new ListValueMap<>();
        Map<String, T> tMap = new HashMap<>();

        List<T> ts = toList();

        Collections.sort(ts, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return getter.compare(o1, o2);
            }
        });

        Ts.ts(ts).ls(new Each<T>() {
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

    /**************************************************
     *
     * findFinal
     *
     **************************************************/

    public interface FinalGetter<T> {
        boolean isNow(T last, T now);
    }


    public T findFinal(FinalGetter<T> finalGetter) {
        if (finalGetter == null)
            return null;

        int count = count();
        T last = null;
        for (int i = 0; i < count; i++) {
            T now = get(i);
            last = last == null ? now : (finalGetter.isNow(last, now) ? now : last);
        }
        return last;
    }

    /**************************************************
     *
     * MaxMin
     *
     **************************************************/

    public interface NowMax<T> {
        boolean isNowMax(T last, T now);
    }

    public MaxMin<T> maxMin(NowMax<T> nowMax) {
        if (nowMax == null)
            return null;

        int count = count();
        MaxMin<T> maxMin = null;
        for (int i = 0; i < count; i++) {
            T now = get(i);
            if (i == 0) {
                maxMin = new MaxMin<>();
                maxMin.max = now;
                maxMin.min = now;
            } else {
                maxMin.max = nowMax.isNowMax(maxMin.max, now) ? now : maxMin.max;
                maxMin.min = nowMax.isNowMax(maxMin.min, now) ? maxMin.min : now;
            }
        }

        return maxMin;
    }
}
