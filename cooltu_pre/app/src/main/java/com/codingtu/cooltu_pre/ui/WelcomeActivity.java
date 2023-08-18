package com.codingtu.cooltu_pre.ui;

import android.graphics.Color;

import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu_pre.R;

import core.actbase.WelcomeActivityBase;
import core.actres.WelcomeActivityRes;

@To(WelcomeActivityRes.class)
@ActBase(layout = R.layout.activity_welcome)
public class WelcomeActivity extends WelcomeActivityBase {

    private int count = 0;


    @ClickView(R.id.bt)
    public void btClick() {
        if (count % 2 == 0) {
            //变小
//            ViewTool.setWH(cv, MobileTool.dpToPx(10), MobileTool.dpToPx(10));
            cv.setBackgroundResource(com.codingtu.cooltu.lib4a.R.mipmap.default_img);
        } else {
            //变大
//            ViewTool.setWH(cv, MobileTool.dpToPx(100), MobileTool.dpToPx(100));
            cv.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        count++;
    }

}
