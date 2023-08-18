package com.codingtu.cooltu.lib4j.data.bean;

import com.codingtu.cooltu.lib4j.ts.Ts;

import java.util.List;

public class Ymd implements Comparable {

    public int y;
    public int m;
    public int d;

    public Ymd() {
    }

    public Ymd(int y, int m, int d) {
        this.y = y;
        this.m = m;
        this.d = d;
    }


    @Override
    public int compareTo(Object o) {
        if (o == null || !(o instanceof Ymd))
            return -1;
        Ymd ymd1 = (Ymd) o;
        int y = this.y - ymd1.y;
        if (y != 0) {
            return y;
        }

        int m = this.m - ymd1.m;
        if (m != 0) {
            return m;
        }

        return this.d - ymd1.d;
    }
}
