package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface DialogForEditMethodModelInterface {

    public void setTagFor_className(StringBuilder sb);

    public void setTagFor_name(StringBuilder sb);

    public void setTagFor_nameClassType(StringBuilder sb);

    public void setTagFor_classType(StringBuilder sb);

    public void setTagFor_classParam(StringBuilder sb);

    public void setTagFor_act(StringBuilder sb);

    public void setTagFor_title(StringBuilder sb);

    public void setTagFor_hint(StringBuilder sb);

    public void setTagFor_inputType(StringBuilder sb);

    public void setTagFor_layout(StringBuilder sb);

    public void setTagFor_textwatcher(StringBuilder sb);

    public void setTagFor_reserve(StringBuilder sb);

    public void setTagFor_stopAnimation(StringBuilder sb);

    public void setTagFor_yesParam(StringBuilder sb);

    public void setTagFor_obj(StringBuilder sb);

    public void setTagFor_textwatcherMethod(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("    private [[className]] [[name]];");
        lines.add("");
        lines.add("    protected void show[[nameClassType]](String text[[classType]] [[classParam]]) {");
        lines.add("        if ([[name]] == null)");
        lines.add("            [[name]] = new [[className]]([[act]])");
        lines.add("                    .setTitle(\"[[title]]\")");
        lines.add("                    .setHint(\"[[hint]]\")");
        lines.add("                    .setInputType([[inputType]])");
        lines.add("                    .setLayout([[layout]])[[textwatcher]]");
        lines.add("                    .setReserveOriValue([[reserve]])[[stopAnimation]]");
        lines.add("                    .setYes(new [[className]].Yes() {");
        lines.add("                        @Override");
        lines.add("                        public boolean yes(String text, Object obj) {");
        lines.add("                            return [[name]]Yes(text[[yesParam]]);");
        lines.add("                        }");
        lines.add("                    })");
        lines.add("                    .build();");
        lines.add("        [[name]].setEditText(text);");
        lines.add("        [[name]].setObject([[obj]]);");
        lines.add("        [[name]].show();");
        lines.add("    }");
        lines.add("");
        lines.add("    [[textwatcherMethod]]");
        lines.add("");
        lines.add("    protected boolean [[name]]Yes(String text[[classType]] [[classParam]]) {");
        lines.add("        return false;");
        lines.add("    }");
        lines.add("");
        lines.add("");
        return lines;
    }
}
