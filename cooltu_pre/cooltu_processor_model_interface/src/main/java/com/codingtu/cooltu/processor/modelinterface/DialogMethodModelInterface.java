package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface DialogMethodModelInterface {

    public void setTagFor_nameToClass(StringBuilder sb);

    public void setTagFor_classType(StringBuilder sb);

    public void setTagFor_classParam(StringBuilder sb);

    public void setTagFor_name(StringBuilder sb);

    public void setTagFor_DialogView(StringBuilder sb);

    public void setTagFor_act(StringBuilder sb);

    public void setTagFor_title(StringBuilder sb);

    public void setTagFor_content(StringBuilder sb);

    public void setTagFor_leftBtText(StringBuilder sb);

    public void setTagFor_rightBtText(StringBuilder sb);

    public void setTagFor_layout(StringBuilder sb);

    public void setTagFor_yesParam(StringBuilder sb);

    public void setTagFor_obj(StringBuilder sb);

    public void setTagFor_showOtherParamSign(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("    protected void show[[nameToClass]]([[classType]] [[classParam]]) {");
        lines.add("        if ([[name]] == null) {");
        lines.add("            [[name]] = new [[DialogView]]([[act]])");
        lines.add("                    .setTitle(\"[[title]]\")");
        lines.add("                    .setContent(\"[[content]]\")");
        lines.add("                    .setLeftBtText(\"[[leftBtText]]\")");
        lines.add("                    .setRighBtText(\"[[rightBtText]]\")");
        lines.add("                    .setLayout([[layout]])");
        lines.add("                    .setOnBtClick(new [[DialogView]].OnBtClick() {");
        lines.add("                        @Override");
        lines.add("                        public void onLeftClick(Object obj) {");
        lines.add("                            [[name]]Left([[yesParam]]);");
        lines.add("                        }");
        lines.add("");
        lines.add("                        @Override");
        lines.add("                        public void onRightClick(Object obj) {");
        lines.add("                            [[name]]Right([[yesParam]]);");
        lines.add("                        }");
        lines.add("                    })");
        lines.add("                    .build();");
        lines.add("        }");
        lines.add("        [[name]].setObject([[obj]]);");
        lines.add("        [[name]].show();");
        lines.add("    }");
        lines.add("    protected void show[[nameToClass]](String content[[showOtherParamSign]][[classType]] [[classParam]]) {");
        lines.add("        if ([[name]] == null) {");
        lines.add("            [[name]] = new [[DialogView]]([[act]])");
        lines.add("                    .setTitle(\"[[title]]\")");
        lines.add("                    .setContent(content)");
        lines.add("                    .setLeftBtText(\"[[leftBtText]]\")");
        lines.add("                    .setRighBtText(\"[[rightBtText]]\")");
        lines.add("                    .setLayout([[layout]])");
        lines.add("                    .setOnBtClick(new [[DialogView]].OnBtClick() {");
        lines.add("                        @Override");
        lines.add("                        public void onLeftClick(Object obj) {");
        lines.add("                            [[name]]Left([[yesParam]]);");
        lines.add("                        }");
        lines.add("");
        lines.add("                        @Override");
        lines.add("                        public void onRightClick(Object obj) {");
        lines.add("                            [[name]]Right([[yesParam]]);");
        lines.add("                        }");
        lines.add("                    })");
        lines.add("                    .build();");
        lines.add("        }else{");
        lines.add("            [[name]].updateContent(content);");
        lines.add("        }");
        lines.add("        [[name]].setObject([[obj]]);");
        lines.add("        [[name]].show();");
        lines.add("    }");
        lines.add("");
        lines.add("    protected void [[name]]Left([[classType]] [[classParam]]) {}");
        lines.add("    protected void [[name]]Right([[classType]] [[classParam]]) {}");
        lines.add("");
        lines.add("");
        return lines;
    }
}
