package com.codingtu.cooltu.processor.lib.bean;

import java.util.Objects;

public class DialogInfo {
    public String name;
    public String title;
    public String content;
    public String objType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DialogInfo that = (DialogInfo) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
