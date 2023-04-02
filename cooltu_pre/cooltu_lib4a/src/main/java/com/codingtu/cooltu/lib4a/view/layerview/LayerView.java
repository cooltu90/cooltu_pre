package com.codingtu.cooltu.lib4a.view.layerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.act.CoreUiInterface;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.act.WhenKeyDown;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;

import com.codingtu.cooltu.lib4a.tools.DestoryTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.layerview.listener.LayerEvent;
import com.codingtu.cooltu.lib4a.view.layerview.listener.LayerEventType;
import com.codingtu.cooltu.lib4a.view.layerview.listener.LayerListener;

public abstract class LayerView extends RelativeLayout implements OnDestroy {

    private AlphaAnimation showShadowAnim;
    private AlphaAnimation hiddenShadowAnim;

    protected int defaultDuration = 300;

    private View shadowView;

    private LayerListener layerListener;

    private boolean isHiddenWhenShadowClick = true;
    private boolean isHiddenWhenBackClick = true;
    protected boolean stopAnimation;

    public LayerView(Context context) {
        this(context, null);
    }

    public LayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public LayerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        DestoryTool.onDestory(context, this);
        AttrsTools.getAttrs(context, attrs, R.styleable.LayerView, new GetAttrs() {
            @Override
            public void getAttrs(Attrs attrs) {
                isHiddenWhenShadowClick = attrs.getBoolean(R.styleable.LayerView_is_hidden_when_shadow_click, isHiddenWhenShadowClickDefault());
                isHiddenWhenBackClick = attrs.getBoolean(R.styleable.LayerView_is_hidden_when_back_click, isHiddenWhenBackClickDefault());
            }
        });
        shadowView = new View(context);
        shadowView.setBackgroundColor(context.getResources().getColor(R.color.colorShadow));
        addView(shadowView, ViewTool.MATCH_PARENT, ViewTool.MATCH_PARENT);
        shadowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onShadowClick();
                if (isHiddenWhenShadowClick) {
                    hidden();
                }
            }
        });

        showShadowAnim = new AlphaAnimation(0f, 1f);
        showShadowAnim.setDuration(defaultDuration);
        showShadowAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        hiddenShadowAnim = new AlphaAnimation(1f, 0f);
        hiddenShadowAnim.setDuration(defaultDuration);
        hiddenShadowAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                sendEvent(LayerEventType.HIDDEN_FINISHED);
                ViewTool.gone(LayerView.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        if (context instanceof CoreUiInterface) {
            ((CoreUiInterface) context).addWhenKeyDown(new WhenKeyDown() {
                @Override
                public boolean onKeyDown(int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && isShow()) {
                        if (isHiddenWhenBackClick)
                            hidden();
                        return true;
                    }
                    return false;
                }
            });
        }

    }

    protected boolean isHiddenWhenShadowClickDefault() {
        return true;
    }

    protected boolean isHiddenWhenBackClickDefault() {
        return true;
    }

    public void setHiddenWhenShadowClick(boolean hiddenWhenShadowClick) {
        isHiddenWhenShadowClick = hiddenWhenShadowClick;
    }

    public void setHiddenWhenBackClick(boolean hiddenWhenBackClick) {
        isHiddenWhenBackClick = hiddenWhenBackClick;
    }

    protected void onShadowClick() {

    }

    public void setLayerListener(LayerListener layerListener) {
        this.layerListener = layerListener;
    }

    private boolean isShow() {
        return ViewTool.isVisible(this);
    }

    public void show() {
        show(null);
    }

    public void show(LayerListener layerListener) {
        if (layerListener != null) {
            this.layerListener = layerListener;
        }
        sendEvent(LayerEventType.SHOW_START);
        ViewTool.visible(this);
        sendEvent(LayerEventType.LAYER_VISIBLE);
        if (!stopAnimation) {
            shadowView.startAnimation(showShadowAnim);
        }

    }

    private void sendEvent(int type) {
        if (this.layerListener != null) {
            this.layerListener.event(getLayerEvent(type));
        }
    }

    private LayerEvent getLayerEvent(int type) {
        LayerEvent layerEvent = new LayerEvent();
        layerEvent.type = type;
        return layerEvent;
    }

    public void hidden() {
        hidden(null);
    }

    public void hidden(LayerListener layerListener) {
        if (layerListener != null) {
            this.layerListener = layerListener;
        }
        sendEvent(LayerEventType.HIDDEN_START);
        if (!stopAnimation) {
            shadowView.startAnimation(hiddenShadowAnim);
        } else {
            ViewTool.gone(this);
            sendEvent(LayerEventType.HIDDEN_FINISHED);
        }
    }

    @Override
    public void destroy() {
        layerListener = null;
    }

    public View getShadowView() {
        return shadowView;
    }
}
