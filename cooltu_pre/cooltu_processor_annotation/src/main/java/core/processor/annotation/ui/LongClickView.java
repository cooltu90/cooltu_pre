package core.processor.annotation.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface LongClickView {
    int[] value();

    boolean inAct() default true;

    boolean check() default false;

    boolean checkLogin() default false;

}

