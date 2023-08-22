package com.codingtu.cooltu.lib4j.ts.impl.basic;

import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.ListSymbolTs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TListTs<T> extends BaseTs<T> {

    private List<T> ts;

    public TListTs(List<T> ts) {
        this.ts = ts;
    }

    @Override
    public T get(int position) {
        return ts.get(position);
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, T target) {
        ts.set(index, target);
    }

    public void replaceOrAdd(T target, IsThisOne<T> isThisOne) {
        if (target == null || isThisOne == null)
            return;

        int index = index(isThisOne);
        if (index >= 0) {
            set(index, target);
        } else {
            ts.add(target);
        }
    }

    public ListSymbolTs<T> symbol() {
        return new ListSymbolTs<>(this);
    }

    public List<T> get() {
        return ts;
    }

    public TArrayTs<T> toArray() {
        int count = count();
        if (count <= 0) {
            return null;
        }
        T[] newArray = (T[]) java.lang.reflect.Array.newInstance
                (get(0).getClass(), count);
        return Ts.ts(ts.toArray(newArray));
    }

    public void delete(IsThisOne<T> isThisOne) {
        if (isThisOne == null)
            return;
        int index = index(isThisOne);
        if (index >= 0) {
            ts.remove(index);
        }
    }

    public void deleteAll(IsThisOne<T> isThisOne) {
        if (isThisOne == null)
            return;
        List<T> ts = convert(new Convert<T, T>() {
            @Override
            public T convert(int index, T t) {
                return isThisOne.isThisOne(index, t) ? null : t;
            }
        }).get();
        this.ts.clear();
        this.ts.addAll(ts);
    }

    /**************************************************
     *
     * 分组排序
     *
     **************************************************/
    public TListTs<T> groupSort(GroupSortGetter<T> getter) {
        ListValueMap<String, String> totalMap = new ListValueMap<>();
        Map<String, T> tMap = new HashMap<>();

        Collections.sort(ts, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return getter.compare(o1, o2);
            }
        });

        ls(new EachTs<T>() {
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
        ts = as;
        return this;
    }

    private void groupSort(List<T> container, int levels, int level, ListValueMap<String, String> categorgMap, Map<String, T> tMap, String key) {
        Ts.ts(categorgMap.get(key)).ls(new EachTs<String>() {
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

    private String getRootGroupKey() {
        return "root";
    }

    public TListTs<T> add(T target) {
        if (ts == null) {
            ts = new ArrayList<>();
        }
        ts.add(target);
        return this;
    }

    public interface GroupSortGetter<T> {
        String getGroup(int level, T t);

        int getLevels();

        int compare(T o1, T o2);

    }

}
