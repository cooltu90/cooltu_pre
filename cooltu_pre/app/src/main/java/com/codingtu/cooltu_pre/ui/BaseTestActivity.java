package com.codingtu.cooltu_pre.ui;

import com.codingtu.cooltu.processor.annotation.resource.ResForBase;
import com.codingtu.cooltu.processor.annotation.ui.InBaseClickView;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu_pre.R;

@ResForBase
public class BaseTestActivity extends CoreTestActivity {

    @StartGroup
    public long money;

    @StartGroup
    public int age;

    @InBaseClickView(R.id.tv1)
    public void tv1Click() {
        toast("tv1");
    }

}
