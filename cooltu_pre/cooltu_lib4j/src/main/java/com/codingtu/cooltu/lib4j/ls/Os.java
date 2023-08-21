package com.codingtu.cooltu.lib4j.ls;

import com.codingtu.cooltu.lib4j.ls.impl.ListTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.TListTs;

import java.util.List;

public class Os {

    public static <T> ListTs<T> os(List<T> ts) {
        return new ListTs<T>(new TListTs<>(ts));
    }
}
