package com.codingtu.cooltu.processor.lib.bean;

import java.util.Objects;

public class EditDialogInfo {
    public String name;
    public String title;
    public String hint;
    public int inputType;
    //public boolean reserve;
    public boolean stopAnimation;
    public String objType;
    public boolean isUseTextwatcher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditDialogInfo that = (EditDialogInfo) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
