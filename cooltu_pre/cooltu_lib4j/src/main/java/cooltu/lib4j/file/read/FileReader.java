package cooltu.lib4j.file.read;

import cooltu.lib4j.file.FileTool;
import cooltu.lib4j.file.read.parse.Parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader<T> {
    private File file;
    private Parse<T> parse;

    private FileReader() {

    }

    /**************************************************
     *
     * 静态创建
     *
     **************************************************/
    private static FileReader<String> create() {
        FileReader<String> fileReader = new FileReader<>();
        fileReader.addParse(new Parse<String>() {
            @Override
            public String parse(String line) {
                return line;
            }
        });
        return fileReader;
    }

    public static FileReader<String> from(File file) {
        FileReader<String> fileReader = create();
        fileReader.file = file;
        return fileReader;
    }

    public static FileReader<String> from(String path) {
        return from(new File(path));
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public FileReader<T> addParse(Parse<T> parse) {
        this.parse = parse;
        return this;
    }

    /**************************************************
     *
     * readline
     *
     **************************************************/

    public void readLine(ReadLine<T> readLine) {
        if (this.file == null) {
            this.file = FileTool.getDefaultFile();
        }
        if (!this.file.exists()) {
//            throw new RuntimeException("未指定文件");
            return;
        }

        if (parse == null) {
            throw new RuntimeException("未添加转换器");
        }

        BufferedReader br = null;
        try {
            br = FileTool.getBufferedReader(file);
            String line = null;
            while ((line = br.readLine()) != null) {
                readLine.readLine(parse.parse(line));
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                br = null;
            }
        }

    }

    public List<T> readLine() {
        ArrayList<T> list = new ArrayList<>();
        readLine(new ReadLine<T>() {
            @Override
            public void readLine(T t) {
                list.add(t);
            }
        });
        return list;
    }

}

