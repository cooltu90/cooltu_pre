package com.codingtu.cooltu_pre;

import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Pkg;
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
        name = "step_one",
        packages = Constants.PKG_MODULE_APP + Pkg.DEFAULT_UI
)
public class App extends CoreApp {
    @Override
    public CoreConfigs createConfigs() {
        return new Configs();
    }
}
