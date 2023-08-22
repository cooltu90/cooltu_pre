package com.codingtu.cooltu.lib4j.file.write;

import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWriter {

    private File file;
    private boolean isCover;

    /**************************************************
     *
     * 创建方法
     *
     **************************************************/

    public static FileWriter to(File file) {
        FileWriter fileWriter = new FileWriter();
        fileWriter.file = file;
        return fileWriter;
    }

    public static FileWriter to(String path) {
        return to(new File(path));
    }

    /**************************************************
     *
     * 添加文件
     *
     **************************************************/

    public FileWriter cover() {
        this.isCover = true;
        return this;
    }

    /**************************************************
     *
     * 写入
     *
     **************************************************/

    public void write(Object line) {
        write(toList(line));
    }

    public void write(Object... lines) {
        write(Ts.ts(lines));
    }

    public void write(List lines) {
        write(Ts.ts(lines));
    }

    public void write(BaseTs<String> getter) {
        if (!isCover) {
            if (this.file.exists()) {
                throw new RuntimeException("文件已经存在");
            }
        } else {
            if (this.file == null) {
                this.file = FileTool.getDefaultFile();
            }
        }
        BufferedWriter bw = null;
        try {
            FileTool.createFileDir(file);

            bw = FileTool.getBufferedWriter(this.file);
            int count = getter == null ? 0 : getter.count();
            for (int i = 0; i < count; i++) {
                bw.write(getter.get(i).toString());
                bw.newLine();
            }
        } catch (Exception e) {
            LibLogs.w(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    LibLogs.w(e);
                }
                bw = null;
            }
        }
    }

    /**************************************************
     *
     * toList
     *
     **************************************************/
    private List toList(Object t) {
        ArrayList ts = new ArrayList();
        ts.add(t);
        return ts;
    }

}
