package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface ApiServiceModelInterface {

    public void setTagFor_name(StringBuilder sb);

    public void setTagFor_methods(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package core.tools.net.api;");
        lines.add("");
        lines.add("import io.reactivex.Flowable;");
        lines.add("import okhttp3.ResponseBody;");
        lines.add("import retrofit2.adapter.rxjava2.Result;");
        lines.add("");
        lines.add("public interface [[name]] {");
        lines.add("[[methods]]");
        lines.add("}");
        return lines;
    }
}
