package core.processor.worker.deal;

import javax.lang.model.element.Element;

import cooltu.lib4j.tools.ClassTool;
import core.processor.annotation.ui.Adapter;
import core.processor.lib.model.AdapterModels;
import core.processor.lib.tools.ElementTools;
import core.processor.worker.deal.base.BaseDeal;
import core.processor.worker.model.BaseParentModel;
import core.processor.worker.model.base.BaseAdapterModel;

public class AdapterDeal extends BaseDeal {

    @Override
    public void deal(Element element) {
        Adapter adapter = element.getAnnotation(Adapter.class);
        String adapterFullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return adapter.value();
            }
        });
        int type = adapter.type();
        String rvName = adapter.rvName();

        if (!ClassTool.isVoid(adapterFullName)) {
            String classFullName = ElementTools.getType(element);
            BaseParentModel baseModel = getBaseParentModel(classFullName);
            if (baseModel != null) {
                BaseAdapterModel adapterModel = AdapterModels.getAdapterModel(type);
                adapterModel.setAdapter(adapterFullName);
                adapterModel.setRvName(rvName);
                adapterModel.setAdapterName("adapter");
                baseModel.setAdapter(adapterModel, "");
            }
        }
    }
}
