package core.actres;

import android.widget.TextView;

import com.codingtu.cooltu.processor.annotation.resource.ColorRes;
import com.codingtu.cooltu.processor.annotation.resource.ColorStr;
import com.codingtu.cooltu.processor.annotation.resource.Dimen;
import com.codingtu.cooltu.processor.annotation.resource.Dp;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
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

}

