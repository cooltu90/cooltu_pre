package com.codingtu.cooltu.test;

import com.codingtu.cooltu.lib4j.config.LibApp;
import com.codingtu.cooltu.lib4j.config.LibConfigs;

public class MyApp extends LibApp {
    public static void init() {
        APP = new MyApp();
    }


    @Override
    protected LibConfigs createConfigs() {
        return new LibConfigs() {
            @Override
            public boolean isLog() {
                return true;
            }

            @Override
            public boolean isLogHttpConnect() {
                return false;
            }

            @Override
            public String getDefaultLogTag() {
                return "test";
            }

            @Override
            public void baseLog(int i, String s, String s1) {
                System.out.println(s1);
            }
        };
    }
}