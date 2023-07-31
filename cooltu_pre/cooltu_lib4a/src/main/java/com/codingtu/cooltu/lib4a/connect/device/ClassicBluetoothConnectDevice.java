package com.codingtu.cooltu.lib4a.connect.device;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.codingtu.cooltu.lib4a.log.Logs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class ClassicBluetoothConnectDevice extends SocketConnectDevice {
    BluetoothSocket bluetoothSocket = null;

    private boolean isRead;
    protected OutputStream outputStream;

    public ClassicBluetoothConnectDevice(String deviceType, String connectType, String uuid, boolean isWifi) {
        super(deviceType, connectType, uuid, isWifi);
    }

    public ClassicBluetoothConnectDevice(String deviceType, String connectType, String uuid, boolean isWifi, String name, String mac) {
        super(deviceType, connectType, uuid, isWifi, name, mac);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void createSockect() {
        bluetoothSocket = null;
        try {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(baseData.mac);
            bluetoothSocket = device.createRfcommSocketToServiceRecord(baseData.uuid);
            bluetoothSocket.connect();
        } catch (IOException e) {
            Logs.e(e);
        }
    }

    @Override
    protected boolean isCreateFail() {
        return bluetoothSocket == null || !bluetoothSocket.isConnected();
    }

    @Override
    protected InputStream getSocketInputStream() throws IOException {
        return bluetoothSocket.getInputStream();
    }

    @Override
    protected OutputStream getSocketOutputStream() throws IOException {
        return bluetoothSocket.getOutputStream();
    }

    @Override
    public void disconnect(DisconnectFinish disconnectFinish) {
        super.disconnect(disconnectFinish);
        if (bluetoothSocket != null) {
            try {
                bluetoothSocket.close();
            } catch (Exception e) {

            }
        }
        bluetoothSocket = null;
    }
}
