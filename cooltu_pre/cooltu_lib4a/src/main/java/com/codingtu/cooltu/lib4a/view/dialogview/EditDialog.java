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

public final class EditDialog implements OnDestroy, View.OnClickListener {

    private Activity act;
    private RelativeLayerView rlv;
    private View inflate;
    private EditText et;
    private View noBt;
    private View yesBt;
    private Yes yes;
    private Object obj;

    public static class Builder {
        private Activity act;
        private String title;
        private String hint;
        private Integer inputType;
        private int layout;
        private boolean isStopAnimation;
        private Yes yes;
        private EdTextWatcher textWatcher;

        public Builder(Activity act) {
            this.act = act;
        }


        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setHint(String hint) {
            this.hint = hint;
            return this;
        }

        public Builder setInputType(Integer inputType) {
            this.inputType = inputType;
            return this;
        }

        public Builder setLayout(int layout) {
            this.layout = layout;
            return this;
        }


        public Builder stopAnimation() {
            isStopAnimation = true;
            return this;
        }

        public Builder setYes(Yes yes) {
            this.yes = yes;
            return this;
        }

        public Builder setTextWatcher(EdTextWatcher textWatcher) {
            this.textWatcher = textWatcher;
            return this;
        }

        public EditDialog build() {
            EditDialog editDialog = new EditDialog(act);
            editDialog.rlv = new RelativeLayerView(act);
            editDialog.rlv.setHiddenWhenBackClick(false);
            editDialog.rlv.setHiddenWhenShadowClick(false);
            ViewTool.addToAct(act, editDialog.rlv);
            ViewTool.gone(editDialog.rlv);
            editDialog.inflate = InflateTool.inflate(act, layout);
            editDialog.rlv.addView(editDialog.inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
            if (isStopAnimation)
                editDialog.rlv.stopAnimation();

            View titleTv = editDialog.inflate.findViewById(R.id.editDialogTitleTv);
            ViewTool.setText(titleTv, title);

            editDialog.et = editDialog.inflate.findViewById(R.id.editDialogTitleEt);
            editDialog.et.setHint(hint);
            editDialog.et.setImeOptions(EditorInfo.IME_ACTION_DONE);
            if (textWatcher != null) {
                textWatcher.setEditText(editDialog.et);
                editDialog.et.addTextChangedListener(textWatcher);
            }
            editDialog.et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        //处理搜索事件
                        if (editDialog.yes == null) {
                            return false;
                        }
                        String text = editDialog.et.getText().toString();
                        if (editDialog.yes.yes(text, editDialog.obj)) {
                            editDialog.rlv.hidden();
                        }
                        return true;
                    }
                    return false;
                }
            });

            if (inputType != null) {
                editDialog.et.setInputType(inputType);
            }

            editDialog.noBt = editDialog.inflate.findViewById(R.id.editDialogNoBt);
            editDialog.yesBt = editDialog.inflate.findViewById(R.id.editDialogYesBt);
            editDialog.yesBt.setOnClickListener(editDialog);
            editDialog.noBt.setOnClickListener(editDialog);

            editDialog.rlv.setLayerListener(new LayerListener() {
                @Override
                public void event(LayerEvent event) {
                    if (event.type == LayerEventType.HIDDEN_START) {
                        ViewTool.inputHidden(editDialog.et);
                    } else if (event.type == LayerEventType.HIDDEN_FINISHED) {
                        editDialog.obj = null;
                    }
                }
            });
            editDialog.yes = yes;
            return editDialog;
        }
    }

    public View getRootView() {
        return this.inflate;
    }

    public EditDialog(Activity act) {
        this.act = act;
        if (act instanceof Destroys) {
            ((Destroys) act).add(this);
        }
    }

    public void setObject(Object obj) {
        this.obj = obj;
    }

    public void setEditText(String text) {
        ViewTool.setEditTextAndSelection(et, StringTool.toString(text));
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
