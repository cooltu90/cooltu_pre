package com.codingtu.cooltu.lib4a.connect;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.Intent;

import com.codingtu.cooltu.lib4a.connect.device.ConnectDevice;
import com.codingtu.cooltu.lib4a.log.Logs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cooltu.lib4j.data.map.ListValueMap;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;

public class ConnectTool {

    public static ConnectService SERVICE;
    private static ListValueMap<String, ConnectCallBack> CALLBACKS;
    private static Map<String, ConnectDevice> PREPARED_DEVICES;
    private static Map<String, ConnectDevice> RUNNING_DEVICES;

    private static CoreConnectConfigs configs() {
        return CoreConnectConfigs.configs();
    }

    private static ListValueMap<String, ConnectCallBack> callbacks() {
        if (CALLBACKS == null) {
            CALLBACKS = new ListValueMap<>();
        }
        return CALLBACKS;
    }

    public static Map<String, ConnectDevice> preparedDevices() {
        if (PREPARED_DEVICES == null) {
            PREPARED_DEVICES = new HashMap<>();
        }
        return PREPARED_DEVICES;
    }

    private static Map<String, ConnectDevice> runningDevices() {
        if (RUNNING_DEVICES == null) {
            RUNNING_DEVICES = new HashMap<>();
        }
        return RUNNING_DEVICES;
    }

    private static void addCallBack(String connectType, ConnectCallBack connectCallBack) {
        List<ConnectCallBack> connectCallBacks = callbacks().get(connectType);
        if (!connectCallBacks.contains(connectCallBacks)) {
            connectCallBacks.add(connectCallBack);
        }
    }

    private static void removeCallBack(String connectType, ConnectCallBack connectCallBack) {
        callbacks().get(connectType).remove(connectCallBack);
    }

    public static void connect(Context context, String connectType, ConnectCallBack connectCallBack) {
        ConnectDevice connectDevice = runningDevices().get(connectType);
        if (connectDevice == null) {
            //没有运行的
            connectDevice = configs().getLocalCachedConnectDevice(connectType);
            if (connectDevice == null) {
                //本地也没有
                connectCallBack.noDevice();
            } else {
                extracted(context, connectType, connectCallBack, connectDevice);
            }
        } else {
            //有运行的
            ConnectDevice connectDeviceLocal = configs().getLocalCachedConnectDevice(connectType);
            if (connectDeviceLocal == null || connectDevice.baseData.deviceType.equals(connectDeviceLocal.baseData.deviceType)) {
                //有正在运行的相关设备，说明连接成功
                addCallBack(connectType, connectCallBack);
                connectCallBack.connectSuccess(connectDevice);
            } else {
                connectDevice.disconnect(new ConnectDevice.DisconnectFinish() {
                    @Override
                    public void onFinished() {
                        extracted(context, connectType, connectCallBack, connectDeviceLocal);
                    }
                });
            }
        }
    }

    private static void extracted(Context context, String connectType, ConnectCallBack connectCallBack, ConnectDevice connectDevice) {
        //本地有
        connectCallBack.connecting(connectDevice);
        addCallBack(connectType, connectCallBack);
        if (SERVICE == null) {
            preparedDevices().put(connectType, connectDevice);
            createService(context);
        } else {
            SERVICE.run(connectDevice);
        }
    }

    private static void createService(Context context) {
        Intent intent = new Intent(context, ConnectService.class);
        context.startService(intent);
    }

    public static void dealMsg(ConnectDevice connectDevice, int msgType, Object obj) {
        List<ConnectCallBack> connectCallBacks = callbacks().get(connectDevice.baseData.connectType);
        Ts.ls(connectCallBacks, new Each<ConnectCallBack>() {
            @Override
            public boolean each(int position, ConnectCallBack connectCallBack) {
                switch (msgType) {
                    case ConnectStatus.FAIL:
                        connectCallBack.connectFail();
                        break;
                    case ConnectStatus.SUCCESS:
                        connectCallBack.connectSuccess(connectDevice);
                        break;
                    case ConnectStatus.READ:
                        ResponseData[] datas = connectDevice.parseResponseDatas((byte[]) obj);
                        int count = CountTool.count(datas);
                        if (count > 0) {
                            for (int i = 0; i < count; i++) {
                                ResponseData data = datas[i];
                                connectCallBack.read(connectDevice, data);
                            }
                        }
                        break;
                    case ConnectStatus.DISCONNECT:
                        connectCallBack.disconnect(connectDevice);
                        break;
                }
                return false;
            }
        });

        switch (msgType) {
            case ConnectStatus.SUCCESS:
                runningDevices().put(connectDevice.baseData.connectType, connectDevice);
                break;
            case ConnectStatus.DISCONNECT:
                runningDevices().put(connectDevice.baseData.connectType, null);
            case ConnectStatus.FAIL:
                connectCallBacks.clear();
                break;
        }
    }

    public static void logBluetoothServices(BluetoothGatt gatt) {
        List<BluetoothGattService> services = gatt.getServices();
        Ts.ls(services, new Each<BluetoothGattService>() {
            @Override
            public boolean each(int position, BluetoothGattService service) {
                Logs.i("======================================");
                Logs.i("sercie:" + service.getUuid().toString());

                List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
                Ts.ls(characteristics, new Each<BluetoothGattCharacteristic>() {
                    @Override
                    public boolean each(int position, BluetoothGattCharacteristic characteristic) {
                        Logs.i("    characteristic:" + characteristic.getUuid().toString());
                        Logs.i("    characteristic getProperties:" + characteristic.getProperties());
                        List<BluetoothGattDescriptor> descriptors = characteristic.getDescriptors();
                        Ts.ls(descriptors, new Each<BluetoothGattDescriptor>() {
                            @Override
                            public boolean each(int position, BluetoothGattDescriptor descriptor) {
                                Logs.i("        descriptor:" + descriptor.getUuid().toString());
                                return false;
                            }
                        });
                        return false;
                    }
                });
                return false;
            }
        });
    }

    /**************************************************
     *
     * 蓝牙的绑定
     *
     **************************************************/

}
