package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface VHModelInterface {

    public void setTagFor_vhPkg(StringBuilder sb);

    public void setTagFor_name(StringBuilder sb);

    public void setTagFor_fileds(StringBuilder sb);

    public void setTagFor_rPkg(StringBuilder sb);

    public void setTagFor_layoutName(StringBuilder sb);

    public void setTagFor_findView(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package [[vhPkg]];");
        lines.add("");
        lines.add("import android.view.ViewGroup;");
        lines.add("");
        lines.add("import core.lib4a.act.viewholder.CoreAdapterVH;");
        lines.add("");
        lines.add("public class [[name]] extends CoreAdapterVH {");
        lines.add("[[fileds]]");
        lines.add("");
        lines.add("    public [[name]](ViewGroup parent) {");
        lines.add("        super([[rPkg]].R.layout.[[layoutName]], parent);");
        lines.add("[[findView]]");
        lines.add("    }");
        lines.add("}");
        return lines;
    }
}
