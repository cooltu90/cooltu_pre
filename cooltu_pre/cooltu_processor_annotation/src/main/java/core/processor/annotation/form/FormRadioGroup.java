package core.processor.annotation.form;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface FormRadioGroup {
    Class onSetItem() default Void.class;

    String onSetItemName() default "";
}
