package cooltu.lib4j.file.delete;

import java.io.File;

public class FileDeleter {

    public static void delete(String path) {
        delete(new File(path));
    }


    public static void delete(File file) {
        if (!file.exists()) {
            return;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            int size = files == null ? 0 : files.length;
            for (int i = 0; i < size; i++) {
                delete(files[i]);
            }
        }
        file.delete();
    }

}
