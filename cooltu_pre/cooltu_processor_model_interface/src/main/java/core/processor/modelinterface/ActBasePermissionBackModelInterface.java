package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface ActBasePermissionBackModelInterface {

    public void setTagFor_if(StringBuilder sb);

    public void setTagFor_code(StringBuilder sb);

    public void setTagFor_methodName(StringBuilder sb);

    public void setTagFor_allow(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("        [[if]] (requestCode == core.tools.Permissions.CODE_[[code]]) {");
        lines.add("            [[methodName]]([[allow]]);");
        lines.add("        }");
        return lines;
    }
}
