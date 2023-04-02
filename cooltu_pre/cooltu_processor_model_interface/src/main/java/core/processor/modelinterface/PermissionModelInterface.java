package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface PermissionModelInterface {

    public void setTagFor_fields(StringBuilder sb);

    public void setTagFor_methods(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package core.tools;");
        lines.add("");
        lines.add("import android.Manifest;");
        lines.add("import android.app.Activity;");
        lines.add("");
        lines.add("public class Permissions {");
        lines.add("");
        lines.add("[[fields]]");
        lines.add("");
        lines.add("[[methods]]");
        lines.add("");
        lines.add("}");
        return lines;
    }
}
