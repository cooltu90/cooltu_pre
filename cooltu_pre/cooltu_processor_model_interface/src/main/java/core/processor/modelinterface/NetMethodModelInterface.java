package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface NetMethodModelInterface {

    public void setTagFor_methodName(StringBuilder sb);

    public void setTagFor_params(StringBuilder sb);

    public void setTagFor_beforeReturn(StringBuilder sb);

    public void setTagFor_beforeCreate(StringBuilder sb);

    public void setTagFor_serviceName(StringBuilder sb);

    public void setTagFor_invokeParams(StringBuilder sb);

    public void setTagFor_methodBackName(StringBuilder sb);

    public void setTagFor_baseUrl(StringBuilder sb);

    public void setTagFor_sendParamField(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("");
        lines.add("    public static API [[methodName]]([[params]]) {");
        lines.add("[[beforeReturn]]");
        lines.add("        return NetTool.api(new CreateApi() {");
        lines.add("            @Override");
        lines.add("            public Flowable<Result<ResponseBody>>");
        lines.add("            create(Retrofit retrofit, CoreSendParams ps) {");
        lines.add("[[beforeCreate]]");
        lines.add("                return retrofit.create([[serviceName]].class).[[methodName]](");
        lines.add("[[invokeParams]]");
        lines.add("                );");
        lines.add("            }");
        lines.add("        }, [[methodBackName]], [[baseUrl]], [[sendParamField]]);");
        lines.add("    }");
        return lines;
    }
}
