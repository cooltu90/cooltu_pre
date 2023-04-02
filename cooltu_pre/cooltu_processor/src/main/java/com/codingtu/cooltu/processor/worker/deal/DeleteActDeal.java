package com.codingtu.cooltu.processor.worker.deal;

import java.io.File;
import java.util.ArrayList;

import javax.lang.model.element.Element;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.file.delete.FileDeleter;
import cooltu.lib4j.file.read.FileReader;
import cooltu.lib4j.file.read.ReadLine;
import cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.processor.annotation.delete.DeleteAct;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;

public class DeleteActDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DeleteAct deleteAct = element.getAnnotation(DeleteAct.class);
        String name = deleteAct.name();
        String packages = deleteAct.packages();

        JavaInfo actInfo = NameTools.getActInfo(packages, name);

        if (Constant.DELETE_ACTS == null) {
            Constant.DELETE_ACTS = new ArrayList<String>();
        }

        Constant.DELETE_ACTS.add(actInfo.fullName);

        deleteStart(name, actInfo);
    }

    private void deleteStart(String name, JavaInfo actInfo) {
        //删除activity
        FileDeleter.delete(new File(actInfo.path));
        //删除actbase
        JavaInfo actBaseInfo = NameTools.getActBaseInfo(name);
        FileDeleter.delete(new File(actBaseInfo.path));
        //删除layout
        String layoutPath = NameTools.getActivityLayoutPath(name);
        FileDeleter.delete(new File(layoutPath));
        //删除manifest
        StringBuilder sb = new StringBuilder();
        FileReader
                .from(NameTools.getManifestPath(Module.CURRENT))
                .readLine(new ReadLine<String>() {
                    @Override
                    public void readLine(String line) {
                        sb.append(line).append("\n");
                    }
                });
        int index = sb.indexOf(actInfo.fullName);
        int start = sb.lastIndexOf("<activity", index);
        int endOne = sb.indexOf("/>", start) + "/>".length();
        int endTwo = sb.indexOf("<", start + 1);
        if (endTwo < endOne) {
            endOne = sb.indexOf("</activity>", start) + "</activity>".length();
        }

        sb.delete(start, endOne);
        FileWriter
                .to(NameTools.getManifestPath(Module.CURRENT))
                .cover()
                .write(sb.toString());
    }
}
