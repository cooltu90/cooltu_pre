package core.tools;

import android.content.Intent;

public class ActBackIntent {

    public static Intent view(java.lang.String name) {
        Intent intent = new Intent();
        intent.putExtra(Pass.NAME, name);
        return intent;
    }
    public static Intent stepOne(int age) {
        Intent intent = new Intent();
        intent.putExtra(Pass.AGE, age);
        return intent;
    }


}

