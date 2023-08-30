package com.codingtu.cooltu_pre.ui;

import com.codingtu.cooltu.lib4a.view.dialogview.EditDialog;
import com.codingtu.cooltu.processor.annotation.resource.ResForBase;
import com.codingtu.cooltu.processor.annotation.ui.InBaseClickView;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.NoticeDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.ToastDialogUse;
import com.codingtu.cooltu_pre.R;

@NoticeDialogUse
@ResForBase
public class BaseTestActivity extends CoreTestActivity {

    @StartGroup
    public long money;

    @StartGroup
    public int age;

    @EditDialogUse(
            title = "提示",
            hint = "请输入"
    )
    protected EditDialog ed1;

    @InBaseClickView(R.id.tv1)
    public void tv1Click() {
        toast("tv1");
    }

}
