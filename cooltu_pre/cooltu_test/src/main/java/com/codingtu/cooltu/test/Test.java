package com.codingtu.cooltu.test;

import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.TArrayTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.TListTs;

import java.util.ArrayList;
import java.util.Comparator;
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

        User[] users1 = new User[]{
                new User("李四", 15),
                new User("张三", 24),
                new User("张三", 56),
                new User("赵一饭", 19),
                new User("孟昭阳", 34),
                new User("王柳", 66),
        };

    }
}
