package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.data.bean.KV;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.path.Path;
import com.codingtu.cooltu.processor.annotation.path.Paths;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.lib.bean.PathInfo;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.PathModel;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

@To(PathModel.class)
public class PathsDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        String baseName = StringTool.cutSuffix(ElementTools.simpleName(element), Suffix.PATH_CONFIGS);
        JavaInfo pathJavaInfo = NameTools.getPathInfo(baseName);
        PathModel pathModel = new PathModel(pathJavaInfo);
        Paths paths = element.getAnnotation(Paths.class);
        pathModel.addRoot(paths.value());


        TypeElement te = (TypeElement) element;
        List<PathInfo> pathInfos = Ts.ts(te.getEnclosedElements()).convert((index, e) -> {
            if (e instanceof VariableElement) {
                Path path = e.getAnnotation(Path.class);
                if (path != null) {
                    PathInfo pathInfo = new PathInfo();
                    pathInfo.path = path;
                    pathInfo.kv = ElementTools.getFiledKv((VariableElement) e);
                    return pathInfo;
                }
            }
            return null;
        }).get();

        pathModel.addPathInfos(pathInfos);


    }
}
