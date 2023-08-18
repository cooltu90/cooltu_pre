package cooltu.lib4j.file.rename;

import cooltu.lib4j.file.FileTool;
import cooltu.lib4j.file.copy.FileCopy;
import cooltu.lib4j.file.delete.FileDeleter;
import cooltu.lib4j.file.list.FileLister;
import cooltu.lib4j.file.list.ListFile;

import java.io.File;

public class FileRename {

    private File src;
    private File target;
    private String srcPath;
    private String targetPath;

    public static FileRename src(File src) {
        FileRename fileRename = new FileRename();
        fileRename.src = src;
        return fileRename;
    }

    public static FileRename src(String src) {
        return src(new File(src));
    }


    public void target(File target) {
        this.target = target;
        start();
    }

    public void target(String target) {
        target(new File(target));
    }

    private void start() {
        srcPath = src.getAbsolutePath();
        targetPath = target.getAbsolutePath();

        if (!target.exists() && target.isDirectory()) {
            target.mkdirs();
        }

        FileLister.dir(src).containsDir().dirInFront().list(new ListFile() {
            @Override
            public void list(File file) {
                File newFile = FileTool.getRenameFile(file, srcPath, targetPath);
                if (file.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    FileCopy.src(file).to(newFile);
                }
            }
        });

        FileDeleter.delete(src);
    }
}
