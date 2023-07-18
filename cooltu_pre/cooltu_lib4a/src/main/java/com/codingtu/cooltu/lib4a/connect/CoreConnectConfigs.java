package com.codingtu.cooltu.lib4a.connect;

import com.codingtu.cooltu.lib4a.CoreConfigs;

public class CoreConnectConfigs {

    private static CoreConnectConfigs CONFIGS;

    public static CoreConnectConfigs configs() {
        if (CONFIGS == null) {
            CONFIGS = CoreConfigs.configs().getConnectConfigs();
        }
        return CONFIGS;
    }

}
