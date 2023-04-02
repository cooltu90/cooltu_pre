package core.processor.lib.tools;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

import cooltu.lib4j.config.LibApp;
import cooltu.lib4j.config.LibConfigs;

public class App extends LibApp {

    private Messager messager;
    private ProcessingEnvironment processingEnv;

    public App(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        this.messager = processingEnv.getMessager();
    }

    public static void init(ProcessingEnvironment processingEnv) {
        LibApp.APP = new App(processingEnv);
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
