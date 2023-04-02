package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.processor.annotation.create.CreateAdapter;
import com.codingtu.cooltu.processor.lib.model.AdapterModels;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.LayoutModel;
import com.codingtu.cooltu.processor.worker.model.VHModel;
import com.codingtu.cooltu.processor.worker.model.base.BaseAdapterModel;

public class CreateAdapterDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        CreateAdapter createAdapter = element.getAnnotation(CreateAdapter.class);
        String packages = createAdapter.packages();
        String typeBaseName = ConvertTool.toClassType(createAdapter.name());
        //创建layout
        JavaInfo layoutInfo = new JavaInfo();
        layoutInfo.path = NameTools.getItemLayoutPath(ConvertTool.toLayoutType(typeBaseName));
        new LayoutModel(layoutInfo);
        //生成VH
        new VHModel(NameTools.getVHInfo(typeBaseName), typeBaseName, false);

        JavaInfo adapterInfo = NameTools.getAdapterInfo(packages, typeBaseName);
        BaseAdapterModel adapterModel = AdapterModels.getAdapterModel(createAdapter.type());
        adapterModel.setInfo(adapterInfo);
        adapterModel.setBeans(ClassTool.getAnnotationClasses(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return createAdapter.beanClasses();
            }
        }));
    }
}
