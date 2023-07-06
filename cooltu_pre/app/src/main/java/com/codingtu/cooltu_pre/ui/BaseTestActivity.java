package com.codingtu.cooltu_pre.ui;

import com.codingtu.cooltu.processor.annotation.resource.ResForBase;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.InBaseClickView;
import com.codingtu.cooltu.processor.annotation.ui.InBaseStartGroup;
import com.codingtu.cooltu_pre.R;

@ResForBase
public class BaseTestActivity extends CoreTestActivity {

    @InBaseStartGroup
    public long money;

    @InBase
    public int age;

    @InBaseClickView(R.id.tv1)
    public void tv1Click() {
        toast("tv1");
    }

}
