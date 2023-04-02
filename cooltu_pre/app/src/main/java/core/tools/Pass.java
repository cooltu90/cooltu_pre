package core.tools;

import android.content.Intent;

public class Pass {
    public static final String FROM_ACT = com.codingtu.cooltu.constant.Constant.FROM_ACT;


    public static final int fromAct(Intent data) {
        return data.getIntExtra(FROM_ACT, -1);
    }


}
