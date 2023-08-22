package com.codingtu.cooltu.lib4j.ls.impl;

import com.codingtu.cooltu.lib4j.data.bean.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.ls.Os;
import com.codingtu.cooltu.lib4j.ls.impl.basic.TListTs;

import java.util.ArrayList;

public abstract class BaseTs<T> implements Ts<T> {


    /**************************************************
     *
     *
     *
     **************************************************/

    public SymbolTs<T> symbol() {
        return new SymbolTs<>(this);
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public interface EachTs<T> {
        boolean each(int position, T t);
    }

    /**************************************************
     *
     * 遍历 - 正向
     *
     **************************************************/
    public void ls(int step, EachTs<T> eachTs) {
        if (eachTs == null || step <= 0)
            return;

        int count = count();
        for (int i = 0; i < count; i += step) {
            if (eachTs.each(i, get(i))) {
                return;
            }
        }
    }

    public void ls(EachTs<T> eachTs) {
        ls(1, eachTs);
    }

    /**************************************************
     *
     * 遍历 - 反向
     *
     **************************************************/
    public void rls(int step, EachTs<T> eachTs) {
        if (eachTs == null || step <= 0)
            return;

        int count = count();
        for (int i = count - 1; i >= 0; i -= step) {
            if (eachTs.each(i, get(i))) {
                return;
            }
        }
    }

    public void rls(EachTs<T> eachTs) {
        rls(1, eachTs);
    }

    /**************************************************
     *
     * IsThisOne
     *
     **************************************************/

    public interface IsThisOne<T> {
        boolean isThisOne(int position, T t);
    }

    /**************************************************
     *
     * 获取符合条件的元素
     *
     **************************************************/

    public T get(IsThisOne<T> isThisOne) {
        if (isThisOne == null)
            return null;

        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = get(i);
            if (isThisOne.isThisOne(i, t)) {
                return t;
            }
        }
        return null;
    }

    /**************************************************
     *
     * 是否有符合条件的元素
     *
     **************************************************/
    public boolean has(IsThisOne<T> isThisOne) {
        return get(isThisOne) != null;
    }

    /**************************************************
     *
     * 符合条件的元素的角标
     *
     **************************************************/
    public int index(IsThisOne<T> isThisOne) {
        if (isThisOne == null)
            return -1;
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = get(i);
            if (isThisOne.isThisOne(i, t)) {
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
    public void replace(T target, IsThisOne<T> isThisOne) {
        if (target == null || isThisOne == null)
            return;

        int index = index(isThisOne);
        if (index >= 0) {
            set(index, target);
        }
    }

    /**************************************************
     *
     * Convert
     *
     **************************************************/

    public interface Convert<S, T> {
        T convert(S s);
    }

    public <S> TListTs<S> convert(Convert<T, S> convert) {
        if (convert == null)
            return null;

        ArrayList<S> list = new ArrayList<>();
        int count = count();
        for (int i = 0; i < count; i++) {
            S s = convert.convert(get(i));
            if (s != null) {
                list.add(s);
            }
        }
        return Os.os(list);
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public interface IsNow<T> {
        boolean isNow(T last, T now);
    }

    public T findFinal(IsNow<T> isNow) {
        if (isNow == null)
            return null;

        int count = count();
        T last = null;
        for (int i = 0; i < count; i++) {
            T now = get(i);
            last = last == null ? now : (isNow.isNow(last, now) ? now : last);
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
