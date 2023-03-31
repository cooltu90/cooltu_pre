package core.processor.annotation.ui;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface FragmentBase {
    int layout();

    Class baseClass() default Void.class;
}