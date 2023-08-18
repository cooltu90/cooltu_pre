package com.codingtu.cooltu.lib4a.view.dialogview;

import android.app.Activity;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.act.Destroys;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.tools.MobileTool;
import com.codingtu.cooltu.lib4a.view.layerview.RelativeLayerView;

import com.codingtu.cooltu.lib4j.tools.StringTool;

import com.codingtu.cooltu.lib4a.tools.InflateTool;
import com.codingtu.cooltu.lib4a.tools.Margins;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.layerview.listener.LayerEvent;
import com.codingtu.cooltu.lib4a.view.layerview.listener.LayerEventType;
import com.codingtu.cooltu.lib4a.view.layerview.listener.LayerListener;

public class EditDialog implements OnDestroy, View.OnClickListener {

    private Activity act;
    private RelativeLayerView rlv;
    private View inflate;
    private int layout;
    private EditText et;
    private View noBt;
    private View yesBt;
    private String title;
    private String hint;
    private Integer inputType;
    private Yes yes;
    private boolean isReserveOriValue;
    private String lastText;
    private Object obj;
    private boolean isStopAnimation;
    private EdTextWatcher textWatcher;

    public EditDialog(Activity act) {
        this.act = act;
        if (act instanceof Destroys) {
            ((Destroys) act).add(this);
        }
    }

    @Override
    public void destroy() {
        if (yesBt != null)
            yesBt.setOnClickListener(null);
        if (noBt != null)
            noBt.setOnClickListener(null);
        inflate = null;
        noBt = null;
        yesBt = null;
        et = null;
        rlv = null;
        act = null;
        yes = null;
        obj = null;
    }

    public EditDialog setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public EditDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public EditDialog setHint(String hint) {
        this.hint = hint;
        return this;
    }

    public EditDialog setInputType(int inputType) {
        this.inputType = inputType;
        return this;
    }

    public EditDialog setReserveOriValue(boolean reserveOriValue) {
        isReserveOriValue = reserveOriValue;
        return this;
    }

    public EditDialog stopAnimation() {
        isStopAnimation = true;
        return this;
    }

    public EditDialog setYes(Yes yes) {
        this.yes = yes;
        return this;
    }

    public EditDialog setObject(Object obj) {
        this.obj = obj;
        return this;
    }


    public EditDialog setTextWatcher(EdTextWatcher textWatcher) {
        this.textWatcher = textWatcher;
        return this;
    }

    public EditDialog build() {
        rlv = new RelativeLayerView(act);
        rlv.setHiddenWhenBackClick(false);
        rlv.setHiddenWhenShadowClick(false);
        ViewTool.addToAct(act, rlv);
        ViewTool.gone(rlv);
        inflate = InflateTool.inflate(act, layout);
        rlv.addView(inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
        if (isStopAnimation)
            rlv.stopAnimation();

        View titleTv = inflate.findViewById(R.id.editDialogTitleTv);
        ViewTool.setText(titleTv, title);

        et = inflate.findViewById(R.id.editDialogTitleEt);
        et.setHint(hint);
        et.setImeOptions(EditorInfo.IME_ACTION_DONE);
        if (textWatcher != null) {
            textWatcher.setEditText(et);
            et.addTextChangedListener(textWatcher);
        }
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //处理搜索事件
                    if (yes == null) {
                        return false;
                    }
                    String text = et.getText().toString();
                    if (yes.yes(text, obj)) {
                        if (isReserveOriValue) {
                            if (!StringTool.isBlank(text)) {
                                lastText = text;
                            }
                        }
                        rlv.hidden();
                    }
                    return true;
                }
                return false;
            }
        });

        if (inputType != null) {
            et.setInputType(inputType);
        }

        noBt = inflate.findViewById(R.id.editDialogNoBt);
        yesBt = inflate.findViewById(R.id.editDialogYesBt);
        yesBt.setOnClickListener(this);
        noBt.setOnClickListener(this);

        isReserveOriValue = true;

        rlv.setLayerListener(new LayerListener() {
            @Override
            public void event(LayerEvent event) {
                if (event.type == LayerEventType.HIDDEN_START) {
                    ViewTool.inputHidden(et);
                } else if (event.type == LayerEventType.HIDDEN_FINISHED) {
                    setEditText(lastText);
                    obj = null;
                }
            }
        });
        return this;
    }

    public void setEditText(String text) {
        this.lastText = StringTool.toString(text);
        ViewTool.setEditTextAndSelection(et, lastText);
    }

    private Integer restHeight;

    public EditDialog show() {
        if (restHeight == null) {
            rlv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int windowVisibleDisplayH = MobileTool.getWindowVisibleDisplayH(act);
                    if (windowVisibleDisplayH < (MobileTool.getScreenHight() * 0.66)) {
                        //获取到了剩余高度
                        restHeight = windowVisibleDisplayH;
                        int dialogH = inflate.getHeight();
                        ViewTool.visible(rlv);
                        if (dialogH != 0) {
                            rlv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            int top = (restHeight - dialogH) / 2;
                            Margins.t(inflate, top);
                            rlv.show();
                        }
                    }
                }
            });
        } else {
            rlv.show();
        }
        ViewTool.inputShow(et);
        et.requestFocus();
        return this;
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == R.id.editDialogYesBt) {
            clickYesBt(v);
            return;
        }

        if (vId == R.id.editDialogNoBt) {
            clickNoBt(v);
            return;
        }
    }

    private void clickNoBt(View v) {
        rlv.hidden();
    }

    private void clickYesBt(View v) {
        if (yes == null) {
            return;
        }
        String text = et.getText().toString();
        if (yes.yes(text, obj)) {
            if (isReserveOriValue) {
                if (!StringTool.isBlank(text)) {
                    lastText = text;
                }
            }
            rlv.hidden();
        }
    }

    public static interface Yes {
        public boolean yes(String text, Object obj);
    }

    public static interface EdTextWatcher extends TextWatcher {
        public void setEditText(EditText et);
    }
}
