package com.codingtu.cooltu.lib4a.act;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import com.codingtu.cooltu.lib4j.tools.CountTool;

public class ActivityManager {

    private List<Activity> acts;

    private ActivityManager() {
    }

    private static final class SingleInstance {
        public static final ActivityManager INSTANCE = new ActivityManager();
    }

    public static ActivityManager getInstance() {
        return SingleInstance.INSTANCE;
    }

    public void add(Activity act) {
        if (acts == null)
            acts = new ArrayList<Activity>();
        acts.add(act);
    }

    public void remove(Activity act) {
        if (acts != null) {
            acts.remove(act);
        }
    }

    public void finishAll() {
        if (acts != null) {
            for (int i = 0; i < CountTool.count(acts); i++) {
                acts.get(i).finish();
            }
            acts.clear();
        }
    }

    public Activity getLastOne() {
        return acts.get(acts.size() - 1);
    }

}
