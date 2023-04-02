package core.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface DialogForToastMethodModelInterface {

    public void setTagFor_className(StringBuilder sb);

    public void setTagFor_act(StringBuilder sb);

    public void setTagFor_layout(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("    private [[className]] toastDialog;");
        lines.add("");
        lines.add("    protected [[className]] getToastDialog() {");
        lines.add("        if (toastDialog == null)");
        lines.add("            toastDialog = new [[className]]([[act]])");
        lines.add("                    .setLayout([[layout]])");
        lines.add("                    .build();");
        lines.add("        return toastDialog;");
        lines.add("    }");
        lines.add("");
        lines.add("    protected void toastShow(String msg) {");
        lines.add("        [[className]] td = getToastDialog();");
        lines.add("        td.setContent(msg);");
        lines.add("        td.show();");
        lines.add("    }");
        lines.add("");
        lines.add("    protected void toastSet(String msg) {");
        lines.add("        if (cooltu.lib4j.tools.StringTool.isNotBlank(msg)) {");
        lines.add("            getToastDialog().setContent(msg);");
        lines.add("        }");
        lines.add("    }");
        lines.add("");
        lines.add("    protected void toastHidden(String msg, long time, core.lib4a.view.layerview.listener.OnHiddenFinished onHiddenFinished) {");
        lines.add("        toastSet(msg);");
        lines.add("        core.lib4a.tools.HandlerTool.getMainHandler().postDelayed(() -> getToastDialog().hidden(onHiddenFinished), time);");
        lines.add("    }");
        lines.add("");
        lines.add("    protected void toastHidden(String msg, core.lib4a.view.layerview.listener.OnHiddenFinished onHiddenFinished) {");
        lines.add("        toastHidden(msg, 1000, onHiddenFinished);");
        lines.add("    }");
        lines.add("");
        lines.add("    protected void toastHidden(String msg) {");
        lines.add("        toastHidden(msg, null);");
        lines.add("    }");
        return lines;
    }
}
