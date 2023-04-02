package com.codingtu.cooltu.processor.annotation.tools;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***************************************
 *
 * 只作为标记，可以在代码中快速定位到相应的Mock类，没有实际作用
 *
 ***************************************/
@Retention(RetentionPolicy.SOURCE)
public @interface Mock {
    Class value() default Void.class;
}
