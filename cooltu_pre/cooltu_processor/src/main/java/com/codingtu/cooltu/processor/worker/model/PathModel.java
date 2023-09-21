package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.path.Path;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.lib.bean.PathInfo;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.modelinterface.PathModelInterface;
import com.codingtu.cooltu.processor.worker.deal.PathsDeal;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

import java.util.HashMap;
import java.util.List;

@To(PathsDeal.class)
public class PathModel extends BaseModel implements PathModelInterface {
    private String[] rootParts;
    private List<PathInfo> pathInfos;

    public PathModel(JavaInfo info) {
        super(info);
        isForce = true;
    }

    public void addRoot(String root) {
        rootParts = root.split("/");
    }

    public void addPathInfos(List<PathInfo> pathInfos) {
        this.pathInfos = pathInfos;
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
    public void setTagFor_SDCardToolFullName(StringBuilder sb) {
        sb.append(FullName.SDCARD_TOOL);
    }

    @Override
    public void setTagFor_className(StringBuilder sb) {
        sb.append(info.name);
    }

    @Override
    public void setTagFor_BasePath(StringBuilder sb) {
        sb.append(FullName.BASE_PATH);
    }

    @Override
    public void setTagFor_fields(StringBuilder sb) {
        for (int i = 0; i < CountTool.count(pathInfos); i++) {
            PathInfo pathInfo = pathInfos.get(i);
            Path path = pathInfo.path;
            if (StringTool.isBlank(path.fileType())) {
                addLnTag(sb, "    public String [name];", pathInfo.kv.v);
            } else {
                addLnTag(sb, "    public [pathBean] [name]_[type];", FullName.PATH_BEAN, pathInfo.kv.v, path.fileType());
            }
        }
    }

    @Override
    public void setTagFor_rootParams(StringBuilder sb) {
        int index = 0;
        HashMap<String, String> nameMap = new HashMap<>();
        for (int i = 0; i < CountTool.count(rootParts); i++) {
            String rootPart = rootParts[i];
            if (StringTool.isNotBlank(rootPart)) {
                if (rootPart.startsWith("{") && rootPart.endsWith("}")) {
                    rootPart = rootPart.substring(1, rootPart.length() - 1);
                    if (StringTool.isBlank(nameMap.get(rootPart))) {
                        if (index == 0) {
                            sb.append("String ");
                        } else {
                            sb.append(", String ");
                        }
                        sb.append(rootPart);
                        nameMap.put(rootPart, rootPart);
                        index++;
                    }
                }
            }
        }
    }

    @Override
    public void setTagFor_rootPaths(StringBuilder sb) {
        for (int i = 0; i < CountTool.count(rootParts); i++) {
            String rootPart = rootParts[i];
            if (StringTool.isNotBlank(rootPart)) {
                if (rootPart.startsWith("{") && rootPart.endsWith("}")) {
                    rootPart = rootPart.substring(1, rootPart.length() - 1);
                    addLnTag(sb, "                + addPrexSeparator([part])", rootPart);
                } else {
                    addLnTag(sb, "                + addPrexSeparator(\"[part]\")", rootPart);
                }
            }
        }
    }

    @Override
    public void setTagFor_pathsInit(StringBuilder sb) {
        Ts.ls(pathInfos, new BaseTs.EachTs<PathInfo>() {
            @Override
            public boolean each(int position, PathInfo pathInfo) {
                boolean isDir = StringTool.isBlank(pathInfo.path.fileType());
                String[] split = pathInfo.path.value().split("/");
                if (isDir) {
                    addLnTag(sb, "        this.[ExtraInfo] =", pathInfo.kv.v);
                } else {
                    addLnTag(sb, "        this.[weather]_[txt] = new [PathBean](", pathInfo.kv.v, pathInfo.path.fileType(), FullName.PATH_BEAN);
                }

                Ts.ls(split, new BaseTs.EachTs<String>() {
                    @Override
                    public boolean each(int position, String part) {
                        if (part.startsWith("{") && part.endsWith("}")) {
                            part = part.substring(1, part.length() - 1);
                            addLnTag(sb, "                this.[root]", part);
                        } else {
                            addLnTag(sb, "                        + addPrexSeparator(\"[ex]\")", part);
                        }
                        return false;
                    }
                });


                if (isDir) {
                    addLnTag(sb, "                        + addPrexSeparator(\"[weather]\")", pathInfo.kv.v);
                    addLnTag(sb, "        ;");
                } else {
                    addLnTag(sb, "                        + addPrexSeparator(\"[weather].[txt]\")", pathInfo.kv.v, pathInfo.path.fileType());
                    addLnTag(sb, "                , \"[txt]\"", pathInfo.path.fileType());
                    addLnTag(sb, "        );");
                }

                return false;
            }
        });
    }

}
/* model_temp_start
package [[pkg]];

import [[SDCardToolFullName]];

public class [[className]] extends [[BasePath]] {

    public String root;
[[fields]]

    public static [[className]] obtain([[rootParams]]) {
        return root(SDCardTool.getSDCard()
[[rootPaths]]
        );
    }

    public static [[className]] root(String root) {
        return new [[className]](root);
    }

    public [[className]](String root) {
        this.root = root;
[[pathsInit]]
    }
}
model_temp_end */
