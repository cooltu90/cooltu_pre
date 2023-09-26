package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.constant.FileContentType;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.data.bean.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.path.DirPath;
import com.codingtu.cooltu.processor.annotation.path.FilePath;
import com.codingtu.cooltu.processor.annotation.path.Paths;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.lib.bean.DirPathInfo;
import com.codingtu.cooltu.processor.lib.bean.FilePathInfo;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.PathModel;

import java.util.HashMap;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

@To(PathModel.class)
public class PathsDeal extends BaseDeal {

    private HashMap<String, String> dirMap = new HashMap<>();
    private HashMap<String, PathModel> pathMap = new HashMap<>();

    @Override
    public void deal(Element element) {

        Paths paths = element.getAnnotation(Paths.class);
        String baseName = paths.name();
        dirMap.put("root", baseName);

        JavaInfo basePathJavaInfo = NameTools.getPathInfo(baseName);
        PathModel pathModel = new PathModel(paths.path(), basePathJavaInfo);
        pathMap.put("root", pathModel);

        Ts.ls(element.getEnclosedElements(), (position, e) -> {
            if (e instanceof VariableElement) {
                VariableElement ve = (VariableElement) e;
                DirPath dir = ve.getAnnotation(DirPath.class);
                if (dir != null) {
                    dealDir(ve, dir);
                }

                FilePath file = ve.getAnnotation(FilePath.class);
                if (file != null) {
                    dealFile(ve, file);
                }

            }
            return false;
        });


    }

    private void dealFile(VariableElement ve, FilePath file) {
        KV<String, String> kv = ElementTools.getFiledKv(ve);

        PathModel parentModel = pathMap.get(file.parent());
        FilePathInfo info = new FilePathInfo();
        info.file = file;

        info.fileName = info.fileFullName = StringTool.isBlank(file.fileName()) ? kv.v : file.fileName();
        info.fieldName = info.fieldFullName = StringTool.isBlank(file.fieldName()) ? kv.v : file.fieldName();
        info.fileContentType = file.fileContentType();
        if (!file.fileType().equals(FileType.NONE)) {
            if (info.fileContentType.equals(FileContentType.NONE)) {
                switch (file.fileType()) {
                    case FileType.TXT:
                    case FileType.JSON:
                        info.fileContentType = FileContentType.TXT;
                        break;
                    case FileType.JPG:
                    case FileType.PNG:
                    case FileType.PNC:
                        info.fileContentType = FileContentType.PIC;
                        break;
                }
            }
            info.fileType = "." + file.fileType();
            info.fieldType = "_" + file.fileType();
            info.fileFullName += info.fileType;
        } else {
            info.fieldType = "_";
        }
        info.fieldFullName += info.fieldType;

        info.beanClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return file.beanClass();
            }
        });
        info.isVoidBean = ClassTool.isVoid(info.beanClass);
        info.filter = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return file.filter();
            }
        });
        info.isFilter = !ClassTool.isVoid(info.filter);


        parentModel.addFile(info);

    }

    private void dealDir(VariableElement ve, DirPath dir) {
        KV<String, String> kv = ElementTools.getFiledKv(ve);
        String baseName = dirMap.get(dir.parent());
        String fieldName = dir.fieldName();
        if (StringTool.isBlank(fieldName)) {
            fieldName = kv.v;
        }

        baseName = baseName + ConvertTool.toClassType(fieldName);
        dirMap.put(kv.v, baseName);

        JavaInfo basePathJavaInfo = NameTools.getPathInfo(baseName);
        PathModel pathModel = new PathModel(null, basePathJavaInfo);

        pathMap.put(kv.v, pathModel);

        PathModel parentModel = pathMap.get(dir.parent());

        DirPathInfo dirInfo = new DirPathInfo();
        dirInfo.javaName = basePathJavaInfo.name;
        dirInfo.fieldName = fieldName;
        dirInfo.dirName = StringTool.isBlank(dir.dirName()) ? kv.v : dir.dirName();
        dirInfo.filter = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return dir.filter();
            }
        });
        dirInfo.isFilter = !ClassTool.isVoid(dirInfo.filter);

        parentModel.addDir(dirInfo);


    }
}
