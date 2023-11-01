package core.tools;

import android.app.Activity;
import android.content.Intent;

public class ActStart {

    public static final void stepTwoActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu_pre.ui.StepTwoActivity.class);
        intent.putExtra(Pass.FROM_ACT,Code4Request.STEP_TWO_ACTIVITY);

        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.STEP_TWO_ACTIVITY);
    }
    public static final void threeActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu_pre.ui.ThreeActivity.class);
        intent.putExtra(Pass.FROM_ACT,Code4Request.THREE_ACTIVITY);

        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.THREE_ACTIVITY);
    }
    public static final void connectActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu_pre.ui.ConnectActivity.class);
        intent.putExtra(Pass.FROM_ACT,Code4Request.CONNECT_ACTIVITY);

        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.CONNECT_ACTIVITY);
    }
    public static final void viewActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu_pre.ui.ViewActivity.class);
        intent.putExtra(Pass.FROM_ACT,Code4Request.VIEW_ACTIVITY);

        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.VIEW_ACTIVITY);
    }
    public static final void stepOneActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu_pre.ui.StepOneActivity.class);
        intent.putExtra(Pass.FROM_ACT,Code4Request.STEP_ONE_ACTIVITY);

        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.STEP_ONE_ACTIVITY);
    }
    public static final void testActivity(Activity act, java.lang.String id,java.lang.String name,long money,int age) {
        Intent intent = new Intent(act, com.codingtu.cooltu_pre.ui.TestActivity.class);
        intent.putExtra(Pass.FROM_ACT,Code4Request.TEST_ACTIVITY);
        intent.putExtra(Pass.ID, id);
        intent.putExtra(Pass.NAME, name);
        intent.putExtra(Pass.MONEY, money);
        intent.putExtra(Pass.AGE, age);

        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.TEST_ACTIVITY);
    }
    public static final void formActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu_pre.ui.FormActivity.class);
        intent.putExtra(Pass.FROM_ACT,Code4Request.FORM_ACTIVITY);

        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.FORM_ACTIVITY);
    }
    public static final void newFormActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu_pre.ui.NewFormActivity.class);
        intent.putExtra(Pass.FROM_ACT,Code4Request.NEW_FORM_ACTIVITY);

        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.NEW_FORM_ACTIVITY);
    }
    public static final void welcomeActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu_pre.ui.WelcomeActivity.class);
        intent.putExtra(Pass.FROM_ACT,Code4Request.WELCOME_ACTIVITY);

        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.WELCOME_ACTIVITY);
    }


}
