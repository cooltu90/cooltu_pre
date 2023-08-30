package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface DialogForToastMethodModelInterface {

    public void setTagFor_className(StringBuilder sb);

    public void setTagFor_act(StringBuilder sb);

    public void setTagFor_layout(StringBuilder sb);

    public void setTagFor_OnHiddenFinishedFullName(StringBuilder sb);

    public void setTagFor_HandlerToolFullName(StringBuilder sb);

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
        lines.add("    protected void toastShow(long time, String msg, [[OnHiddenFinishedFullName]] onHiddenFinished) {");
        lines.add("        toastShow(msg);");
        lines.add("        [[HandlerToolFullName]].getMainHandler().postDelayed(new java.lang.Runnable() {");
        lines.add("            @Override");
        lines.add("            public void run() {");
        lines.add("                getToastDialog().hidden(onHiddenFinished);");
        lines.add("            }");
        lines.add("        }, time);");
        lines.add("    }");
        lines.add("");
        lines.add("    protected void toastShow(long time, String msg) {");
        lines.add("        toastShow(time, msg, null);");
        lines.add("    }");
        lines.add("");
        lines.add("    protected void toastHidden(long time, String msg, [[OnHiddenFinishedFullName]] onHiddenFinished) {");
        lines.add("        getToastDialog().setContent(msg);");
        lines.add("        [[HandlerToolFullName]].getMainHandler().postDelayed(new java.lang.Runnable() {");
        lines.add("            @Override");
        lines.add("            public void run() {");
        lines.add("                getToastDialog().hidden(onHiddenFinished);");
        lines.add("            }");
        lines.add("        }, time);");
        lines.add("    }");
        lines.add("");
        lines.add("    protected void toastHidden(long time, String msg) {");
        lines.add("        toastHidden(time, msg, null);");
        lines.add("    }");
        return lines;
    }
}
