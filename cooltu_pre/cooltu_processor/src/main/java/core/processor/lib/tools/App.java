package core.processor.lib.tools;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

import cooltu.lib4j.config.LibApp;
import cooltu.lib4j.config.LibConfigs;

public class App extends LibApp {

    private Messager messager;

    public App(Messager messager) {
        this.messager = messager;
    }

    public static void init(final Messager messager) {
        LibApp.APP = new App(messager);
    }

    @Override
    protected LibConfigs createConfigs() {
        return new LibConfigs() {
            @Override
            public boolean isLog() {
                return true;
            }

            @Override
            public String getDefaultLogTag() {
                return "logtag";
            }

            @Override
            public void baseLog(int level, String tag, String msg) {
                messager.printMessage(Diagnostic.Kind.NOTE, msg);
            }
        };
    }
}
