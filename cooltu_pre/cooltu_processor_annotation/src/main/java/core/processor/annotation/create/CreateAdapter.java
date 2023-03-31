package core.processor.annotation.create;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface CreateAdapter {
    String name();

    String packages();

    int type();

    Class[] beanClasses();
}
