package com.codingtu.cooltu.lib4j.os;

import com.codingtu.cooltu.lib4j.tools.CountTool;

import java.util.Iterator;
import java.util.Set;

import javax.lang.model.element.Element;

public class Ss<S> {
    Set<S> ss;

    public static <S> Ss<S> ss(Set<S> ss) {
        Ss<S> sss = new Ss<S>();
        sss.ss = ss;
        return sss;
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public interface EachSs<S> {
        public boolean each(S s);
    }

    public void ls(EachSs<S> eachSs) {
        os(new Os.EachOs<S>() {
            @Override
            public boolean each(int position, S s) {
                return eachSs.each(s);
            }
        });
    }

    public void os(Os.EachOs<S> eachOs) {
        if (eachOs == null)
            return;
        if (CountTool.count(ss) > 0) {
            int index = 0;
            Iterator<? extends S> iterator = ss.iterator();
            while (iterator.hasNext()) {
                if (eachOs.each(index, iterator.next())) {
                    return;
                }
                index++;
            }
        }
    }
}
