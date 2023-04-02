package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface NetModelInterface {

    public void setTagFor_backNameFileds(StringBuilder sb);

    public void setTagFor_baseUrls(StringBuilder sb);

    public void setTagFor_methods(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package core.tools;");
        lines.add("");
        lines.add("import core.lib4a.net.NetTool;");
        lines.add("import core.lib4a.net.api.API;");
        lines.add("import core.lib4a.net.api.CreateApi;");
        lines.add("import core.lib4a.net.bean.CoreSendParams;");
        lines.add("import io.reactivex.Flowable;");
        lines.add("import okhttp3.ResponseBody;");
        lines.add("import retrofit2.Retrofit;");
        lines.add("import retrofit2.adapter.rxjava2.Result;");
        lines.add("");
        lines.add("public class Net {");
        lines.add("");
        lines.add("[[backNameFileds]]");
        lines.add("");
        lines.add("[[baseUrls]]");
        lines.add("");
        lines.add("[[methods]]");
        lines.add("");
        lines.add("}");
        return lines;
    }
}
