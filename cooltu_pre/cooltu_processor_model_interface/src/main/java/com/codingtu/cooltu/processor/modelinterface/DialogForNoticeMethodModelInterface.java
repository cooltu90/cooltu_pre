package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface DialogForNoticeMethodModelInterface {

    public void setTagFor_className(StringBuilder sb);

    public void setTagFor_act(StringBuilder sb);

    public void setTagFor_layout(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("    private [[className]] noticeDialog;");
        lines.add("");
        lines.add("    protected void noticeShow(String msg) {");
        lines.add("        if (noticeDialog == null)");
        lines.add("            noticeDialog = new [[className]]([[act]])");
        lines.add("                    .setLayout([[layout]])");
        lines.add("                    .build();");
        lines.add("        noticeDialog.setContent(msg);");
        lines.add("        noticeDialog.show();");
        lines.add("    }");
        return lines;
    }
}
