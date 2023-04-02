package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface ApiServiceMethodModelInterface {

    public void setTagFor_method(StringBuilder sb);

    public void setTagFor_methodName(StringBuilder sb);

    public void setTagFor_params(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("[[method]]");
        lines.add("    Flowable<Result<ResponseBody>> [[methodName]](");
        lines.add("[[params]]");
        lines.add("    );");
        return lines;
    }
}
