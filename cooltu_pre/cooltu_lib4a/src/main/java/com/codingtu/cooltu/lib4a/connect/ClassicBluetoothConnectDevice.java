package com.codingtu.cooltu.lib4a.connect;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.codingtu.cooltu.lib4a.log.Logs;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cooltu.lib4j.tools.CountTool;

public abstract class ClassicBluetoothConnectDevice extends ConnectDevice {

    private boolean isRead;
    protected OutputStream outputStream;
    BluetoothSocket bluetoothSocket = null;

    public ClassicBluetoothConnectDevice(String deviceType, String connectType, String uuid, boolean isWifi) {
        super(deviceType, connectType, uuid, isWifi);
    }

    public ClassicBluetoothConnectDevice(String deviceType, String connectType, String uuid, boolean isWifi, String name, String mac) {
        super(deviceType, connectType, uuid, isWifi, name, mac);
    }

    @Override
    public void connect() {
        new Thread(new Runnable() {
            @SuppressLint("MissingPermission")
            @Override
            public void run() {
                bluetoothSocket = null;
                try {
                    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice device = bluetoothAdapter.getRemoteDevice(baseData.mac);
                    bluetoothSocket = device.createRfcommSocketToServiceRecord(baseData.uuid);
                    bluetoothSocket.connect();
                } catch (IOException e) {
                    Logs.e(e);
                }

                if (bluetoothSocket == null || !bluetoothSocket.isConnected()) {
                    sendMessage(ConnectStatus.FAIL, null);
                    return;
                }

                DataInputStream dis = null;
                try {
                    dis = new DataInputStream(new BufferedInputStream(bluetoothSocket.getInputStream()));
                } catch (IOException e) {
                    Logs.e(e);
                }
                if (dis == null) {
                    sendMessage(ConnectStatus.FAIL, null);
                    return;
                }

                outputStream = null;
                try {
                    outputStream = bluetoothSocket.getOutputStream();
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

    private void startRead(DataInputStream dis) {
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

    private byte[] copyData(List<Byte> byteList) {
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
        if (bluetoothSocket != null) {
            try {
                bluetoothSocket.close();
            } catch (Exception e) {

            }
        }
        bluetoothSocket = null;
    }
}
