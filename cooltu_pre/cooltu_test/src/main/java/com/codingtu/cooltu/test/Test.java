package com.codingtu.cooltu.test;

import com.codingtu.cooltu.lib4j.ls.Os;
import com.codingtu.cooltu.lib4j.ls.impl.ListTs;
import com.codingtu.cooltu.lib4j.ls.impl.StringTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.StringArrayTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.TArrayTs;
import com.codingtu.cooltu.lib4j.ls.impl.basic.TListTs;

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


        String[] names = new String[]{};

        ListTs<User> os = Os.os(users);

    }
}
