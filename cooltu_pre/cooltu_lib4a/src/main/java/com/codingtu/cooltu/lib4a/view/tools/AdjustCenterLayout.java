package com.codingtu.cooltu.lib4a.view.tools;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.codingtu.cooltu.lib4a.act.Destroys;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.tools.Margins;
import com.codingtu.cooltu.lib4a.tools.MobileTool;

public class AdjustCenterLayout implements OnDestroy {
    private View view;
    private Activity act;
    private int lastH;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;

    public static AdjustCenterLayout view(View view) {
        AdjustCenterLayout layout = new AdjustCenterLayout();
        layout.view = view;
        return layout;
    }

    public AdjustCenterLayout act(Activity act) {
        this.act = act;
        if (act instanceof Destroys) {
            ((Destroys) act).add(this);
        }
        lastH = MobileTool.getWindowVisibleDisplayH(act);
        return this;
    }

    public void start() {
        onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int windowVisibleDisplayH = MobileTool.getWindowVisibleDisplayH(act);
                if (windowVisibleDisplayH != lastH) {
                    lastH = windowVisibleDisplayH;
                    int top = (lastH - view.getHeight()) / 2;
                    Margins.t(view, top);
                }
            }
        };
        view.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    @Override
    public void destroy() {
        if (view != null)
            view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        onGlobalLayoutListener = null;
        act = null;
        view = null;
    }

}
