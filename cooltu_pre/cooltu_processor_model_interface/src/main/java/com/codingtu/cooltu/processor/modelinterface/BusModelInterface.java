package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface BusModelInterface {

    public void setTagFor_pkg(StringBuilder sb);

    public void setTagFor_name(StringBuilder sb);

    public void setTagFor_baseName(StringBuilder sb);

    public void setTagFor_readParams(StringBuilder sb);

    public void setTagFor_params(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import com.codingtu.cooltu.lib4a.bus.Bus;");
        lines.add("");
        lines.add("public abstract class [[name]] implements Bus {");
        lines.add("");
        lines.add("    public static final String TASK = \"[[baseName]]\";");
        lines.add("");
        lines.add("    @Override");
        lines.add("    public String getTask() {");
        lines.add("        return TASK;");
        lines.add("    }");
        lines.add("");
        lines.add("    @Override");
        lines.add("    public void back(Object obj) {");
        lines.add("        [[baseName]]BusBack([[readParams]]);");
        lines.add("    }");
        lines.add("");
        lines.add("    protected abstract void [[baseName]]BusBack([[params]]);");
        lines.add("");
        lines.add("}");
        return lines;
    }
}
