package com.codingtu.cooltu.lib4j.os;

import com.codingtu.cooltu.lib4j.data.bean.Symbol;
import com.codingtu.cooltu.lib4j.data.bean.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.os.impl.ArrayOs;
import com.codingtu.cooltu.lib4j.os.impl.BooleanOs;
import com.codingtu.cooltu.lib4j.os.impl.ByteOs;
import com.codingtu.cooltu.lib4j.os.impl.CharOs;
import com.codingtu.cooltu.lib4j.os.impl.DoubleOs;
import com.codingtu.cooltu.lib4j.os.impl.FloatOs;
import com.codingtu.cooltu.lib4j.os.impl.IntOs;
import com.codingtu.cooltu.lib4j.os.impl.ListSymbolOs;
import com.codingtu.cooltu.lib4j.os.impl.ListOs;
import com.codingtu.cooltu.lib4j.os.impl.LongOs;
import com.codingtu.cooltu.lib4j.os.impl.ShortOs;
import com.codingtu.cooltu.lib4j.os.impl.SymbolOs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Os<O> {

    private int step = 1;

    public abstract O get(int position);

    public abstract int count();

    public abstract void set(int index, O target);

    /**************************************************
     *
     *
     *
     **************************************************/

    public static <O extends Symbol> ListSymbolOs<O> tsSymbol(List<O> os) {
        return new ListSymbolOs<>(os(os));
    }

    public static <O extends Symbol> SymbolOs<O> tsSymbol(O... os) {
        return new SymbolOs<O>(os(os));
    }

    public static <T> ListOs<T> os(List<T> os) {
        return new ListOs(os);
    }


    public static <O> Os<O> os(O... os) {
        return new ArrayOs(os);
    }

    //byte short char int long float double boolean

    public static Os<Byte> os(byte... os) {
        return new ByteOs(os);
    }

    public static Os<Short> os(short... os) {
        return new ShortOs(os);
    }

    public static Os<Character> os(char... os) {
        return new CharOs(os);
    }

    public static Os<Integer> os(int... os) {
        return new IntOs(os);
    }

    public static Os<Long> os(long... os) {
        return new LongOs(os);
    }

    public static Os<Float> os(float... os) {
        return new FloatOs(os);
    }

    public static Os<Double> os(double... os) {
        return new DoubleOs(os);
    }

    public static Os<Boolean> os(boolean... os) {
        return new BooleanOs(os);
    }

    /**************************************************
     *
     * 遍历方法
     *
     **************************************************/

    public interface EachOs<O> {
        boolean each(int position, O o);
    }

    public Os<O> step(int step) {
        this.step = step;
        return this;
    }

    public void ls(EachOs<O> eachOs) {
        if (eachOs == null) {
            return;
        }

        int count = count();
        for (int i = 0; i < count; i += step) {
            if (eachOs.each(i, get(i))) {
                return;
            }
        }
    }


    public void rls(EachOs<O> eachOs) {
        if (eachOs == null) {
            return;
        }

        int count = count();
        for (int i = count - 1; i >= 0; i -= step) {
            if (eachOs.each(i, get(i))) {
                return;
            }
        }
    }


    /**************************************************
     *
     *
     *
     **************************************************/

    public interface Filter<O> {
        boolean get(int position, O o);
    }

    public O get(Filter<O> filter) {
        if (filter == null)
            return null;

        int count = count();
        O o = null;
        for (int i = 0; i < count; i++) {
            o = get(i);
            if (filter.get(i, o)) {
                return o;
            }
        }
        return null;
    }

    /**************************************************
     *
     * has
     *
     **************************************************/
    public boolean has(Filter<O> filter) {
        return get(filter) != null;
    }

    /**************************************************
     *
     * index
     *
     **************************************************/
    public int index(Filter<O> filter) {
        if (filter == null)
            return -1;
        int count = count();
        O o = null;
        for (int i = 0; i < count; i++) {
            o = get(i);
            if (filter.get(i, o)) {
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
    public void replace(O target, Filter<O> filter) {
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

    public <T> ListOs<T> convert(Convert<O, T> convert) {
        List<T> os = new ArrayList<>();

        if (convert == null) {
            return null;
        }

        ls(new EachOs<O>() {
            @Override
            public boolean each(int position, O o) {
                T t = convert.convert(o);
                if (t != null) {
                    os.add(t);
                }
                return false;
            }
        });

        return Os.os(os);
    }

    public List<O> toList() {
        ArrayList<O> list = new ArrayList<>();
        int count = count();
        for (int i = 0; i < count; i++) {
            list.add(get(i));
        }
        return list;
    }

    public O[] toArray() {
        int count = count();
        if (count <= 0) {
            return null;
        }

        O[] newArray = (O[]) java.lang.reflect.Array.newInstance
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

    public O last() {
        int count = count();
        return count > 0 ? get(count - 1) : null;
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public interface GroupSortGetter<O> {
        String getGroup(int level, O o);

        int getLevels();

        int compare(O o1, O o2);

    }

    private String getRootGroupKey() {
        return "root";
    }

    private void groupSort(List<O> container, int levels, int level, ListValueMap<String, String> categorgMap, Map<String, O> tMap, String key) {
        Os.os(categorgMap.get(key)).ls(new EachOs<String>() {
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

    public List<O> groupSort(GroupSortGetter<O> getter) {
        ListValueMap<String, String> totalMap = new ListValueMap<>();
        Map<String, O> tMap = new HashMap<>();

        List<O> os = toList();

        Collections.sort(os, new Comparator<O>() {
            @Override
            public int compare(O o1, O o2) {
                return getter.compare(o1, o2);
            }
        });

        Os.os(os).ls(new EachOs<O>() {
            @Override
            public boolean each(int i, O o) {
                tMap.put(getter.getGroup(getter.getLevels() - 1, o), o);

                String[] gs = new String[getter.getLevels()];
                for (int j = 0; j < gs.length; j++) {
                    gs[j] = getter.getGroup(j, o);
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

        List<O> as = new ArrayList<>();
        groupSort(as, getter.getLevels(), 0, totalMap, tMap, getRootGroupKey());
        return as;
    }

    /**************************************************
     *
     * findFinal
     *
     **************************************************/

    public interface FinalGetter<O> {
        boolean isNow(O last, O now);
    }


    public O findFinal(FinalGetter<O> finalGetter) {
        if (finalGetter == null)
            return null;

        int count = count();
        O last = null;
        for (int i = 0; i < count; i++) {
            O now = get(i);
            last = last == null ? now : (finalGetter.isNow(last, now) ? now : last);
        }
        return last;
    }

    /**************************************************
     *
     * MaxMin
     *
     **************************************************/

    public interface NowMax<O> {
        boolean isNowMax(O last, O now);
    }

    public MaxMin<O> maxMin(NowMax<O> nowMax) {
        if (nowMax == null)
            return null;

        int count = count();
        MaxMin<O> maxMin = null;
        for (int i = 0; i < count; i++) {
            O now = get(i);
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
