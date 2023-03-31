package core.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import core.constant.FullName;
import core.processor.annotation.ui.ActBase;
import core.processor.lib.model.ModelMap;
import core.processor.lib.tools.IdTools;
import core.processor.lib.tools.NameTools;
import core.processor.worker.ModelType;
import core.processor.worker.deal.base.BaseDeal;
import core.processor.worker.model.ActBaseModel;

public class ActBaseDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        final ActBase actBase = element.getAnnotation(ActBase.class);
        String baseClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return actBase.baseClass();
            }
        });
        if (ClassTool.isVoid(baseClass)) {
            baseClass = FullName.BASE_ACT;
        }

        IdTools.Id id = IdTools.elementToId(element, ActBase.class, actBase.layout());

        ActBaseModel actBaseModel = new ActBaseModel(getActBaseInfo((TypeElement) element));
        actBaseModel.setBaseClass(baseClass);
        actBaseModel.setLayout(id.rPackage, id.rName);
    }

    private static JavaInfo getActBaseInfo(TypeElement element) {
        return NameTools.getActBaseInfo(NameTools.getActivityTypeBaseName(element));
    }

    public static ActBaseModel getActBaseModel(TypeElement element) {
        return ModelMap.find(ModelType.actBase, getActBaseInfo(element).fullName);
    }

    public static ActBaseModel getActBaseModel(String actFullName) {
        return ModelMap.find(ModelType.actBase, NameTools.getActBaseInfoByActFullName(actFullName).fullName);
    }
}
