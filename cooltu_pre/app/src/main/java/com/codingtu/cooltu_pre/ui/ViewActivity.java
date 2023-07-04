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
        //188-158 256-256
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int[][] nums = new int[][]{
//                        {256, 256, 188, 158, 34, 49, 1, 1},
//                        {512, 512, 376, 314, 68, 99, 2, 2},
//                        {1024, 1024, 750, 626, 137, 199, 4, 4},
//                        {2048, 2048, 1500, 1250, 274, 399, 8, 8,},
//                        {4096, 4096, 3000, 2500, 548, 798, 16, 16},
//                        {8192, 8192, 6000, 5000, 1096, 1596, 32, 32},
//                        {16384, 16384, 12000, 10000, 2192, 3192, 64, 64},
//                        {32768, 32768, 24000, 20000, 4384, 6384, 128, 128},
//                };
//
//                BigScaleImageView.BigImage bigImage = new BigScaleImageView.BigImage();
//                bigImage.setSliceWidth(256);
//                for (int level = 0; level < 8; level++) {
//                    int count = (int) Math.pow(2, level);
//
//                    int[] num = nums[level];
//
//                    BigScaleImageView.BigImageLevel bigImageLevel = new BigScaleImageView.BigImageLevel();
//                    bigImageLevel.setImageWH(num[0], num[1], num[2], num[3], num[4], num[5], num[6], num[7]);
//
//                    bigImage.addLevel(level, bigImageLevel);
//
//                    for (int row = 0; row < count; row++) {
//                        for (int column = 0; column < count; column++) {
//                            bigImageLevel.setImage(row, column, SDCardTool.getSDCard()+"/0gp/Map/Map/" + level + "_" + column + "_" + row);
//                        }
//                    }
//                }
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        iv.setBigImage(bigImage);
//                    }
//                });
//            }
//        }).start();

    }

}
