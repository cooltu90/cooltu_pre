package com.codingtu.cooltu.lib4a.connect.device;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4a.connect.ConnectDeviceBaseData;
import com.codingtu.cooltu.lib4a.connect.ConnectStatus;
import com.codingtu.cooltu.lib4a.connect.ConnectTool;
import com.codingtu.cooltu.lib4a.connect.ResponseData;
import com.codingtu.cooltu.lib4a.log.Logs;

import java.util.UUID;

import cooltu.lib4j.data.bean.CoreBean;

public abstract class ConnectDevice extends CoreBean {

    public ConnectDeviceBaseData baseData;
    protected DisconnectFinish disconnectFinish;

    public ConnectDevice(int deviceType, int connectType) {
        this(deviceType, connectType, null, null);
    }

    public ConnectDevice(int deviceType, int connectType, String name, String mac) {
        this.baseData = new ConnectDeviceBaseData();
        this.baseData.deviceType = deviceType;
        this.baseData.connectType = connectType;
        this.baseData.name = name;
        this.baseData.mac = mac;
    }

    public void setNameAndMac(String name, String mac) {
        this.baseData.name = name;
        this.baseData.mac = mac;
    }

    public void connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Logs.i("connect");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private Handler handler;


    private Handler getHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    dealMsg(msg.what, msg.obj);
                }
            };
        }
        return handler;
    }

    protected void dealMsg(int msgType, Object obj) {
        ConnectTool.dealMsg(this, msgType, obj);
        if (msgType == ConnectStatus.DISCONNECT) {
            if (disconnectFinish != null)
                disconnectFinish.onFinished();
            disconnectFinish = null;
        }
    }

    protected void sendMessage(int msgType, Object obj) {
        Message msg = Message.obtain();
        msg.what = msgType;
        msg.obj = obj;
        getHandler().sendMessage(msg);
    }

    public abstract ResponseData[] parseResponseDatas(byte[] obj);


    public static interface DisconnectFinish {
        public void onFinished();
    }

    public void disconnect(DisconnectFinish disconnectFinish) {
        this.disconnectFinish = disconnectFinish;
    }
}
