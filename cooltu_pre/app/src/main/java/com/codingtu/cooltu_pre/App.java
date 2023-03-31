package com.codingtu.cooltu_pre;

import core.constant.Module;
import core.constant.Pkg;
import core.lib4a.CoreApp;
import core.lib4a.CoreConfigs;
import core.lib4a.act.CoreActivity;
import core.lib4a.fragment.CoreFragment;
import core.processor.annotation.ModuleInfo;
import core.processor.annotation.create.CreateAct;

@ModuleInfo(
        module = Module.APP,
        baseAct = CoreActivity.class,
        baseFragment = CoreFragment.class,
        defaultRPackage = Constants.PKG_MODULE_APP,
        mockPackage = Pkg.DEFAULT_NET_MOCK,
        defaultLayout = Constants.LAYOUT_NAME_DEFAULT_TEMP
)
@CreateAct(
        name = "welcome",
        packages = Constants.PKG_MODULE_APP + Pkg.DEFAULT_UI
)
public class App extends CoreApp {
    @Override
    public CoreConfigs createConfigs() {
        return new Configs();
    }
}
