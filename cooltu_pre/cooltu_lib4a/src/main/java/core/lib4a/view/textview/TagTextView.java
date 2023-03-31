package core.lib4a.view.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.Gravity;

import com.codingtu.cooltu.lib4a.R;

import core.lib4a.bean.LTRB;
import core.lib4a.tools.DrawTool;
import core.lib4a.tools.MobileTool;
import core.lib4a.view.attrs.Attrs;
import core.lib4a.view.attrs.AttrsTools;
import core.lib4a.view.attrs.GetAttrs;
import core.lib4a.view.base.CoreTextView;

public class TagTextView extends CoreTextView {
    private int textW;
    private Paint paint;
    private int lineWidth;
    private int bgColor;
    private int cornerRadius;

    public TagTextView(Context context) {
        super(context);
    }

    public TagTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);

        paint = DrawTool.getDefaultPaint();

        AttrsTools.getAttrs(context, attrs, R.styleable.TagTextView, new GetAttrs() {
            @Override
            public void getAttrs(Attrs attrs) {
                lineWidth = attrs.getDimensionPixelSize(R.styleable.TagTextView_lineWidth, -1);
                bgColor = attrs.getColor(R.styleable.TagTextView_bgColor, Color.RED);
                cornerRadius = attrs.getDimensionPixelSize(R.styleable.TagTextView_corner_radius, MobileTool.dpToPx(context, 3));
            }
        });

        setGravity(Gravity.CENTER);

    }


    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
        invalidate();
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int canvasWidth = canvas.getWidth();

        int canvasHeight = canvas.getHeight();

        if (canvasWidth <= 0 || canvasHeight <= 0) {
            return;
        }

        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);

        LTRB ltrb = new LTRB();
        ltrb.th(0, getHeight());
        ltrb.lw(0, getWidth());

        boolean hasBg = lineWidth < 0;

        int halfLineWidth = 0;
        if (!hasBg) {
            halfLineWidth = lineWidth / 2 + (lineWidth % 2 == 0 ? 0 : 1);
        }

        paint.setColor(bgColor);
        canvas.drawRoundRect(ltrb.toRectF(), cornerRadius + halfLineWidth, cornerRadius + halfLineWidth, paint);

        if (!hasBg) {
            ltrb.th(lineWidth, getHeight() - lineWidth * 2);
            ltrb.lw(lineWidth, getWidth() - lineWidth * 2);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            paint.setColor(Color.TRANSPARENT);
            canvas.drawRoundRect(ltrb.toRectF(), cornerRadius - halfLineWidth, cornerRadius - halfLineWidth, paint);
            paint.setXfermode(null);
        }

        canvas.restoreToCount(layerId);

        super.onDraw(canvas);
    }


}
