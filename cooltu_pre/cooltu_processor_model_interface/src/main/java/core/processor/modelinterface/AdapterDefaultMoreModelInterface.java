package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface AdapterDefaultMoreModelInterface {

    public void setTagFor_pkg(StringBuilder sb);

    public void setTagFor_rPkg(StringBuilder sb);

    public void setTagFor_bean(StringBuilder sb);

    public void setTagFor_vhFullName(StringBuilder sb);

    public void setTagFor_layoutName(StringBuilder sb);

    public void setTagFor_name(StringBuilder sb);

    public void setTagFor_vhName(StringBuilder sb);

    public void setTagFor_beanTypeName(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import [[rPkg]].R;");
        lines.add("import [[bean]];");
        lines.add("");
        lines.add("import core.lib4a.act.adapter.CoreMoreListAdapter;");
        lines.add("import core.processor.annotation.ui.VH;");
        lines.add("import [[vhFullName]];");
        lines.add("");
        lines.add("@VH(R.layout.[[layoutName]])");
        lines.add("public abstract class [[name]] extends CoreMoreListAdapter<[[vhName]], [[beanTypeName]]> {");
        lines.add("    @Override");
        lines.add("    public void onBindViewHolder([[vhName]] vh, int position, [[beanTypeName]] t) {");
        lines.add("");
        lines.add("    }");
        lines.add("}");
        return lines;
    }
}
