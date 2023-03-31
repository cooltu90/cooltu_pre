package core.processor.lib.bean;

import java.util.List;

import javax.lang.model.element.VariableElement;

import core.processor.lib.ls.EachType;
import core.processor.lib.ls.TypeLss;

public class Param {
    StringBuilder sb = new StringBuilder();
    StringBuilder sb1 = new StringBuilder();

    public Param(List<String> classes, String[] paramNames, final EachType each) {
        TypeLss.ls(classes, paramNames, new EachType() {
            @Override
            public void each(int position, String type, String name) {
                add(position, type, name);
                if (each != null)
                    each.each(position, type, name);
            }
        });
    }

    public Param(List<? extends VariableElement> ps, final EachType eachType) {
        TypeLss.ls(ps, new EachType() {
            @Override
            public void each(int position, String type, String name) {
                add(position, type, name);
                if (eachType != null)
                    eachType.each(position, type, name);
            }
        });
    }

    //, String name, double money
    public void add(int position, String type, String name) {
        if (position != 0) {
            sb.append(", ");
            sb1.append(", ");
        }
        sb.append(type).append(" ").append(name);
        sb1.append(name);
    }

    public String getParams() {
        return sb.toString();
    }

    public String getInvokeParams() {
        return sb1.toString();
    }

    public String getPartParams() {
        return ", " + getParams();
    }

    public String getPartInvokeParams() {
        return ", " + getInvokeParams();
    }
}
