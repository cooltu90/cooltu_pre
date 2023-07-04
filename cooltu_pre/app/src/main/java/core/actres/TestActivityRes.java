package core.actres;

import android.widget.TextView;

import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu_pre.ui.TestActivity;
import com.codingtu.cooltu.processor.annotation.resource.ResFor;

@ResFor(TestActivity.class)
public class TestActivityRes {

    @InBase
    @StartGroup
    public String name;

    @InBase
    public TextView tv;

}

