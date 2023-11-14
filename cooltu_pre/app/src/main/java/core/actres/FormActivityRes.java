package core.actres;

import com.codingtu.cooltu.lib4a.constant.InputType;
import com.codingtu.cooltu.lib4a.view.dialogview.EditDialog;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu_pre.ui.FormActivity;
import com.codingtu.cooltu.processor.annotation.resource.ResFor;

@ResFor(FormActivity.class)
public class FormActivityRes {

    @EditDialogUse(
            title = "输入金额 ",
            hint = "请输入金额",
            inputType = InputType.INT
    )
    EditDialog editDialog;

}

