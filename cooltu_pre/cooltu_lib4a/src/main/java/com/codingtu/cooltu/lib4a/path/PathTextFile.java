package com.codingtu.cooltu.lib4a.path;

import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

import java.util.List;

public class PathTextFile extends PathBaseFile {


    public PathTextFile(String root, String type) {
        super(root, type);
    }

    public List<String> getTextLins() {
        return FileReader.from(root).readLine();
    }

    public String getText() {
        List<String> lines = getTextLins();
        StringBuilder sb = new StringBuilder();
        Ts.ls(lines, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                sb.append(s);
                return false;
            }
        });
        return sb.toString();
    }

    public void setText(Object text) {
        FileWriter.to(root).cover().write(text);
    }

    public void setTextLines(List lines) {
        FileWriter.to(root).cover().write(lines);
    }
}
