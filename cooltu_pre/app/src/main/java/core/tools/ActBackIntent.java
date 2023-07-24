package core.tools;

import android.content.Intent;

public class ActBackIntent {

    public static Intent stepOne(int age) {
        Intent intent = new Intent();
        intent.putExtra(Pass.AGE, age);
        return intent;
    }


}

