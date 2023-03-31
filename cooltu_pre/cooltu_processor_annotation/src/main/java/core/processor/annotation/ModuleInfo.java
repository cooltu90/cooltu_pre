package core.processor.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ModuleInfo {
    //模块名
    String module();

    //基础的Activity
    Class baseAct();

    //基础的Fragment
    Class baseFragment();

    //默认的R的包名
    String defaultRPackage();

    //mock文件的包名
    String mockPackage();

    //默认的layout模板
    String defaultLayout();

}
