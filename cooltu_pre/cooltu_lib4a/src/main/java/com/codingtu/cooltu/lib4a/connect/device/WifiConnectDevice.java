package com.codingtu.cooltu.lib4a.connect.device;

import com.codingtu.cooltu.lib4a.connect.ConnectDeviceBaseData;
import com.codingtu.cooltu.lib4a.log.Logs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public abstract class WifiConnectDevice extends SocketConnectDevice {
    private OutputStream outputStream;
    private Socket socket;

    public WifiConnectDevice(int connectType, int deviceType, String name, String mac) {
        super(connectType, deviceType, name, mac);
    }

    public WifiConnectDevice(ConnectDeviceBaseData baseData) {
        super(baseData);
    }

    @Override
    protected void createSockect() {
        try {
//            socket = new Socket(getIp(), getPort());
//            socket.setSoTimeout(3000);
            socket = new Socket();
            socket.setSoTimeout(3000); // if you like
            socket.connect(new InetSocketAddress(getIp(), getPort()), 3000);
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
