package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface CreateFragmentModelInterface {

    public void setTagFor_pkg(StringBuilder sb);

    public void setTagFor_rPkg(StringBuilder sb);

    public void setTagFor_imports(StringBuilder sb);

    public void setTagFor_resPkg(StringBuilder sb);

    public void setTagFor_name(StringBuilder sb);

    public void setTagFor_baseName(StringBuilder sb);

    public void setTagFor_base(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import [[rPkg]].R;");
        lines.add("[[imports]]");
        lines.add("import [[resPkg]].[[name]]Res;");
        lines.add("import core.processor.annotation.tools.To;");
        lines.add("");
        lines.add("import core.processor.annotation.ui.FragmentBase;");
        lines.add("");
        lines.add("@To([[name]]Res.class)");
        lines.add("@FragmentBase(layout = R.layout.fragment_[[baseName]])");
        lines.add("public class [[name]] extends [[base]] {");
        lines.add("");
        lines.add("}");
        return lines;
    }
}
