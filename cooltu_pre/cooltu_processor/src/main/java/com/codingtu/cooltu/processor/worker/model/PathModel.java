package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.FileContentType;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.data.bean.KV;
import com.codingtu.cooltu.lib4j.file.delete.FileDeleter;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.TListTs;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.lib.bean.DirPathInfo;
import com.codingtu.cooltu.processor.lib.bean.FilePathInfo;
import com.codingtu.cooltu.processor.lib.bean.PathFilterInfo;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.lib.tools.ParamTools;
import com.codingtu.cooltu.processor.modelinterface.PathModelInterface;
import com.codingtu.cooltu.processor.worker.deal.PathFilterDeal;
import com.codingtu.cooltu.processor.worker.deal.PathsDeal;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

@To(PathsDeal.class)
public class PathModel extends BaseModel implements PathModelInterface {
    public static boolean isFirst = true;

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
    protected void beforCreate() {
        super.beforCreate();
        if (isFirst) {
            isFirst = false;
            FileDeleter.delete(NameTools.getPathPath());
        }
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
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

                    if (info.isFilter) {
                        PathFilterInfo filterInfo = PathFilterDeal.map.get(info.filter);

                        StringBuilder paramSb = new StringBuilder();
                        StringBuilder filterSetSb = new StringBuilder();

                        Ts.ls(filterInfo.params, new BaseTs.EachTs<KV<String, String>>() {
                            @Override
                            public boolean each(int position, KV<String, String> kv) {
                                if (position != 0) {
                                    paramSb.append(", ");
                                }
                                paramSb.append(kv.k).append(" ").append(kv.v);

                                addLnTag(filterSetSb, "        filter.[type] = [type];", kv.v, kv.v);

                                return false;
                            }
                        });

                        addLnTag(sb, "    public [List]<[CheckLabelPath]> [label]List([params]) {"
                                , FullName.T_LIST_TS, info.javaName, info.fieldName, paramSb.toString());
                        addLnTag(sb, "        [LabelFilter] filter = new [LabelFilter]();", info.filter, info.filter);
                        addLnTag(sb, filterSetSb.toString());

                        addLnTag(sb, "        return [Ts].ts(new java.io.File(root()).listFiles()).convert((index, file) -> {", FullName.TS);
                        addLnTag(sb, "            if (filter.check(file.getName())) {");
                        addLnTag(sb, "                return [label](file.getName());", info.fieldName);
                        addLnTag(sb, "            }");
                        addLnTag(sb, "            return null;");
                        addLnTag(sb, "        });");
                        addLnTag(sb, "    }");
                    }

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
                    String fileClass = null;

                    if (!info.isVoidBean) {
                        fullName = FullName.PATH_BEAN_FILE;
                        fileClass = fullName + "<" + info.beanClass + ">";
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

                        fileClass = fullName;
                        paramLine = "                , \"[txt]\"";
                        paramParams = new Object[]{info.file.fileType()};
                    }

                    addLnTag(sb, "    public [PathTextFile] [handle_jpg](String [labelName]) {"
                            , fileClass, info.fieldFullName, cutParam);
                    addLnTag(sb, "        return new [PathBeanFile](", fullName);
                    addLnTag(sb, "                this.root");
                    addLnTag(sb, "                        + addPrexSeparator([labelName] + \"[.txt]\")", cutParam, info.fileType);
                    addLnTag(sb, paramLine, paramParams);
                    addLnTag(sb, "        );");
                    addLnTag(sb, "    }");


                    if (info.isFilter) {
                        PathFilterInfo filterInfo = PathFilterDeal.map.get(info.filter);

                        StringBuilder paramSb = new StringBuilder();
                        StringBuilder filterSetSb = new StringBuilder();

                        Ts.ls(filterInfo.params, new BaseTs.EachTs<KV<String, String>>() {
                            @Override
                            public boolean each(int position, KV<String, String> kv) {
                                if (position != 0) {
                                    paramSb.append(", ");
                                }
                                paramSb.append(kv.k).append(" ").append(kv.v);

                                addLnTag(filterSetSb, "        filter.[type] = [type];", kv.v, kv.v);

                                return false;
                            }
                        });

                        addLnTag(sb, "    public [TListTs]<[PathBeanFile<User>]> [label_txt]_list([String type]) {"
                                , FullName.T_LIST_TS, fileClass, info.fieldFullName, paramSb.toString());
                        addLnTag(sb, "        [LabelFilter] filter = new [LabelFilter]();", info.filter, info.filter);
                        addLnTag(sb, filterSetSb.toString());

                        addLnTag(sb, "        return [Ts].ts(new java.io.File(root()).listFiles()).convert((index, file) -> {", FullName.TS);
                        addLnTag(sb, "            if (filter.check(file.getName())) {");
                        addLnTag(sb, "                return [label_txt](file.getName());", info.fieldFullName);
                        addLnTag(sb, "            }");
                        addLnTag(sb, "            return null;");
                        addLnTag(sb, "        });");
                        addLnTag(sb, "    }");
                    }


                }
                return false;
            }
        });
    }
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
