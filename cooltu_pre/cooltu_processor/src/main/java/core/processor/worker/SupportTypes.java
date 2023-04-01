package core.processor.worker;

import core.processor.annotation.ModuleInfo;
import core.processor.annotation.create.CreateAct;
import core.processor.annotation.create.CreateAdapter;
import core.processor.annotation.create.CreateFragment;
import core.processor.annotation.delete.DeleteAct;
import core.processor.annotation.form.Form;
import core.processor.annotation.form.FormBean;
import core.processor.annotation.form.FormItem;
import core.processor.annotation.net.Apis;
import core.processor.annotation.net.NetBack;
import core.processor.annotation.permission.Permission;
import core.processor.annotation.rename.RenameAct;
import core.processor.annotation.ui.ActBack;
import core.processor.annotation.ui.ActBase;
import core.processor.annotation.ui.ActRes;
import core.processor.annotation.ui.Adapter;
import core.processor.annotation.ui.BusBack;
import core.processor.annotation.ui.BusConfig;
import core.processor.annotation.ui.ClickView;
import core.processor.annotation.ui.DefaultCode;
import core.processor.annotation.ui.DefaultDialogLayout;
import core.processor.annotation.ui.DefaultEditDialogLayout;
import core.processor.annotation.ui.DefaultPass;
import core.processor.annotation.ui.DefaultToastDialogLayout;
import core.processor.annotation.ui.FragmentBase;
import core.processor.annotation.ui.LongClickView;
import core.processor.annotation.ui.UseDialog;
import core.processor.annotation.ui.UseEditDialog;
import core.processor.annotation.ui.VH;

/**************************************************
 *
 * 此处的顺序决定了读取顺序
 *
 **************************************************/
public class SupportTypes {
    public static Class[] types() {
        return new Class[]{
                ModuleInfo.class,
                DefaultDialogLayout.class,
                DefaultToastDialogLayout.class,
                DefaultEditDialogLayout.class,
                DefaultCode.class,
                DefaultPass.class,
                DeleteAct.class,
                RenameAct.class,
                CreateAct.class,
                CreateFragment.class,
                CreateAdapter.class,
                ActBase.class,
                FragmentBase.class,
                BusConfig.class,
                ActRes.class,
                ClickView.class,
                LongClickView.class,
                BusBack.class,
                Form.class,
                FormBean.class,
                FormItem.class,
                NetBack.class,
                ActBack.class,
                UseDialog.class,
                UseEditDialog.class,
                Permission.class,
                Adapter.class,
                VH.class,
                Apis.class
        };
    }

}
