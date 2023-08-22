package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.ui.UseEditDialog;
import com.codingtu.cooltu.processor.lib.bean.EditDialogInfo;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.BaseParentModel;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public class UseEditDialogDeal extends BaseDeal {
    private BaseParentModel baseModel;

    @Override
    public void deal(Element element) {
        UseEditDialog dialog = element.getAnnotation(UseEditDialog.class);
        String[] names = dialog.names();
        String[] titles = dialog.titles();
        String[] hints = dialog.hints();
        int[] inputTypes = dialog.inputTypes();
        boolean[] reserves = dialog.reserves();
        List<String> objTypes = ClassTool.getAnnotationClasses(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return dialog.objTypes();
            }
        });


        if (NameTools.isActivity((TypeElement) element)) {
            baseModel = ActBaseDeal.getActBaseModel((TypeElement) element);
        } else if (NameTools.isFragment((TypeElement) element)) {
            baseModel = FragmentBaseDeal.getFragmentBaseModel((TypeElement) element);
        }

        Ts.ls(names, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int i, String name) {
                EditDialogInfo info = new EditDialogInfo();
                info.name = name;
                info.title = titles[i];
                info.hint = hints[i];
                info.inputType = inputTypes[i];
                info.reserve = reserves[i];
                info.objType = objTypes.get(i);
                baseModel.addEditDialog(info);
                return false;
            }
        });

    }
}
