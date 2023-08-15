package com.codingtu.cooltu.lib4a.connect.device;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.codingtu.cooltu.lib4a.connect.ConnectDeviceBaseData;
import com.codingtu.cooltu.lib4a.log.Logs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public abstract class ClassicBluetoothConnectDevice extends SocketConnectDevice {
    BluetoothSocket bluetoothSocket = null;

    public ClassicBluetoothConnectDevice(int connectType, int deviceType, String name, String mac) {
        super(connectType, deviceType, name, mac);
    }

    public ClassicBluetoothConnectDevice(ConnectDeviceBaseData baseData) {
        super(baseData);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void createSockect() {
        bluetoothSocket = null;
        try {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(baseData.mac);
            bluetoothSocket = device.createRfcommSocketToServiceRecord(getServiceUUID());
            bluetoothSocket.connect();
        } catch (IOException e) {
            Logs.e(e);
        }
    }

    protected abstract UUID getServiceUUID();

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
