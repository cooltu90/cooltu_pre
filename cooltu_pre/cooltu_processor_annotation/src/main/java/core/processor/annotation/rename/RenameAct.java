package core.processor.annotation.rename;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface RenameAct {
    String oldName();

    String newName();

    String oldPackages();

    String newPackages();
}
