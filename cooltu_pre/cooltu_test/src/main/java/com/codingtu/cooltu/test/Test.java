package com.codingtu.cooltu.test;

import com.codingtu.cooltu.cryption.tools.Cryption;
import com.codingtu.cooltu.cryption.tools.CryptionListener;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.TArrayTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.TListTs;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        MyApp.init();

        Cryption
                .decode()
                .file("H:\\临时\\今日桌面\\新建文件夹\\1 - 副本.exe")
                .password("1111")
                .listener(new CryptionListener() {
                    @Override
                    public void start(File file) {
                        Logs.i("start:" + file.getAbsolutePath());
                    }

                    @Override
                    public void finish(File file) {
                        Logs.i("finish:" + file.getAbsolutePath());
                    }

                    @Override
                    public void progress(File file, long totalLen, long currentLen) {
                        Logs.i("percent: totalLen:" + totalLen + " currentLen:" + currentLen);
                    }

                    @Override
                    public void error(File file, Throwable throwable) {
                        Logs.e(throwable);
                    }
                })
                .start();

    }
}
