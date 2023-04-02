package com.codingtu.cooltu.lib4a.view.textview;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;

public class HandlerTextWatcher implements TextWatcher {

    private Handler handler;
    private int type;
    private int index;

    public HandlerTextWatcher(Handler handler, int type, int index) {
        this.handler = handler;
        this.type = type;
        this.index = index;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Message message = Message.obtain();
        message.obj = s.toString();
        message.what = type;
        message.arg1 = index;
        handler.sendMessage(message);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
