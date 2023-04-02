package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface ResModelInterface {

    public void setTagFor_pkg(StringBuilder sb);

    public void setTagFor_actFullName(StringBuilder sb);

    public void setTagFor_resFullName(StringBuilder sb);

    public void setTagFor_actSimpleName(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import [[actFullName]];");
        lines.add("import [[resFullName]];");
        lines.add("");
        lines.add("@ResFor([[actSimpleName]].class)");
        lines.add("public class [[actSimpleName]]Res {");
        lines.add("");
        lines.add("}");
        lines.add("");
        return lines;
    }
}
