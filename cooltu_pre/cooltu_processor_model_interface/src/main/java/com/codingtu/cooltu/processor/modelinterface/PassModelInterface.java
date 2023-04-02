package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface PassModelInterface {

    public void setTagFor_fields(StringBuilder sb);

    public void setTagFor_methods(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package core.tools;");
        lines.add("");
        lines.add("import android.content.Intent;");
        lines.add("");
        lines.add("public class Pass {");
        lines.add("    public static final String FROM_ACT = com.codingtu.cooltu.constant.Constant.FROM_ACT;");
        lines.add("[[fields]]");
        lines.add("");
        lines.add("    public static final int fromAct(Intent data) {");
        lines.add("        return data.getIntExtra(FROM_ACT, -1);");
        lines.add("    }");
        lines.add("[[methods]]");
        lines.add("");
        lines.add("}");
        return lines;
    }
}
