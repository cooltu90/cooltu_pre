package com.codingtu.cooltu_pre.ui;

import android.graphics.Color;
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
import com.codingtu.cooltu_pre.bean.MyLabel;

import java.util.ArrayList;
import java.util.List;

import cooltu.lib4j.fake.Fake;
import cooltu.lib4j.tools.CountTool;
import core.actbase.ViewActivityBase;
import core.actres.ViewActivityRes;

@To(ViewActivityRes.class)
@ActBase(layout = R.layout.activity_view)
public class ViewActivity extends ViewActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ttv.setTextColor(Color.BLUE);
        ttv.setStrokeColor(Color.RED);

    }

}
