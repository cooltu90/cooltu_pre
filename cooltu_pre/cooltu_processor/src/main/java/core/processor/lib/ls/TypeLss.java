package core.processor.lib.ls;

import java.util.List;

import javax.lang.model.element.VariableElement;

import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import core.processor.lib.tools.ElementTools;

public class TypeLss {
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
        Ts.ls(ps, new Each<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                String type = ElementTools.getType(ve);
                String name = ElementTools.simpleName(ve);
                eachType.each(position, type, name);
                return false;
            }
        });
    }

    public static void ls(List<? extends VariableElement> ps, final EachTypePlus eachType) {
        Ts.ls(ps, new Each<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                String type = ElementTools.getType(ve);
                String name = ElementTools.simpleName(ve);
                eachType.each(position, ve, type, name);
                return false;
            }
        });
    }

    public static interface EachTypePlus {
        public void each(int position, VariableElement ve, String type, String name);
    }
}
