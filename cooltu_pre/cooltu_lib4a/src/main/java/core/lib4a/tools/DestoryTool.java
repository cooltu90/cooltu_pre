package core.lib4a.tools;

import android.content.Context;

import core.lib4a.act.Destroys;
import core.lib4a.act.OnDestroy;

public class DestoryTool {

    public static void onDestory(Context context, OnDestroy onDestroy) {
        if (context instanceof Destroys) {
            ((Destroys) context).add(onDestroy);
        }
    }

}
