package com.codingtu.cooltu.test;

import com.codingtu.cooltu.lib4j.data.bean.Ymd;
import com.codingtu.cooltu.lib4j.tts.Convert;
import com.codingtu.cooltu.lib4j.tts.FinalGetter;
import com.codingtu.cooltu.lib4j.tts.MaxMin;
import com.codingtu.cooltu.lib4j.tts.Ts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        MyApp.init();

        List<User> users = new ArrayList<>();
        users.add(new User("李四", 15));
        users.add(new User("赵一饭", 19));
        users.add(new User("王柳", 66));
        users.add(new User("张三", 24));
        users.add(new User("孟昭阳", 34));
        users.add(new User("张三", 56));

        User target = new User("张三", 56);

        List<String> strings = new ArrayList<>();
        strings.add("2023-05-29");
        strings.add("2022-07-12");
        strings.add("2025-04-15");
        strings.add("2025-09-18");

        MaxMin<Ymd> maxMin = Ts.ts(strings).convert(new Convert<String, Ymd>() {
            @Override
            public Ymd convert(String s) {
                Ymd ymd = new Ymd();
                ymd.y = Integer.parseInt(s.substring(0, 4));
                ymd.m = Integer.parseInt(s.substring(5, 7));
                ymd.d = Integer.parseInt(s.substring(8, 10));
                return ymd;
            }
        }).maxMin(new FinalGetter<Ymd>() {
            @Override
            public boolean nowMax(Ymd last, Ymd now) {
                if (now.y == last.y) {
                    if (now.m == last.m) {
                        return now.d > last.d;
                    }
                    return now.m > last.m;
                }
                return now.y > last.y;
            }
        });

        Logs.i(maxMin.max.y + "-" + maxMin.max.m + "-" + maxMin.max.d);
        Logs.i(maxMin.min.y + "-" + maxMin.min.m + "-" + maxMin.min.d);


    }
}
