package core.processor.worker.deal;

import java.io.File;

import javax.lang.model.element.Element;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import core.constant.FullName;
import core.constant.Pkg;
import core.processor.annotation.create.CreateFragment;
import core.processor.lib.tools.NameTools;
import core.processor.worker.deal.base.BaseDeal;
import core.processor.worker.model.CreateFragmentModel;
import core.processor.worker.model.FragmentBaseModel;
import core.processor.worker.model.LayoutModel;
import core.processor.worker.model.ResModel;

public class CreateFragmentDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        //获取基本信息
        final CreateFragment createAct = element.getAnnotation(CreateFragment.class);
        String layoutBaseName = createAct.name();
        String packages = createAct.packages();
        JavaInfo fragmentInfo = NameTools.getFragmentInfo(packages, layoutBaseName);
        File file = new File(fragmentInfo.path);
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
            base = FullName.BASE_FRAGMENT;
        }

        //创建layout
        JavaInfo layoutInfo = new JavaInfo();
        layoutInfo.path = NameTools.getFragmentLayoutPath(layoutBaseName);
        new LayoutModel(layoutInfo);

        //创建FragmentBase
        JavaInfo fragmentBaseInfo = NameTools.getFragmentBaseInfo(layoutBaseName);
        FragmentBaseModel fragmentBaseModel = new FragmentBaseModel(fragmentBaseInfo);
        fragmentBaseModel.setBaseClass(base);
        fragmentBaseModel.setLayout(Pkg.DEFAULT_R, NameTools.getFragmentLayoutName(layoutBaseName));

        //创建Res
        JavaInfo actResInfo = NameTools.getFragmentResInfo(layoutBaseName);
        new ResModel(actResInfo,fragmentInfo);

        //创建Fragment
        new CreateFragmentModel(fragmentInfo, layoutBaseName, fragmentBaseInfo);

    }
}
