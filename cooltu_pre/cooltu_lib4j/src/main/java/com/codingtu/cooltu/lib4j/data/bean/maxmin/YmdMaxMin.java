package com.codingtu.cooltu.lib4j.data.bean.maxmin;

import com.codingtu.cooltu.lib4j.data.bean.Ymd;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.each.Each;

import java.util.List;

public class YmdMaxMin {
    public Ymd max;
    public Ymd min;


    public static <S> YmdMaxMin obtain(List<S> ss, Ts.Convert<S, Ymd> convert) {
        Ts.ls(ss, new Each<S>() {
            @Override
            public boolean each(int position, S s) {
                Ymd ymd = convert.convert(s);
                return false;
            }
        });
        return null;
    }
}
