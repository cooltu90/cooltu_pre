package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface ActBackModelInterface {

    public void setTagFor_if(StringBuilder sb);

    public void setTagFor_code(StringBuilder sb);

    public void setTagFor_method(StringBuilder sb);

    public void setTagFor_params(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("            [[if]] (requestCode == core.tools.Code4Request.[[code]]) {");
        lines.add("                [[method]]([[params]]);");
        lines.add("            }");
        return lines;
    }
}
