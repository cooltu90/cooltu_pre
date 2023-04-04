package com.codingtu.cooltu_pre;

import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Pkg;

import com.codingtu.cooltu.lib4a.CoreApp;
import com.codingtu.cooltu.lib4a.CoreConfigs;
import com.codingtu.cooltu.lib4a.act.CoreActivity;
import com.codingtu.cooltu.lib4a.fragment.CoreFragment;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;
import com.codingtu.cooltu.processor.annotation.ui.DefaultEditDialogLayout;

@ModuleInfo(
        module = Module.APP,
        baseAct = CoreActivity.class,
        baseFragment = CoreFragment.class,
        defaultRPackage = Constants.PKG_MODULE_APP,
        mockPackage = Pkg.DEFAULT_NET_MOCK,
        defaultLayout = Constants.LAYOUT_NAME_DEFAULT_TEMP
)
@DefaultEditDialogLayout(R.layout.dialog_et)
@CreateAct(
        name = "three",
        packages = Constants.PKG_MODULE_APP + Pkg.DEFAULT_UI
)
public class App extends CoreApp {
    @Override
    public CoreConfigs createConfigs() {
        return new Configs();
    }
}
