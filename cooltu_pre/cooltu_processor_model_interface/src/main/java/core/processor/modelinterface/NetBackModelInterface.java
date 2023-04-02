package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface NetBackModelInterface {

    public void setTagFor_name(StringBuilder sb);

    public void setTagFor_returnTag(StringBuilder sb);

    public void setTagFor_back(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package core.tools.net.back;");
        lines.add("");
        lines.add("import java.util.List;");
        lines.add("");
        lines.add("import core.lib4a.net.bean.CoreSendParams;");
        lines.add("import core.lib4a.net.netback.NetBack;");
        lines.add("import okhttp3.ResponseBody;");
        lines.add("import retrofit2.adapter.rxjava2.Result;");
        lines.add("");
        lines.add("public abstract class [[name]] extends NetBack {");
        lines.add("[[returnTag]]");
        lines.add("    @Override");
        lines.add("    public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {");
        lines.add("        super.accept(code, result, params, objs);");
        lines.add("[[back]]");
        lines.add("    }");
        lines.add("}");
        return lines;
    }
}
