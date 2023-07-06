package core.tools;

import android.content.Intent;

public class Pass {
    public static final String FROM_ACT = com.codingtu.cooltu.constant.Constant.FROM_ACT;
    public static final String MONEY = "money";
    public static final String AGE = "age";
    public static final String ID = "id";
    public static final String NAME = "name";


    public static final int fromAct(Intent data) {
        return data.getIntExtra(FROM_ACT, -1);
    }
    public static final long money(Intent data) {
        return data.getLongExtra(MONEY, -1);
    }
    public static final int age(Intent data) {
        return data.getIntExtra(AGE, -1);
    }
    public static final String id(Intent data) {
        return data.getStringExtra(ID);
    }
    public static final String name(Intent data) {
        return data.getStringExtra(NAME);
    }


}
