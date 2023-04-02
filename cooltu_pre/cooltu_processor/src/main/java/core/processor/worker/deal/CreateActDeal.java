package core.processor.worker.deal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;

import java.io.File;

import javax.lang.model.element.Element;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.ConvertTool;
import core.processor.annotation.create.CreateAct;
import core.processor.lib.tools.NameTools;
import core.processor.worker.deal.base.BaseDeal;
import core.processor.worker.model.ActBaseModel;
import core.processor.worker.model.CreateActModel;
import core.processor.worker.model.LayoutModel;
import core.processor.worker.model.ManifestModel;
import core.processor.worker.model.ResModel;
import core.processor.worker.model.StartModel;

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
