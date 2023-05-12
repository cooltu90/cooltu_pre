package com.codingtu.cooltu_pre.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.bean.WH;
import com.codingtu.cooltu.lib4a.image.BitmapWH;
import com.codingtu.cooltu.lib4a.image.CutFileImage;
import com.codingtu.cooltu.lib4a.image.FileBitmap;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;
import com.codingtu.cooltu.lib4a.tools.DrawTool;
import com.codingtu.cooltu.lib4a.tools.SDCardTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu_pre.R;

import core.actbase.ViewActivityBase;

import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import java.io.File;
import java.io.IOException;

import core.actres.ViewActivityRes;

@To(ViewActivityRes.class)
@ActBase(layout = R.layout.activity_view)
public class ViewActivity extends ViewActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String file = SDCardTool.getSDCard() + "/testimg/5.jpg";

        ViewTool.completeView(iv1, new ViewTool.ViewComplete() {
            @Override
            public void viewComplete() {
                Bitmap bitmap = FileBitmap.obtain(file)
                        .size(iv1.getWidth(), iv1.getHeight())
                        .cut(new Rect(100, 100, 1000, 1000))
                        .bitmap();
                iv1.setImageBitmap(bitmap);
            }
        });


    }
}
