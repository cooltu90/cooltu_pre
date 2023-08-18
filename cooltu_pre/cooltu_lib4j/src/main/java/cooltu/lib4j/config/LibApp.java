package cooltu.lib4j.config;

import cooltu.lib4j.log.LibLogs;

public abstract class LibApp {

    public static LibApp APP;

    protected abstract LibConfigs createConfigs();

}
