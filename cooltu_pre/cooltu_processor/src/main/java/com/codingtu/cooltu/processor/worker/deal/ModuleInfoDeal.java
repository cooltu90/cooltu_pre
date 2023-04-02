package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;

import cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.ActBackIntentModel;
import com.codingtu.cooltu.processor.worker.model.BusSendModel;
import com.codingtu.cooltu.processor.worker.model.Code4RequestModel;
import com.codingtu.cooltu.processor.worker.model.PassModel;
import com.codingtu.cooltu.processor.worker.model.PermissionModel;
import com.codingtu.cooltu.processor.worker.model.StartModel;
import com.codingtu.cooltu.processor.worker.model.mock.MockModel;
import com.codingtu.cooltu.processor.worker.model.net_retrofit.NetModel;

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
