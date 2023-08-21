package com.codingtu.cooltu.test;

import com.codingtu.cooltu.lib4j.os.Os;
import com.codingtu.cooltu.lib4j.os.Ss;

import java.util.HashSet;

public class Test {
    public static void main(String[] args) {

        MyApp.init();

        HashSet<User> users = new HashSet<>();
        users.add(new User("李四", 15));
        users.add(new User("赵一饭", 19));
        users.add(new User("王柳", 66));
        users.add(new User("张三", 24));
        users.add(new User("孟昭阳", 34));
        users.add(new User("张三", 56));

        Ss.ss(users).os(new Os.EachOs<User>() {

            @Override
            public boolean each(int position, User o) {
                Logs.i("position:" + position + " " + o);
                return false;
            }
        });

    }
}
