package core.lib4a;

import android.app.Application;

import androidx.annotation.NonNull;

import cooltu.lib4j.config.LibApp;
import cooltu.lib4j.config.LibConfigs;
import core.lib4a.log.Logs;

public abstract class CoreApp extends Application implements Thread.UncaughtExceptionHandler {

    //保存自己的实例
    public static CoreApp APP;

    @Override
    public void onCreate() {
        super.onCreate();
        APP = this;
        LibApp.APP = new LibApp() {
            @Override
            protected LibConfigs createConfigs() {
                return CoreApp.this.createConfigs();
            }
        };
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Logs.e(e);
    }

    public abstract CoreConfigs createConfigs();
}
