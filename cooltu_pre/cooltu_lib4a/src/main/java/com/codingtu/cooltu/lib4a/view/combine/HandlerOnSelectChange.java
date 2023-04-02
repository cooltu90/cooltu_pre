package com.codingtu.cooltu.lib4a.view.combine;

import android.os.Handler;
import android.os.Message;

public class HandlerOnSelectChange implements RadioGroup.OnSelectChange {

    private Handler handler;
    private int type;
    private int index;

    public HandlerOnSelectChange(Handler handler, int type, int index) {
        this.handler = handler;
        this.type = type;
        this.index = index;
    }

    @Override
    public void onChange(int selected) {
        Message message = Message.obtain();
        message.obj = selected;
        message.what = type;
        message.arg1 = index;
        handler.sendMessage(message);
    }
}
