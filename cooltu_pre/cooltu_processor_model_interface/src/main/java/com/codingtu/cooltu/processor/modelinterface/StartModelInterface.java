package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface StartModelInterface {

    public void setTagFor_methods(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package core.tools;");
        lines.add("");
        lines.add("import android.app.Activity;");
        lines.add("import android.content.Intent;");
        lines.add("");
        lines.add("public class ActStart {");
        lines.add("");
        lines.add("[[methods]]");
        lines.add("");
        lines.add("}");
        return lines;
    }
}
