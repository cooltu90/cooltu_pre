package com.codingtu.cooltu.test;

import com.codingtu.cooltu.lib4j.fake.Fake;
import com.codingtu.cooltu.lib4j.tss.TS;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        MyApp.init();

        String[] strs = new String[3];
        int[] ints = new int[]{1, 3, 4};
        List<String> names = Fake.names();
        names.add("lisi");

        int lisi = TS.ts(names).index(new TS.GetFilter<String>() {
            @Override
            public boolean get(int position, String s) {
                return s.equals("lisi");
            }
        });

        Logs.i(lisi);
    }
}
