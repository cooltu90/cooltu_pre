package com.codingtu.cooltu.test;

import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.lib4j.data.bean.Symbol;

public class User extends CoreBean implements Symbol {

    public String name;
    public int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String obtainSymbol() {
        return name;
    }
}
