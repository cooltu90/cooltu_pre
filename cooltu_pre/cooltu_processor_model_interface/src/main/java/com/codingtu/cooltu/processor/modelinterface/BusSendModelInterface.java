package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface BusSendModelInterface {

    public void setTagFor_name(StringBuilder sb);

    public void setTagFor_methods(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package core.tools;");
        lines.add("");
        lines.add("import com.codingtu.cooltu.lib4a.bus.BusStation;");
        lines.add("");
        lines.add("public class [[name]] {");
        lines.add("[[methods]]");
        lines.add("}");
        lines.add("");
        return lines;
    }
}
