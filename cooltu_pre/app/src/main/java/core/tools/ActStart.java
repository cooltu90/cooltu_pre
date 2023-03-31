package core.tools;

import android.app.Activity;
import android.content.Intent;

public class ActStart {

    public static final void welcomeActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu_pre.ui.WelcomeActivity.class);
        intent.putExtra(Pass.FROM_ACT,Code4Request.WELCOME_ACTIVITY);

        core.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.WELCOME_ACTIVITY);
    }


}
