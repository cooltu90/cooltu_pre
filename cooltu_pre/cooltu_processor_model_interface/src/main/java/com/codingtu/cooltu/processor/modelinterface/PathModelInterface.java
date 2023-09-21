package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface PathModelInterface {

    public void setTagFor_pkg(StringBuilder sb);

    public void setTagFor_SDCardToolFullName(StringBuilder sb);

    public void setTagFor_className(StringBuilder sb);

    public void setTagFor_BasePath(StringBuilder sb);

    public void setTagFor_fields(StringBuilder sb);

    public void setTagFor_rootParams(StringBuilder sb);

    public void setTagFor_rootPaths(StringBuilder sb);

    public void setTagFor_pathsInit(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import [[SDCardToolFullName]];");
        lines.add("");
        lines.add("public class [[className]] extends [[BasePath]] {");
        lines.add("");
        lines.add("    public String root;");
        lines.add("[[fields]]");
        lines.add("");
        lines.add("    public static [[className]] obtain([[rootParams]]) {");
        lines.add("        return root(SDCardTool.getSDCard()");
        lines.add("[[rootPaths]]");
        lines.add("        );");
        lines.add("    }");
        lines.add("");
        lines.add("    public static [[className]] root(String root) {");
        lines.add("        return new [[className]](root);");
        lines.add("    }");
        lines.add("");
        lines.add("    public [[className]](String root) {");
        lines.add("        this.root = root;");
        lines.add("[[pathsInit]]");
        lines.add("    }");
        lines.add("}");
        return lines;
    }
}
