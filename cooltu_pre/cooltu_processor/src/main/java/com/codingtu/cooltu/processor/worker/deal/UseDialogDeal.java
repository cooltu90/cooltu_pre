package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.ui.UseDialog;
import com.codingtu.cooltu.processor.lib.bean.DialogInfo;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.BaseParentModel;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

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

        Ts.ls(names, new BaseTs.EachTs<String>() {
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
