package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface SendParamsModelInterface {

    public void setTagFor_name(StringBuilder sb);

    public void setTagFor_fields(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package core.tools.net.params;");
        lines.add("");
        lines.add("import core.lib4a.net.bean.CoreSendParams;");
        lines.add("");
        lines.add("public class [[name]] extends CoreSendParams {");
        lines.add("[[fields]]");
        lines.add("}");
        return lines;
    }
}
