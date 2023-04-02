package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface CreateActModelInterface {

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
        lines.add("import android.os.Bundle;");
        lines.add("");
        lines.add("import androidx.annotation.Nullable;");
        lines.add("");
        lines.add("import [[rPkg]].R;");
        lines.add("");
        lines.add("[[imports]]");
        lines.add("import com.codingtu.cooltu.processor.annotation.tools.To;");
        lines.add("import com.codingtu.cooltu.processor.annotation.ui.ActBase;");
        lines.add("import [[resPkg]].[[name]]Res;");
        lines.add("");
        lines.add("@To([[name]]Res.class)");
        lines.add("@ActBase(layout = R.layout.activity_[[baseName]])");
        lines.add("public class [[name]] extends [[base]] {");
        lines.add("");
        lines.add("    @Override");
        lines.add("    protected void onCreate(@Nullable Bundle savedInstanceState) {");
        lines.add("        super.onCreate(savedInstanceState);");
        lines.add("    }");
        lines.add("}");
        return lines;
    }
}
