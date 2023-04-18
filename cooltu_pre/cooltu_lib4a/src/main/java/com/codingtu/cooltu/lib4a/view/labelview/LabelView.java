package com.codingtu.cooltu.lib4a.view.labelview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.DrawTool;
import com.codingtu.cooltu.lib4a.tools.Margins;
import com.codingtu.cooltu.lib4a.tools.Paddings;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;
import com.codingtu.cooltu.lib4a.view.textview.TagTextView;

import java.util.List;

import cooltu.lib4j.tools.CountTool;

public class LabelView extends CoreLabelView {

    private boolean isInit;
    private int labelBgColor;
    private int labelRow;
    private int labelLeft;
    private int labelH;
    private int labelTextColor;
    private int labelRadius;
    private int labelSize;
    private int labelPadding;
    private int labelDivider;
    private int labelTop;
    private List<? extends Label> labels;
    private int labelId;
    private OnClickListener onClickListener;

    public LabelView(Context context) {
        super(context);
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        AttrsTools.getAttrs(context, attrs, R.styleable.LabelView, new GetAttrs() {
            @Override
            public void getAttrs(Attrs attrs) {
                labelBgColor = attrs.getColor(R.styleable.LabelView_labelBgColor, Color.parseColor("#cccccc"));
                labelTextColor = attrs.getColor(R.styleable.LabelView_labelTextColor, Color.parseColor("#ffffff"));
                labelRow = attrs.getInt(R.styleable.LabelView_labelRow, -1);
                labelLeft = attrs.getDimensionPixelSize(R.styleable.LabelView_labelLeft, 0);
                labelH = attrs.getDimensionPixelSize(R.styleable.LabelView_labelH, 0);
                labelRadius = attrs.getDimensionPixelSize(R.styleable.LabelView_labelRadius, 0);
                labelSize = attrs.getDimensionPixelSize(R.styleable.LabelView_labelSize, 0);
                labelPadding = attrs.getDimensionPixelSize(R.styleable.LabelView_labelPadding, 0);
                labelDivider = attrs.getDimensionPixelSize(R.styleable.LabelView_labelDivider, 0);
                labelTop = attrs.getDimensionPixelSize(R.styleable.LabelView_labelTop, 0);
            }
        });
    }

    public void setLable(int labelId, List<? extends Label> labels) {
        this.labelId = labelId;
        this.labels = labels;
        startAdd();
    }

    private void startAdd() {
        if (isInit) {
            startAddReal();
        } else {
            ViewTool.completeView(this, new ViewTool.ViewComplete() {
                @Override
                public void viewComplete() {
                    isInit = true;
                    startAddReal();
                }
            });
        }
    }

    private void startAddReal() {
        int width = labelLeft;
        int currentRow = 0;
        for (int i = 0; i < CountTool.count(labels); i++) {
            Label label = labels.get(i);
            String labelStr = label.getLabel();
            int w = DrawTool.getTextBounds(labelSize, labelStr).width() + labelPadding * 2;
            if (width + w + labelDivider > getWidth()) {
                currentRow++;
                width = labelLeft;
            }

            if (labelRow > 0 && currentRow >= labelRow) {
                break;
            }
            TagTextView ttv = new TagTextView(getContext());
            addView(ttv, ViewTool.WRAP_CONTENT, labelH);
            ttv.setText(labelStr);
            ttv.setBgColor(labelBgColor);
            ttv.setTextColor(labelTextColor);
            ttv.setCornerRadius(labelRadius);
            ttv.setTextSize(TypedValue.COMPLEX_UNIT_PX, labelSize);
            Paddings.lr(ttv, labelPadding, labelPadding);

            Margins.lt(ttv, width, (labelTop + labelH) * currentRow + labelTop);
            width += (w + labelDivider);

            ttv.setId(labelId);
            ttv.setTag(R.id.tag_0, label);
            if (onClickListener != null)
                ttv.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        this.onClickListener = l;
    }

    @Override
    public void destroy() {
        super.destroy();
        this.onClickListener = null;
        labels.clear();
        labels = null;
    }
}
