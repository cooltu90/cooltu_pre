package com.codingtu.cooltu_pre.connect.device;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;

import com.codingtu.cooltu.lib4a.connect.ResponseData;
import com.codingtu.cooltu.lib4a.connect.device.BleBluetoothConnectDevice;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu_pre.connect.ConnectDeviceType;
import com.codingtu.cooltu_pre.connect.ConnectType;

import java.util.UUID;

@SuppressLint("MissingPermission")
public class RfCrazy extends BleBluetoothConnectDevice {
    private BluetoothGattCharacteristic writeCharacteristic;

    public RfCrazy(String name, String mac) {
        super(
                ConnectDeviceType.RF_CRAZY,
                ConnectType.UFO,
                "6e400001-b5a3-f393-e0a9-e50e24dcca9e",
                false,
                name, mac);
    }

    @Override
    protected void onServicesDiscovered(BluetoothGatt gatt, int status) {
        super.onServicesDiscovered(gatt, status);
        Logs.i("onServicesDiscovered");
        BluetoothGattService service = gatt.getService(UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e"));
        writeCharacteristic = service.getCharacteristic(UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e"));
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e"));
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        gatt.writeDescriptor(descriptor);
        gatt.setCharacteristicNotification(characteristic, true);
        extracted(gatt);
    }

    @Override
    public void disconnect(DisconnectFinish disconnectFinish) {
        super.disconnect(disconnectFinish);
        writeCharacteristic = null;
    }

    private void extracted(BluetoothGatt gatt) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {

                    }
                    byte[] data = new byte[24];
                    initBleData(data);
                    try {
                        writeCharacteristic.setValue(data);
                    } catch (Exception e) {
                        break;
                    }
                    gatt.writeCharacteristic(writeCharacteristic);
                }
            }

            private void initBleData(byte[] data) {
                data[0] = (byte) 0x4a;
                data[1] = (byte) 0x08;

                data[2] = (byte) 0x00;
                data[3] = (byte) 0x14;

                data[4] = (byte) 0x00;

                data[5] = (byte) 0xFF;
                data[6] = (byte) 0xFF;

                data[7] = (byte) 0xFF;
                data[8] = (byte) 0xFF;

                data[9] = (byte) 0xFF;

                data[10] = (byte) 0xFF;

                data[11] = (byte) 0xFF;
                data[12] = (byte) 0xFF;

                data[13] = (byte) 0xFF;
                data[14] = (byte) 0xFF;
                data[15] = (byte) 0xFF;
                data[16] = (byte) 0xFF;

                data[17] = (byte) 0xFF;
                data[18] = (byte) 0xFF;
                data[19] = (byte) 0xFF;
                data[20] = (byte) 0xFF;

                data[21] = (byte) 0x01;

                for (int i = 2; i <= 21; i++) {
                    data[22] += data[i];
                }
                data[23] = (byte) ~((byte) data[22]);
            }
        }).start();
    }

    @Override
    public ResponseData[] parseResponseDatas(byte[] obj) {
        return null;
    }
}
