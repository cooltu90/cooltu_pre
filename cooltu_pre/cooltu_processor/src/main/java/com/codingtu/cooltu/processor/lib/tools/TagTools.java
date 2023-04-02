package com.codingtu.cooltu.processor.lib.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cooltu.lib4j.tools.StringTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;

public class TagTools {

    public static List<String> getLines(final Map<String, StringBuilder> tags, List<String> readLines) {
        final ArrayList<String> lines = new ArrayList<String>();
        Ts.ls(readLines, new Each<String>() {
            @Override
            public boolean each(int position, String line) {
                lines.add(getLine(tags, line));
                return false;
            }
        });
        return lines;
    }

    public static String getLine(Map<String, StringBuilder> tags, String line) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = 0;
        while (true) {
            start = line.indexOf("[[", end);
            if (start > 0) {
                sb.append(line.substring(end, start));
            } else if (start < 0) {
                sb.append(line.substring(end, line.length()));
                break;
            }
            end = line.indexOf("]]", start);

            String tag = line.substring(start + 2, end);
            if (StringTool.isNotBlank(tag)) {

                StringBuilder tagValue = tags.get(tag);
                if (tagValue != null) {
                    sb.append(tagValue.toString());
                }
            } else {
                sb.append("[]");
            }

            end += 2;
        }
        return sb.toString();
    }

    public static String getLine(String line, Object... tags) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = 0;
        int num = 0;
        while (true) {
            start = line.indexOf("[", end);
            if (start > 0) {
                sb.append(line.substring(end, start));
            } else if (start < 0) {
                sb.append(line.substring(end, line.length()));
                break;
            }
            end = line.indexOf("]", start);
            String tag = line.substring(start + 1, end);
            if (StringTool.isNotBlank(tag)) {
                Object tagValue = tags[num];
                if (tagValue != null) {
                    sb.append(StringTool.toString(tagValue));
                }
                num++;
            } else {
                sb.append("[]");
            }
            end += 1;
        }
        return sb.toString();
    }

    public static List<String> getTags(List<String> lines) {
        ArrayList<String> tags = new ArrayList<>();
        Ts.ls(lines, new Each<String>() {
            @Override
            public boolean each(int position, String line) {
                getTag(tags, line);
                return false;
            }
        });
        return tags;
    }

    public static void getTag(Set<String> tags, String line) {
        int start = 0;
        int end = 0;
        while (true) {
            start = line.indexOf("[[", end);
            if (start < 0) {
                break;
            }
            end = line.indexOf("]]", start);

            String tag = line.substring(start + 2, end);
            if (StringTool.isNotBlank(tag)) {
                tags.add(tag);
            }
            end += 2;
        }
    }

    public static void getTag(List<String> tags, String line) {
        int start = 0;
        int end = 0;
        while (true) {
            start = line.indexOf("[[", end);
            if (start < 0) {
                break;
            }
            end = line.indexOf("]]", start);

            String tag = line.substring(start + 2, end);
            if (StringTool.isNotBlank(tag)) {
                if (!tags.contains(tag)) {
                    tags.add(tag);
                }
            }
            end += 2;
        }
    }
}
