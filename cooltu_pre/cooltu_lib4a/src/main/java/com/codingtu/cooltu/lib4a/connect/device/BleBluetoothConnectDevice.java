package com.codingtu.cooltu.lib4a.connect.device;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothProfile;

import com.codingtu.cooltu.lib4a.CoreApp;
import com.codingtu.cooltu.lib4a.connect.ConnectDeviceBaseData;
import com.codingtu.cooltu.lib4a.connect.ConnectStatus;

@SuppressLint("MissingPermission")
public abstract class BleBluetoothConnectDevice extends ConnectDevice {
    protected BluetoothGatt bluetoothGatt;

    public BleBluetoothConnectDevice(int connectType, int deviceType, String name, String mac) {
        super(connectType, deviceType, name, mac);
    }

    public BleBluetoothConnectDevice(ConnectDeviceBaseData baseData) {
        super(baseData);
    }

    @Override
    public void connect() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(baseData.mac);
        device.connectGatt(CoreApp.APP, false, new BluetoothGattCallback() {
            @Override
            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                super.onConnectionStateChange(gatt, status, newState);
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    if (newState == BluetoothProfile.STATE_CONNECTED) {
                        if (!gatt.discoverServices()) {
                            sendMessage(ConnectStatus.FAIL, null);
                        } else {
                            sendMessage(ConnectStatus.SUCCESS, null);
                        }
                    } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                        gatt.close();
                        sendMessage(ConnectStatus.DISCONNECT, null);
                    }
                } else {
                    gatt.close();
                    sendMessage(ConnectStatus.FAIL, null);
                }
            }

            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                super.onServicesDiscovered(gatt, status);
                bluetoothGatt = gatt;
                BleBluetoothConnectDevice.this.onServicesDiscovered(gatt, status);
            }

            @Override
            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                super.onCharacteristicChanged(gatt, characteristic);
                BleBluetoothConnectDevice.this.onCharacteristicChanged(gatt, characteristic);
            }

            @Override
            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicWrite(gatt, characteristic, status);
                BleBluetoothConnectDevice.this.onCharacteristicWrite(gatt, characteristic, status);
            }

            @Override
            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicRead(gatt, characteristic, status);
                BleBluetoothConnectDevice.this.onCharacteristicRead(gatt, characteristic, status);
            }

            @Override
            public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                super.onDescriptorWrite(gatt, descriptor, status);
                BleBluetoothConnectDevice.this.onDescriptorWrite(gatt, descriptor, status);
            }

            @Override
            public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                super.onDescriptorRead(gatt, descriptor, status);
                BleBluetoothConnectDevice.this.onDescriptorRead(gatt, descriptor, status);
            }
        });
    }

    protected void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {

    }

    protected void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {

    }

    protected void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {

    }

    protected void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {

    }

    protected void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {

    }

    protected void onServicesDiscovered(BluetoothGatt gatt, int status) {

    }

    @Override
    public void disconnect(DisconnectFinish disconnectFinish) {
        super.disconnect(disconnectFinish);
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
        }
        bluetoothGatt = null;
    }
}
