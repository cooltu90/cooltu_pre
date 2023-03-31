package core.processor.worker.deal;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import core.processor.annotation.ui.UseDialog;
import core.processor.lib.bean.DialogInfo;
import core.processor.lib.tools.NameTools;
import core.processor.worker.deal.base.BaseDeal;
import core.processor.worker.model.BaseParentModel;

public class UseDialogDeal extends BaseDeal {

    BaseParentModel baseModel;

    @Override
    public void deal(Element element) {
        UseDialog info = element.getAnnotation(UseDialog.class);
        String[] names = info.names();
        String[] titles = info.titles();
        String[] contents = info.contents();
        List<String> objTypes = ClassTool.getAnnotationClasses(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return info.objTypes();
            }
        });


        if (NameTools.isActivity((TypeElement) element)) {
            baseModel = ActBaseDeal.getActBaseModel((TypeElement) element);
        } else if (NameTools.isFragment((TypeElement) element)) {
            baseModel = FragmentBaseDeal.getFragmentBaseModel((TypeElement) element);
        }

        Ts.ls(names, new Each<String>() {
            @Override
            public boolean each(int position, String name) {
                DialogInfo dialogInfo = new DialogInfo();
                dialogInfo.name = name;
                dialogInfo.title = titles[position];
                dialogInfo.content = contents[position];
                dialogInfo.objType = objTypes.get(position);
                baseModel.addDialog(dialogInfo);
                return false;
            }
        });

    }
}
