package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface BindHandlerModelInterface {

    public void setTagFor_type(StringBuilder sb);

    public void setTagFor_bean(StringBuilder sb);

    public void setTagFor_cases(StringBuilder sb);

    public void setTagFor_ListValueMapFullName(StringBuilder sb);

    public void setTagFor_TsFullName(StringBuilder sb);

    public void setTagFor_EachFullName(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("    public static class BindHandler extends android.os.Handler {");
        lines.add("        private [[type]] [[bean]];");
        lines.add("");
        lines.add("        public BindHandler([[type]] [[bean]]) {");
        lines.add("            this.[[bean]] = [[bean]];");
        lines.add("        }");
        lines.add("");
        lines.add("        @Override");
        lines.add("        public void handleMessage(android.os.Message msg) {");
        lines.add("            super.handleMessage(msg);");
        lines.add("[[cases]]");
        lines.add("");
        lines.add("        }");
        lines.add("");
        lines.add("");
        lines.add("        private [[ListValueMapFullName]]<Integer, com.codingtu.cooltu.lib4a.form.FormLink> links;");
        lines.add("");
        lines.add("        private [[ListValueMapFullName]]<Integer, com.codingtu.cooltu.lib4a.form.FormLink> getLinks() {");
        lines.add("            if (links == null) {");
        lines.add("                links = new [[ListValueMapFullName]]<>();");
        lines.add("            }");
        lines.add("            return links;");
        lines.add("        }");
        lines.add("");
        lines.add("        public void addLink(int index, com.codingtu.cooltu.lib4a.form.FormLink link) {");
        lines.add("            getLinks().get(index).add(link);");
        lines.add("        }");
        lines.add("");
        lines.add("        private void link(int id) {");
        lines.add("            [[TsFullName]].ls(getLinks().get(id), new [[EachFullName]]<com.codingtu.cooltu.lib4a.form.FormLink>() {");
        lines.add("                @Override");
        lines.add("                public boolean each(int position, com.codingtu.cooltu.lib4a.form.FormLink formLink) {");
        lines.add("                    formLink.link();");
        lines.add("                    return false;");
        lines.add("                }");
        lines.add("            });");
        lines.add("        }");
        lines.add("    }");
        return lines;
    }
}
