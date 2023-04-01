package core.processor.worker.deal;

import javax.lang.model.element.Element;

import cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Pkg;
import core.processor.annotation.ModuleInfo;
import core.processor.worker.deal.base.BaseDeal;
import core.processor.worker.model.ActBackIntentModel;
import core.processor.worker.model.BusSendModel;
import core.processor.worker.model.Code4RequestModel;
import core.processor.worker.model.PassModel;
import core.processor.worker.model.PermissionModel;
import core.processor.worker.model.StartModel;
import core.processor.worker.model.mock.MockModel;
import core.processor.worker.model.net_retrofit.NetModel;

public class ModuleInfoDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        final ModuleInfo info = element.getAnnotation(ModuleInfo.class);
        Module.CURRENT = info.module();
        FullName.BASE_ACT = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return info.baseAct();
            }
        });
        FullName.BASE_FRAGMENT = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return info.baseFragment();
            }
        });
        Pkg.DEFAULT_R = info.defaultRPackage();
        Pkg.MOCK = Pkg.DEFAULT_R + info.mockPackage();
        Constant.DEFAULT_TEMP_LAYOUT = info.defaultLayout();

        PassModel passModel = PassModel.model;
        StartModel startModel = StartModel.model;
        Code4RequestModel code4RequestModel = Code4RequestModel.model;
        NetModel netModel1 = NetModel.model;
        PermissionModel permissionModel = PermissionModel.model;
        MockModel mockModel = MockModel.model;
        ActBackIntentModel actBackIntentModel = ActBackIntentModel.model;
        BusSendModel busSendModel = BusSendModel.model;
    }
}
