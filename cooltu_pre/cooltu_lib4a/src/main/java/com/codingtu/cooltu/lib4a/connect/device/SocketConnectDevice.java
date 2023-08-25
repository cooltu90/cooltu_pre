package com.codingtu.cooltu.lib4a.connect.device;

import com.codingtu.cooltu.lib4a.connect.ConnectDeviceBaseData;
import com.codingtu.cooltu.lib4a.connect.ConnectStatus;
import com.codingtu.cooltu.lib4a.log.Logs;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;

public abstract class SocketConnectDevice extends ConnectDevice {

    protected boolean isRead;
    protected OutputStream outputStream;

    public SocketConnectDevice(int connectType, int deviceType, String name, String mac) {
        super(connectType, deviceType, name, mac);
    }

    public SocketConnectDevice(ConnectDeviceBaseData baseData) {
        super(baseData);
    }

    @Override
    public void connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                createSockect();

                if (isCreateFail()) {
                    sendMessage(ConnectStatus.FAIL, null);
                    return;
                }

                DataInputStream dis = null;
                try {
                    dis = new DataInputStream(new BufferedInputStream(getSocketInputStream()));
                } catch (IOException e) {
                    Logs.e(e);
                }
                if (dis == null) {
                    sendMessage(ConnectStatus.FAIL, null);
                    return;
                }

                outputStream = null;
                try {
                    outputStream = getSocketOutputStream();
                } catch (IOException e) {
                    Logs.e(e);
                }

                if (outputStream == null) {
                    sendMessage(ConnectStatus.FAIL, null);
                    return;
                }

                sendMessage(ConnectStatus.SUCCESS, null);
                startRead(dis);
                startWrite();

            }
        }).start();
    }

    protected abstract OutputStream getSocketOutputStream() throws IOException;

    protected abstract InputStream getSocketInputStream() throws IOException;

    protected abstract boolean isCreateFail();

    protected abstract void createSockect();

    protected void startRead(DataInputStream dis) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Byte> byteList = new ArrayList<>();
                byte[] bytes = new byte[1];
                int len;
                isRead = true;
                while (isRead) {
                    try {
                        len = dis.read(bytes);
                    } catch (Exception e) {
                        len = -1;
                    }
                    if (len != -1) {
                        byteList.add(bytes[0]);
                        try {
                            if (dis.available() == 0) {
                                sendMessage(ConnectStatus.READ, copyData(byteList));
                                byteList.clear();
                            }
                        } catch (IOException e) {
                            isRead = false;
                        }
                    } else {
                        isRead = false;
                    }
                }
                sendMessage(ConnectStatus.DISCONNECT, null);
            }
        }).start();
    }

    public static byte[] copyData(List<Byte> byteList) {
        byte[] newBytes = new byte[CountTool.count(byteList)];
        for (int i = 0; i < newBytes.length; i++) {
            newBytes[i] = byteList.get(i);
        }
        return newBytes;
    }


    protected void startWrite() {

    }


    public void write(int... ints) throws Exception {
        if (outputStream != null) {
            for (int i = 0; i < CountTool.count(ints); i++) {
                outputStream.write(ints[i]);
            }
            outputStream.flush();
        }
    }

    @Override
    public void disconnect(DisconnectFinish disconnectFinish) {
        super.disconnect(disconnectFinish);
        isRead = false;
    }
}
