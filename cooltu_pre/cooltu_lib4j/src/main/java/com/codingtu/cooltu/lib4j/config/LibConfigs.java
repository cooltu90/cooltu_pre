package com.codingtu.cooltu.lib4j.config;

import com.codingtu.cooltu.lib4j.json.base.JsonHolder;
import com.codingtu.cooltu.lib4j.json.fastjson.FastJsonHolder;

public abstract class LibConfigs {

    public static LibConfigs CONFIGS;

    public static LibConfigs configs() {
        if (CONFIGS == null) {
            CONFIGS = LibApp.APP.createConfigs();
        }
        return CONFIGS;
    }

    public JsonHolder createJsonHolder() {
        return new FastJsonHolder();
    }

    public abstract boolean isLog();

    public abstract boolean isLogHttpConnect();

    public abstract String getDefaultLogTag();

    public abstract void baseLog(int level, String tag, String msg);
}
