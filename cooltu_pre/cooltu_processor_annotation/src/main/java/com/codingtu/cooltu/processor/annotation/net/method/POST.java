package com.codingtu.cooltu.processor.annotation.net.method;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface POST {
    String value();

    String baseUrl() default "";

    boolean isJsonBody() default false;
}
