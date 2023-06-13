package com.codingtu.cooltu_pre.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.download.Download;
import com.codingtu.cooltu.lib4a.image.ImageTools;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;
import com.codingtu.cooltu.lib4a.tools.SDCardTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.image.BigScaleImageView;
import com.codingtu.cooltu.lib4a.view.image.BreathView;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu_pre.R;
import com.codingtu.cooltu_pre.bean.MyLabel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cooltu.lib4j.fake.Fake;
import cooltu.lib4j.tools.CountTool;
import core.actbase.ViewActivityBase;
import core.actres.ViewActivityRes;
import io.reactivex.Flowable;

@To(ViewActivityRes.class)
@ActBase(layout = R.layout.activity_view)
public class ViewActivity extends ViewActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Map<String, String> picMap = new HashMap<>();
        //188-158 256-256
        String key = null;
        for (int i = 0; i < 8; i++) {
            int count = (int) Math.pow(2, i);
            for (int j = 0; j < count; j++) {
                for (int k = 0; k < count; k++) {
                    key = i + "_" + j + "_" + k;
                    picMap.put(key, "/0gp/Map/Map/" + key);
                }
            }
        }
        BigScaleImageView.BigImage bigImage = new BigScaleImageView.BigImage();
        for (int level = 0; level < 8; level++) {
            int count = (int) Math.pow(2, level);

            BigScaleImageView.BigImageLevel bigImageLevel = new BigScaleImageView.BigImageLevel();
            bigImage.addLevel(level, bigImageLevel);

            for (int row = 0; row < count; row++) {
                for (int column = 0; column < count; column++) {
                    bigImageLevel.setImage(row, column, "/0gp/Map/Map/" + level + "_" + column + "_" + row);
                }
            }
        }

        iv.setBigImage(bigImage);

    }

}
