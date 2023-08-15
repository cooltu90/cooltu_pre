package com.codingtu.cooltu.lib4a.connect.device;

import com.codingtu.cooltu.lib4a.log.Logs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class WifiConnectDevice extends SocketConnectDevice {
    private OutputStream outputStream;
    private Socket socket;

    public WifiConnectDevice(int deviceType, int connectType) {
        super(deviceType, connectType);
    }

    public WifiConnectDevice(int deviceType, int connectType, String name, String mac) {
        super(deviceType, connectType, name, mac);
    }

    @Override
    protected void createSockect() {
        try {
            socket = new Socket(getIp(), getPort());
            socket.setSoTimeout(3000);
        } catch (IOException e) {
            Logs.e(e);
        }
    }

    @Override
    protected boolean isCreateFail() {
        return socket == null || !socket.isConnected();
    }

    @Override
    protected InputStream getSocketInputStream() throws IOException {
        return socket.getInputStream();
    }

    @Override
    protected OutputStream getSocketOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    @Override
    public void disconnect(DisconnectFinish disconnectFinish) {
        super.disconnect(disconnectFinish);
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception e) {

            }
        }
        socket = null;
    }

    protected abstract int getPort();

    protected abstract String getIp();
}
