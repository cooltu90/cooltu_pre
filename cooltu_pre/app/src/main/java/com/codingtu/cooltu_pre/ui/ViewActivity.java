package com.codingtu.cooltu_pre.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.image.ImageTools;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;
import com.codingtu.cooltu.lib4a.tools.SDCardTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu_pre.R;

import cooltu.lib4j.fake.Fake;
import core.actbase.ViewActivityBase;
import core.actres.ViewActivityRes;

@To(ViewActivityRes.class)
@ActBase(layout = R.layout.activity_view)
public class ViewActivity extends ViewActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String file = SDCardTool.getSDCard() + "/testimg/5.jpg";
//        String file="/storage/emulated/0/EnvCheckData/photos/pts_default_default_任务1.1_20230327_103405/L-08008/unhandle.jpg";

        ImageTools.setImage(civ,getImage());
//        civ.setBackgroundResource(com.codingtu.cooltu.lib4a.R.mipmap.default_img);

    }

    @ClickView(R.id.civ)
    public void civClick() {
        ImageTools.setImage(civ, getImage());
    }


    private String getImage() {
        String image = Fake.image();
        Logs.i("image:" + image);
        return image;
    }


}
