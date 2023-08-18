package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;

import java.io.File;

import javax.lang.model.element.Element;

import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.ActBaseModel;
import com.codingtu.cooltu.processor.worker.model.CreateActModel;
import com.codingtu.cooltu.processor.worker.model.LayoutModel;
import com.codingtu.cooltu.processor.worker.model.ManifestModel;
import com.codingtu.cooltu.processor.worker.model.ResModel;
import com.codingtu.cooltu.processor.worker.model.StartModel;

public class CreateActDeal extends BaseDeal {

    @Override
    public void deal(Element element) {
        //获取基本信息
        final CreateAct createAct = element.getAnnotation(CreateAct.class);
        String layoutBaseName = createAct.name();
        String packages = createAct.packages();
        JavaInfo actInfo = NameTools.getActInfo(packages, layoutBaseName);
        File file = new File(actInfo.path);
        if (file.exists()) {
            return;
        }

        String base = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return createAct.baseClass();
            }
        });
        if (ClassTool.isVoid(base)) {
            base = FullName.BASE_ACT;
        }

        //创建layout
        JavaInfo layoutInfo = new JavaInfo();
        layoutInfo.path = NameTools.getActivityLayoutPath(layoutBaseName);
        new LayoutModel(layoutInfo);

        JavaInfo actBaseInfo = NameTools.getActBaseInfo(layoutBaseName);
        //创建ActBase
        ActBaseModel actBaseModel = new ActBaseModel(actBaseInfo);
        actBaseModel.setBaseClass(base);
        actBaseModel.setLayout(Pkg.DEFAULT_R, NameTools.getActivityLayoutName(layoutBaseName));

        //创建Res
        JavaInfo actResInfo = NameTools.getActResInfo(layoutBaseName);
        new ResModel(actResInfo,actInfo);

        //创建activity
        new CreateActModel(actInfo, layoutBaseName, actBaseInfo);

        //添加Manifest
        JavaInfo manifestInfo = new JavaInfo();
        manifestInfo.path = NameTools.getManifestPath();
        new ManifestModel(manifestInfo).setAct(actInfo.fullName, createAct.screenOrientation());

        //添加ActStart
        StartModel.model.addStart(actInfo.fullName, ConvertTool.toStaticType(actInfo.name), null);
    }

}
