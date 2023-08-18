package cooltu.lib4j.file.list;

import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import cooltu.lib4j.tools.CountTool;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileLister {
    private File dirFile;
    private boolean isContainsDir;
    private boolean isDirInFront;

    public static FileLister dir(String dir) {
        return dir(new File(dir));
    }

    public static FileLister dir(File dirFile) {
        FileLister fileLister = new FileLister();
        fileLister.dirFile = dirFile;
        return fileLister;
    }

    public FileLister containsDir() {
        this.isContainsDir = true;
        return this;
    }

    public FileLister dirInFront() {
        this.isDirInFront = true;
        return this;
    }


    public void listOnce(ListFile listFile) {
        Ts.ls(dirFile.listFiles(), new Each<File>() {
            @Override
            public boolean each(int position, File file) {
                listFile.list(file);
                return false;
            }
        });
    }

    public void list(ListFile listFile) {
        listOnce(new ListFile() {
            @Override
            public void list(File file) {
                FileLister.this.list(file, listFile);
            }
        });
    }

    private void list(File file, ListFile listFile) {
        if (file.isDirectory()) {
            if (isContainsDir && isDirInFront)
                listFile.list(file);
            File[] files = file.listFiles();
            int count = CountTool.count(files);
            for (int i = 0; i < count; i++) {
                list(files[i], listFile);
            }
            if (isContainsDir && !isDirInFront)
                listFile.list(file);
        } else {
            listFile.list(file);
        }
    }

    public List<File> list() {
        ArrayList<File> list = new ArrayList<>();
        list(new ListFile() {
            @Override
            public void list(File file) {
                list.add(file);
            }
        });
        return list;
    }

    public LinkedList<File> linkedList() {
        LinkedList<File> list = new LinkedList<>();
        list(new ListFile() {
            @Override
            public void list(File file) {
                list.addLast(file);
            }
        });
        return list;
    }

}
