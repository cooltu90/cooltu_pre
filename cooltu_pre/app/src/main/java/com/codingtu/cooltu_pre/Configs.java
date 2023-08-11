package com.codingtu.cooltu_pre;

import com.codingtu.cooltu.lib4a.CoreConfigs;
import com.codingtu.cooltu.lib4a.connect.CoreConnectConfigs;
import com.codingtu.cooltu_pre.connect.ConnectConfigs;

public class Configs extends CoreConfigs {
    @Override
    public String getBaseUrl() {
        return null;
    }

    @Override
    public String getImageGetterFileProvider() {
        return null;
    }

    @Override
    public boolean isLog() {
        return true;
    }

    @Override
    public String getDefaultLogTag() {
        return "logtag";
    }


    @Override
    public CoreConnectConfigs getConnectConfigs() {
        return new ConnectConfigs();
    }
}
