package com.codingtu.cooltu.processor.lib.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cooltu.lib4j.file.read.FileReader;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.tools.StringTool;

public class LayoutTools {

    public static class ViewInfo {
        public String name;
        public String id;
        public boolean isInclude;
        public String subLayout;
        public ViewInfo parent;
        public String tag;
        public String bindType;
        public String bindName;
        public String bindCase;
        public String bindCaseType;
        public String bindHint;

        @Override
        public String toString() {
            return "ViewInfo{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", isInclude=" + isInclude +
                    ", subLayout='" + subLayout + '\'' +
                    ", parent=" + parent +
                    '}';
        }
    }

    public static List<ViewInfo> read(String name, ViewInfo parent) {
        try {

            List<String> lines = FileReader.from(getLayoutFile(name)).readLine();
            List<ViewInfo> vis = new ArrayList<ViewInfo>();
            ViewInfo vi = null;
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.startsWith("<")) {
                    if (!line.startsWith("</")) {
                        addToVis(vis, vi);
                        vi = new ViewInfo();


                        String viewPath = line.substring(1).split(" ")[0];
                        if ("include".endsWith(viewPath)) {
                            vi.name = "include";
                            vi.isInclude = true;
                        } else if (viewPath.contains(".")) {
                            vi.name = viewPath;
                        } else if ("merge".equals(viewPath)) {
                            vi = null;
                            continue;
                        } else {
                            vi.name = "android.widget." + viewPath;
                        }

                        if (parent != null) {
                            if (vis.size() == 0) {
                                vi.id = parent.id;
                                parent.name = vi.name;
                                vi.parent = parent.parent;
                            } else {
                                vi.parent = parent;
                            }
                        }
                    }
                }
                if (vi == null) {
                    continue;
                }

                if (vi.isInclude && line.contains("layout=")) {
                    vi.subLayout = StringTool.getSub(line, "layout=", "/", "\"");
                }

                if (line.contains("android:id=\"@+id")) {
                    vi.id = getId(line, "android:id=\"@+id");
                } else if (line.contains("android:id=\"@id")) {
                    vi.id = getId(line, "android:id=\"@id");
                } else if (line.contains("android:tag=")) {
                    vi.tag = StringTool.getSub(line, "android:tag=", "\"", "\"");
                    if (StringTool.isNotBlank(vi.tag)) {
                        if (vi.tag.startsWith("bean:")) {
                            String[] parts = vi.tag.substring("bean:".length()).split(" ");
                            vi.bindType = parts[0];
                            vi.bindName = parts[1];
                        } else if (vi.tag.startsWith("bind:")) {
                            String[] parts = vi.tag.split(" ");
                            for (int j = 0; j < CountTool.count(parts); j++) {
                                String part = parts[j];
                                if (part.startsWith("bind:")) {
                                    vi.bindCase = part.substring("bind:".length());
                                } else if (part.startsWith("hint:")) {
                                    vi.bindHint = part.substring("hint:".length());
                                } else if (part.startsWith("type:")) {
                                    vi.bindCaseType = part.substring("type:".length());
                                }

                            }
                        }
                    }
                }

            }
            addToVis(vis, vi);

            return vis;
        } catch (Exception e) {
            return null;
        }

    }

    private static void addToVis(List<ViewInfo> vis, ViewInfo vi) throws Exception {
        if (vi != null) {
            if (vi.isInclude) {
                List<ViewInfo> sss = read(vi.subLayout, StringTool.isNotBlank(vi.id) ? vi : null);
                vis.addAll(sss);
            } else if (StringTool.isNotBlank(vi.id)) {
                vis.add(vi);
            }
        }
    }


    private static String getId(String line, String startStr) {
        return StringTool.getSub(line, startStr, "/", "\"");
    }

    public static File getLayoutFile(String name) {
        return new File(NameTools.getLayoutPath(name));
    }

    public static String getViFieldName(ViewInfo vi) {
        if (vi.parent != null) {
            return vi.id + "_" + getViFieldName(vi.parent);
        }
        if (vi.id.startsWith("core_")) {
            return vi.id.substring("core_".length());
        }
        return vi.id;
    }

    public static String getViParent(ViewInfo vi) {
        if (vi.parent != null) {
            return getViFieldName(vi.parent);
        }
        return "";
    }
}
