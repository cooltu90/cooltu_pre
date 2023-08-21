package com.codingtu.cooltu.test;

import com.codingtu.cooltu.lib4j.data.bean.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.tts.Ts;

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

        MaxMin<User> maxMin = Ts.ts(users).maxMin(new Ts.NowMax<User>() {
            @Override
            public boolean isNowMax(User last, User now) {
                return now.age > last.age;
            }
        });

        Logs.i(maxMin.max);
        Logs.i(maxMin.min);

    }
}
