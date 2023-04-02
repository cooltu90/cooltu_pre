package com.codingtu.cooltu.processor.worker.model;

import java.util.ArrayList;
import java.util.List;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.file.read.FileReader;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.modelinterface.ManifestModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

public class ManifestModel extends BaseModel implements ManifestModelInterface {

    private String actFullName;
    private String screenOrientation;

    public ManifestModel(JavaInfo info) {
        super(info);
        this.isForce = true;
    }

    @Override
    public List<String> getLines() {
        ArrayList<String> newLines = new ArrayList<>();
        try {
            List<String> lines = FileReader.from(info.path).readLine();
            int count = CountTool.count(lines);
            for (int i = 0; i < count; i++) {
                String line = lines.get(i);
                if (line.contains("</application>")) {
                    newLines.add("        <activity");
                    if (StringTool.isBlank(screenOrientation)) {
                        newLines.add("            android:name=\"" + actFullName + "\" />");
                    } else {
                        newLines.add("            android:name=\"" + actFullName + "\"");
                        newLines.add("            android:screenOrientation=\"" + screenOrientation + "\" />");
                    }
                }
                newLines.add(line);
            }
        } catch (Exception e) {
        }
        return newLines;
    }

    public void setAct(String fullName, String screenOrientation) {
        this.actFullName = fullName;
        this.screenOrientation = screenOrientation;
    }
}
