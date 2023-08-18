package cooltu.lib4j.file;

import cooltu.lib4j.file.bean.FileInfo;

import java.io.*;

public class FileTool {

    /**************************************************
     *
     * 获取项目目录
     *
     **************************************************/
    public static String getProjectDir() {
        return System.getProperty("user.dir");
    }


    public static File getDefaultFile() {
        return new File(getProjectDir(), "files\\files");
    }

    public static File getFileInProject(String path) {
        return new File(getProjectDir(), path);
    }

    /**************************************************
     *
     * 读取
     *
     **************************************************/
    public static BufferedReader getBufferedReader(File file) throws Exception {
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
    }

    public static BufferedWriter getBufferedWriter(File file) throws Exception {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
    }

    /**************************************************
     *
     * 创建目录
     *
     **************************************************/
    public static void createFileDir(File file) {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
    }

    public static void createFileDir(String path) {
        createFileDir(new File(path));
    }

    public static void createDir(String path) {
        createDir(new File(path));
    }

    public static void createDir(File dir) {
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**************************************************
     *
     * toFileInfo
     *
     **************************************************/
    public static FileInfo toFileInfo(File file) {
        FileInfo info = new FileInfo();
        info.file = file;
        info.isDir = file.isDirectory();
        info.name = file.getName();
        info.simpleName = info.name;
        if (!info.isDir) {
            int lastIndexOf = info.name.lastIndexOf(".");
            if (lastIndexOf >= 0) {
                info.simpleName = info.name.substring(0, lastIndexOf);
                info.type = info.name.substring(lastIndexOf + 1);
            }
        }
        return info;
    }

    public static String simpleName(File file) {
        String simpleName = file.getName();
        if (!file.isDirectory()) {
            int lastIndexOf = simpleName.lastIndexOf(".");
            if (lastIndexOf >= 0) {
                simpleName = simpleName.substring(0, lastIndexOf);
            }
        }
        return simpleName;
    }

    public static String type(File file) {
        String type = "";
        if (!file.isDirectory()) {
            String fileName = file.getName();
            int lastIndexOf = fileName.lastIndexOf(".");
            if (lastIndexOf >= 0) {
                type = fileName.substring(lastIndexOf + 1);
            }
        }
        return type;
    }

    public static String getRename(File file, String oldDirPath, String newDirPath) {
        return newDirPath + file.getAbsolutePath().substring(oldDirPath.length());
    }

    public static File getRenameFile(File file, String oldDirPath, String newDirPath) {
        return new File(getRename(file, oldDirPath, newDirPath));
    }

}
