package core.actres;

import android.view.ViewDebug;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.view.dialogview.Dialog;
import com.codingtu.cooltu.lib4a.view.dialogview.EditDialog;
import com.codingtu.cooltu.processor.annotation.resource.ColorRes;
import com.codingtu.cooltu.processor.annotation.resource.ColorStr;
import com.codingtu.cooltu.processor.annotation.resource.Dimen;
import com.codingtu.cooltu.processor.annotation.resource.Dp;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.annotation.ui.dialog.DialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu_pre.R;
import com.codingtu.cooltu_pre.ui.TestActivity;
import com.codingtu.cooltu.processor.annotation.resource.ResFor;

@ResFor(TestActivity.class)
public class TestActivityRes {

    @ColorStr("#ffffff")
    public int color11;
    @ColorRes(R.color.purple_200)
    public int color22;
    @Dp(2.6f)
    public int dp11;
    @Dimen(R.dimen.d1)
    public int d1;

    @DialogUse(
            title = "dialog",
            content = "xxxx",
            objType = Void.class
    )
    Dialog dialog;


    @EditDialogUse(
            title = "提示",
            hint = "请输入",
            stopAnimation = true,
            objType = Void.class
    )
    EditDialog editDialog;

}

