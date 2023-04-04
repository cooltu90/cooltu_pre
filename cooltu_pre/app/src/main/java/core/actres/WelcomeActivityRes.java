package core.actres;

import com.codingtu.cooltu.lib4a.view.dialogview.EditDialog;
import com.codingtu.cooltu.processor.annotation.resource.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu_pre.ui.WelcomeActivity;

@ResFor(WelcomeActivity.class)
public class WelcomeActivityRes {

    @EditDialogUse(
            hint = "xxx",
            title = "xxx",
            isUseTextWatcher = true
    )
    public EditDialog ed;

}

