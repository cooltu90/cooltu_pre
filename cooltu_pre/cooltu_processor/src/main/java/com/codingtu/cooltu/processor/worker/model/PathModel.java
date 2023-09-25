package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.FileContentType;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.lib.bean.DirPathInfo;
import com.codingtu.cooltu.processor.lib.bean.FilePathInfo;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.modelinterface.PathModelInterface;
import com.codingtu.cooltu.processor.worker.deal.PathsDeal;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

@To(PathsDeal.class)
public class PathModel extends BaseModel implements PathModelInterface {
    private String rootPath;
    private List<DirPathInfo> dirInfos;
    private List<FilePathInfo> fileInfos;

    public PathModel(String rootPath, JavaInfo info) {
        super(info);
        isForce = true;
        this.rootPath = rootPath;
    }

    public void addDir(DirPathInfo info) {
        if (dirInfos == null) {
            dirInfos = new ArrayList<>();
        }
        dirInfos.add(info);
    }


    public void addFile(FilePathInfo info) {
        if (fileInfos == null) {
            fileInfos = new ArrayList<>();
        }
        fileInfos.add(info);
    }

    @Override
    public void create() {
        super.create();
        Logs.i("create:" + info.path + ":" + info.fullName);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    @Override
    public void setTagFor_pkg(StringBuilder sb) {
        sb.append(Pkg.PATH);
    }

    @Override
    public void setTagFor_name(StringBuilder sb) {
        sb.append(info.name);
    }

    @Override
    public void setTagFor_basePath(StringBuilder sb) {
        sb.append(FullName.BASE_PATH);
    }

    private boolean isParam(String s) {
        return s.startsWith("{") && s.endsWith("}");
    }

    private String cutParam(String s) {
        return s.substring(1, s.length() - 1);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    @Override
    public void setTagFor_obtain(StringBuilder sb) {
        if (StringTool.isNotBlank(rootPath)) {
            String[] split = rootPath.split("/");

            StringBuilder paramSb = new StringBuilder();

            int paramIndex[] = {0};
            Ts.ls(split, new BaseTs.EachTs<String>() {
                @Override
                public boolean each(int position, String s) {
                    if (isParam(s)) {
                        if (paramIndex[0] != 0) {
                            paramSb.append(", ");
                        }
                        paramSb.append("String ").append(cutParam(s));
                        paramIndex[0]++;
                    }
                    return false;
                }
            });

            Logs.i(paramSb.toString());

            addLnTag(sb, "    public static [name] obtain([params]) {"
                    , info.name, paramSb.toString());
            addLnTag(sb, "        return root([SDCardTool].getSDCard()", FullName.SDCARD_TOOL);

            Ts.ls(split, new BaseTs.EachTs<String>() {
                @Override
                public boolean each(int position, String s) {
                    if (isParam(s)) {
                        addLnTag(sb, "                + addPrexSeparator([company])", cutParam(s));
                    } else {
                        addLnTag(sb, "                + addPrexSeparator(\"[EnvCheckData]\")", s);
                    }
                    return false;
                }
            });

            addLnTag(sb, "        );");
            addLnTag(sb, "    }");
        }
    }

    @Override
    public void setTagFor_fields(StringBuilder sb) {
        Ts.ls(dirInfos, new BaseTs.EachTs<DirPathInfo>() {
            @Override
            public boolean each(int position, DirPathInfo info) {
                if (!isParam(info.dirName)) {
                    addLnTag(sb, "    public [CheckDeleteLabelPath] [DeleteLabel];", info.javaName, info.fieldName);
                }
                return false;
            }
        });

        Ts.ls(fileInfos, new BaseTs.EachTs<FilePathInfo>() {
            @Override
            public boolean each(int position, FilePathInfo info) {
                if (!isParam(info.fileName)) {
                    if (info.fileContentType.equals(FileContentType.TXT)) {
                        if (info.isVoidBean) {
                            addLnTag(sb, "    public [PathTextFile] [i4l];"
                                    , FullName.PATH_TEXT_FILE
                                    , info.fieldFullName);
                        } else {
                            addLnTag(sb, "    public [PathBeanFile]<[User]> [user_txt];",
                                    FullName.PATH_BEAN_FILE,
                                    info.beanClass,
                                    info.fieldFullName);
                        }
                    } else if (info.fileContentType.equals(FileContentType.PIC)) {
                        addLnTag(sb, "    public [PathImageFile] [handle_jpg];", FullName.PATH_IMAGE_FILE, info.fieldFullName);
                    }
                }
                return false;
            }
        });

    }

    @Override
    public void setTagFor_pathInit(StringBuilder sb) {
        Ts.ls(dirInfos, new BaseTs.EachTs<DirPathInfo>() {
            @Override
            public boolean each(int position, DirPathInfo info) {
                if (!isParam(info.dirName)) {
                    addLnTag(sb, "        this.[DeleteLabel] = new [CheckDeleteLabelPath](", info.fieldName, info.javaName);
                    addLnTag(sb, "                this.root");
                    addLnTag(sb, "                        + addPrexSeparator(\"[DeleteLabel]\")", info.dirName);
                    addLnTag(sb, "        );");
                }
                return false;
            }
        });
        Ts.ls(fileInfos, new BaseTs.EachTs<FilePathInfo>() {
            @Override
            public boolean each(int position, FilePathInfo info) {
                if (!isParam(info.fileName)) {
                    String fullName = null;
                    String initParams = null;
                    Object[] params = null;
                    if (info.fileContentType.equals(FileContentType.TXT)) {
                        if (info.isVoidBean) {
                            fullName = FullName.PATH_TEXT_FILE;
                            initParams = "                , \"[txt]\"";
                            params = new Object[]{info.file.fileType()};
                        } else {
                            fullName = FullName.PATH_BEAN_FILE;
                            initParams = "                , \"[txt]\", [class].class";
                            params = new Object[]{info.file.fileType(), info.beanClass};
                        }
                    } else if (info.fileContentType.equals(FileContentType.PIC)) {
                        fullName = FullName.PATH_IMAGE_FILE;
                        initParams = "                , \"[txt]\"";
                        params = new Object[]{info.file.fileType()};
                    }

                    if (StringTool.isBlank(fullName))
                        return false;

                    addLnTag(sb, "        this.[name] = new [PathTextFile](", info.fieldFullName, fullName);
                    addLnTag(sb, "                this.root");
                    addLnTag(sb, "                        + addPrexSeparator(\"[DeleteLabel]\")", info.fileFullName);
                    addLnTag(sb, initParams, params);
                    addLnTag(sb, "        );");
                }
                return false;
            }
        });
    }

    @Override
    public void setTagFor_hasParamPath(StringBuilder sb) {
        Ts.ls(dirInfos, new BaseTs.EachTs<DirPathInfo>() {
            @Override
            public boolean each(int position, DirPathInfo info) {
                if (isParam(info.dirName)) {
                    String param = cutParam(info.dirName);
                    addLnTag(sb, "    public [CheckDeleteLabelLabelPath] [label](String [labelName]) {"
                            , info.javaName, info.fieldName, param);
                    addLnTag(sb, "        return new [CheckDeleteLabelLabelPath](", info.javaName);
                    addLnTag(sb, "                this.root");
                    addLnTag(sb, "                        + addPrexSeparator([labelName])", param);
                    addLnTag(sb, "        );");
                    addLnTag(sb, "    }");
                }
                return false;
            }
        });
        Ts.ls(fileInfos, new BaseTs.EachTs<FilePathInfo>() {
            @Override
            public boolean each(int position, FilePathInfo info) {
                if (isParam(info.fileName)) {
                    String methodLine = null;
                    Object[] methodParams = null;
                    String paramLine = null;
                    Object[] paramParams = null;
                    String fullName = null;
                    String cutParam = cutParam(info.fileName);

                    if (!info.isVoidBean) {
                        fullName = FullName.PATH_BEAN_FILE;
                        methodLine = "    public [PathBeanFile]<[User]> [handle_jpg](String [labelName]) {";
                        methodParams = new Object[]{fullName, info.beanClass, info.fieldFullName, cutParam};
                        paramLine = "                , \"[txt]\", [className].class";
                        paramParams = new Object[]{info.file.fileType(), info.beanClass};
                    } else {
                        if (info.fileContentType.equals(FileContentType.TXT)) {
                            fullName = FullName.PATH_TEXT_FILE;
                        } else if (info.fileContentType.equals(FileContentType.PIC)) {
                            fullName = FullName.PATH_IMAGE_FILE;
                        }

                        if (StringTool.isBlank(fullName)) {
                            return false;
                        }

                        methodLine = "    public [PathTextFile] [handle_jpg](String [labelName]) {";
                        methodParams = new Object[]{fullName, info.fieldFullName, cutParam};
                        paramLine = "                , \"[txt]\"";
                        paramParams = new Object[]{info.file.fileType()};
                    }

                    addLnTag(sb, methodLine, methodParams);
                    addLnTag(sb, "        return new [PathBeanFile](", fullName);
                    addLnTag(sb, "                this.root");
                    addLnTag(sb, "                        + addPrexSeparator([labelName] + \"[.txt]\")", cutParam, info.fileType);
                    addLnTag(sb, paramLine, paramParams);
                    addLnTag(sb, "        );");
                    addLnTag(sb, "    }");
                }
                return false;
            }
        });
//        Ts.ls(fileInfos, new BaseTs.EachTs<FilePathInfo>() {
//            @Override
//            public boolean each(int position, FilePathInfo info) {
//                if (isParam(info.fileName)) {
//                    String methodLine = null;
//                    Object[] methodParams = null;
//                    String paramLine = null;
//                    Object[] paramParams = null;
//                    if (info.fileContentType.equals(FileContentType.TXT)) {
//                        String cutParam = cutParam(info.fileName);
//                        if (info.isVoidBean) {
//                            addLnTag(sb, "    public [PathTextFile] [handle_jpg](String [labelName]) {"
//                                    , FullName.PATH_TEXT_FILE, info.fieldFullName, cutParam);
//                            addLnTag(sb, "        return new [PathTextFile](", FullName.PATH_TEXT_FILE);
//                        } else {
//                            addLnTag(sb, "    public [PathBeanFile]<[User]> [handle_jpg](String [labelName]) {"
//                                    , FullName.PATH_BEAN_FILE, info.beanClass, info.fieldFullName, cutParam);
//                            addLnTag(sb, "        return new [PathTextFile](", FullName.PATH_BEAN_FILE);
//                        }
//
//
//                        addLnTag(sb, "                this.root");
//                        addLnTag(sb, "                        + addPrexSeparator([labelName] + \"[.txt]\")", cutParam, info.fileType);
//
//                        if (info.isVoidBean) {
//                            addLnTag(sb, "                , \"txt\"");
//                        } else {
//                            addLnTag(sb, "                , \"txt\", [className].class", info.beanClass);
//                        }
//
//                        addLnTag(sb, "        );");
//                        addLnTag(sb, "    }");
//                    }
//                }
//                return false;
//            }
//        });
    }


/**************************************************
 *
 * ————设置字段
 * {@link #setTagFor_fields(StringBuilder)}
 *
 * ————设置初始化
 * {@link #setTagFor_pathInit(StringBuilder)}
 *
 * ————有字段的文件夹
 * {@link #setTagFor_hasParamPath(StringBuilder)} }
 *
 *
 **************************************************/

}
/* model_temp_start
package [[pkg]];

public class [[name]] extends [[basePath]] {
[[fields]]
[[obtain]]
    public static [[name]] root(String root) {
        return new [[name]](root);
    }

    public [[name]](String root) {
        super(root);
[[pathInit]]
    }
[[hasParamPath]]
}

model_temp_end */
