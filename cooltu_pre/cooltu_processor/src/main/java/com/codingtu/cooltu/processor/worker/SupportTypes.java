package com.codingtu.cooltu.processor.worker;

import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;
import com.codingtu.cooltu.processor.annotation.create.CreateAdapter;
import com.codingtu.cooltu.processor.annotation.create.CreateFragment;
import com.codingtu.cooltu.processor.annotation.delete.DeleteAct;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.form.FormItem;
import com.codingtu.cooltu.processor.annotation.net.Apis;
import com.codingtu.cooltu.processor.annotation.net.NetBack;
import com.codingtu.cooltu.processor.annotation.permission.Permission;
import com.codingtu.cooltu.processor.annotation.rename.RenameAct;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.resource.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.processor.annotation.ui.Base;
import com.codingtu.cooltu.processor.annotation.ui.BusBack;
import com.codingtu.cooltu.processor.annotation.ui.BusConfig;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.DefaultCode;
import com.codingtu.cooltu.processor.annotation.ui.DefaultDialogLayout;
import com.codingtu.cooltu.processor.annotation.ui.DefaultEditDialogLayout;
import com.codingtu.cooltu.processor.annotation.ui.DefaultPass;
import com.codingtu.cooltu.processor.annotation.ui.DefaultToastDialogLayout;
import com.codingtu.cooltu.processor.annotation.ui.FragmentBase;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.LongClickView;
import com.codingtu.cooltu.processor.annotation.ui.UseDialog;
import com.codingtu.cooltu.processor.annotation.ui.UseEditDialog;
import com.codingtu.cooltu.processor.annotation.ui.VH;

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
                Base.class,
                DeleteAct.class,
                RenameAct.class,
                CreateAct.class,
                CreateFragment.class,
                CreateAdapter.class,
                ActBase.class,
                FragmentBase.class,
                BusConfig.class,
                ResFor.class,
                InBase.class,
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
