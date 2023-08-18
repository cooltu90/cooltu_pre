package cooltu.lib4j.file.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cooltu.lib4j.file.FileTool;
import cooltu.lib4j.file.list.FileLister;
import cooltu.lib4j.file.list.ListFile;

public class FileCopy {
    private File src;
    private File target;
    private boolean force;
    private String targetPath;
    private String srcPath;

    public static FileCopy src(String path) {
        FileCopy fileCopy = new FileCopy();
        fileCopy.path(path);
        return fileCopy;
    }

    public static FileCopy src(File file) {
        FileCopy fileCopy = new FileCopy();
        fileCopy.path(file);
        return fileCopy;
    }

    private FileCopy path(String path) {
        this.src = new File(path);
        return this;
    }

    private FileCopy path(File path) {
        this.src = path;
        return this;
    }

    public FileCopy force() {
        this.force = true;
        return this;
    }

    public void to(String path) {
        this.target = new File(path);
        toTarget();
    }

    public void to(File target) {
        this.target = target;
        toTarget();
    }

    public void toDir(String dir) {
        this.target = new File(dir, src.getName());
        toTarget();
    }

    public void toDir(File dir) {
        this.target = new File(dir, src.getName());
        toTarget();
    }


    private void toTarget() {
        if (!force && target.exists()) {
            return;
        }
        targetPath = target.getAbsolutePath();
        srcPath = src.getAbsolutePath();
        if (targetPath.startsWith(srcPath)) {
            return;
        }

        toTarget(src);
    }

    private void toTarget(File file) {
        String rename = FileTool.getRename(file, srcPath, targetPath);
        File newFile = new File(rename);
        if (file.isDirectory()) {
            newFile.mkdirs();
            FileLister.dir(file).listOnce(new ListFile() {
                @Override
                public void list(File file) {
                    toTarget(file);
                }
            });
        } else {
            copy(file, newFile);
        }
    }

    private void copy(File oldFile, File newFile) {
        try {
            FileTool.createFileDir(newFile);
            InputStream input = new FileInputStream(oldFile);
            OutputStream output = new FileOutputStream(newFile);
            copy(input, output);
        } catch (Exception e) {
        }
    }

    private void copy(InputStream input, OutputStream output) {
        try {
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = input.read(bytes)) > 0) {
                output.write(bytes, 0, len);
            }
        } catch (Exception e) {
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }

    }

}
