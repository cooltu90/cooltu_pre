package com.codingtu.cooltu.processor.lib.ls;

import com.codingtu.cooltu.lib4j.ls.Os;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.List;

import javax.lang.model.element.VariableElement;

public class TypeLs {
    public static void ls(List<String> classes, String[] paramNames, EachType o) {
        int count = CountTool.count(paramNames);
        int offset = 0;
        for (int i = 0; i < count; i++) {
            String paramName = paramNames[i];
            String paramClass = classes.get(i + offset);
            if (List.class.getName().equals(paramClass)) {
                offset += 1;
                String beanClass = classes.get(i + offset);
                paramClass += "<" + beanClass + ">";
            }
            o.each(i, paramClass, paramName);
        }
    }

    public static void ls(List<? extends VariableElement> ps, final EachType eachType) {
        Os.ls(ps, (position, ve) -> {
            String type = ElementTools.getType(ve);
            String name = ElementTools.simpleName(ve);
            eachType.each(position, type, name);
            return false;
        });
    }

    public static void ls(List<? extends VariableElement> ps, final EachTypePlus eachType) {
        Os.ls(ps, (position, ve) -> {
            String type = ElementTools.getType(ve);
            String name = ElementTools.simpleName(ve);
            eachType.each(position, ve, type, name);
            return false;
        });
    }

    public static interface EachTypePlus {
        public void each(int position, VariableElement ve, String type, String name);
    }
}