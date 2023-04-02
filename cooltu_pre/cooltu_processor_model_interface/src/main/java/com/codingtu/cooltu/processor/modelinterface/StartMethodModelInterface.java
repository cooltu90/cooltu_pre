package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface StartMethodModelInterface {

    public void setTagFor_methodName(StringBuilder sb);

    public void setTagFor_methodParams(StringBuilder sb);

    public void setTagFor_actFullName(StringBuilder sb);

    public void setTagFor_code(StringBuilder sb);

    public void setTagFor_params(StringBuilder sb);

    public void setTagFor_ActTools(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("    public static final void [[methodName]](Activity act[[methodParams]]) {");
        lines.add("        Intent intent = new Intent(act, [[actFullName]].class);");
        lines.add("        intent.putExtra(Pass.FROM_ACT,Code4Request.[[code]]);");
        lines.add("[[params]]");
        lines.add("        [[ActTools]].startActivityForResult(act, intent, Code4Request.[[code]]);");
        lines.add("    }");
        return lines;
    }
}
