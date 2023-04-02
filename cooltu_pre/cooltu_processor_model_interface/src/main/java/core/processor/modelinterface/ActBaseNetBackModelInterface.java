package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface ActBaseNetBackModelInterface {

    public void setTagFor_if(StringBuilder sb);

    public void setTagFor_backName(StringBuilder sb);

    public void setTagFor_backClass(StringBuilder sb);

    public void setTagFor_params(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("        [[if]] (\"[[backName]]\".equals(code)) {");
        lines.add("            [[backClass]] back = new [[backClass]]() {");
        lines.add("                @Override");
        lines.add("                public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {");
        lines.add("                    super.accept(code, result, params, objs);");
        lines.add("                    [[backName]]([[params]]);");
        lines.add("                }");
        lines.add("            };");
        lines.add("            back.accept(code, result, params, objs);");
        lines.add("        }");
        return lines;
    }
}
