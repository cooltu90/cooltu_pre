package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface PathModelInterface {

    public void setTagFor_pkg(StringBuilder sb);

    public void setTagFor_name(StringBuilder sb);

    public void setTagFor_basePath(StringBuilder sb);

    public void setTagFor_fields(StringBuilder sb);

    public void setTagFor_obtain(StringBuilder sb);

    public void setTagFor_pathInit(StringBuilder sb);

    public void setTagFor_hasParamPath(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("public class [[name]] extends [[basePath]] {");
        lines.add("[[fields]]");
        lines.add("[[obtain]]");
        lines.add("    public static [[name]] root(String root) {");
        lines.add("        return new [[name]](root);");
        lines.add("    }");
        lines.add("");
        lines.add("    public [[name]](String root) {");
        lines.add("        super(root);");
        lines.add("[[pathInit]]");
        lines.add("    }");
        lines.add("[[hasParamPath]]");
        lines.add("}");
        lines.add("");
        return lines;
    }
}
