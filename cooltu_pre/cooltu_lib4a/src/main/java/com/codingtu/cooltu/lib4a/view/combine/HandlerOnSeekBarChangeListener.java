package com.codingtu.cooltu.lib4a.view.combine;

import android.os.Handler;
import android.os.Message;
import android.widget.SeekBar;

public class HandlerOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
    private final Handler handler;
    private final int type;
    private final int index;

    public HandlerOnSeekBarChangeListener(Handler handler, int type, int index) {
        this.handler = handler;
        this.type = type;
        this.index = index;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Message message = Message.obtain();
        message.obj = progress;
        message.what = type;
        message.arg1 = index;
        handler.sendMessage(message);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
