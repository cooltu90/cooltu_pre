package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface MockModelInterface {

    public void setTagFor_fields(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package core.tools;");
        lines.add("");
        lines.add("public class Mocks {");
        lines.add("[[fields]]");
        lines.add("}");
        return lines;
    }
}
