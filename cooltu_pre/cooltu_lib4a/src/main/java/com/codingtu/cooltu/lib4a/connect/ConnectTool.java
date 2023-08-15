package com.codingtu.cooltu.lib4a.connect;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.Intent;

import com.codingtu.cooltu.lib4a.connect.device.ConnectDevice;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.PfTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cooltu.lib4j.data.map.ListValueMap;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;

@SuppressLint("MissingPermission")
public class ConnectTool {

    public static ConnectService SERVICE;
    private static ListValueMap<Integer, ConnectCallBack> CALLBACKS;
    private static Map<Integer, ConnectDevice> PREPARED_DEVICES;
    private static Map<Integer, ConnectDevice> RUNNING_DEVICES;

    private static CoreConnectConfigs configs() {
        return CoreConnectConfigs.configs();
    }

    private static ListValueMap<Integer, ConnectCallBack> callbacks() {
        if (CALLBACKS == null) {
            CALLBACKS = new ListValueMap<>();
        }
        return CALLBACKS;
    }

    public static Map<Integer, ConnectDevice> preparedDevices() {
        if (PREPARED_DEVICES == null) {
            PREPARED_DEVICES = new HashMap<>();
        }
        return PREPARED_DEVICES;
    }

    private static Map<Integer, ConnectDevice> runningDevices() {
        if (RUNNING_DEVICES == null) {
            RUNNING_DEVICES = new HashMap<>();
        }
        return RUNNING_DEVICES;
    }

    private static void addCallBack(int connectType, ConnectCallBack connectCallBack) {
        List<ConnectCallBack> connectCallBacks = callbacks().get(connectType);
        if (!connectCallBacks.contains(connectCallBacks)) {
            connectCallBacks.add(connectCallBack);
        }
    }

    private static void removeCallBack(int connectType, ConnectCallBack connectCallBack) {
        callbacks().get(connectType).remove(connectCallBack);
    }

    public static void connect(Context context, int connectType, ConnectCallBack connectCallBack) {
        ConnectDevice connectDevice = runningDevices().get(connectType);
        if (connectDevice == null) {
            //没有运行的
            connectDevice = getLocalCachedConnectDevice(connectType);
            if (connectDevice == null) {
                //本地也没有
                connectCallBack.noDevice();
            } else {
                extracted(context, connectType, connectCallBack, connectDevice);
            }
        } else {
            //有运行的
            ConnectDevice connectDeviceLocal = getLocalCachedConnectDevice(connectType);
            if (connectDeviceLocal == null || connectDevice.baseData.deviceType == connectDeviceLocal.baseData.deviceType) {
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

    private static void extracted(Context context, int connectType, ConnectCallBack connectCallBack, ConnectDevice connectDevice) {
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
     *
     *
     **************************************************/

    private static Map<Integer, String> connectLinkCacheKeyMap;

    private static Map<Integer, String> connectLinkCacheKeyMap() {
        if (connectLinkCacheKeyMap == null) {
            connectLinkCacheKeyMap = new HashMap<>();
            CoreConnectConfigs.configs().connectLinkCacheKeyMap(connectLinkCacheKeyMap);
        }
        return connectLinkCacheKeyMap;
    }

    private static String cacheKey(int connectType) {
        return connectLinkCacheKeyMap().get(connectType);
    }

    private static ConnectDevice getLocalCachedConnectDevice(int connectType) {
        ConnectDeviceBaseData baseData = PfTool.getLastConnectDeviceBaseData(cacheKey(connectType));
        if (baseData != null) {
            return CoreConnectConfigs.configs().createConnectDevice(baseData);
        }
        return null;
    }

    public void cacheConnectDeviceBaseData(int connectType, int deviceType, String name, String mac) {
        PfTool.cacheLastConnectDeviceBaseData(
                cacheKey(connectType),
                new ConnectDeviceBaseData(connectType, deviceType, name, mac));
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public static interface BluetoothBondCallBack {
        void bonded(int connectType, int deviceType, String name, String address);

        void deviceNoMatch();

        void bonding();
    }


    public static void bonded(int connectType, int deviceType, BluetoothDevice device, BluetoothBondCallBack callBack) {
        if (isBluetoothDirectBond(connectType, deviceType)) {
            callBack.bonded(connectType, deviceType, device.getName(), device.getAddress());
        } else if (isBluetoothPairBond(connectType, deviceType)) {
            if (BluetoothDevice.BOND_NONE == device.getBondState()) {
                if (device.createBond()) {
                    callBack.bonding();
                }
            } else {
                callBack.bonded(connectType, deviceType, device.getName(), device.getAddress());
            }
        } else {
            callBack.deviceNoMatch();
        }
    }

    private static BondMethodMap bondMethodMap;

    private static BondMethodMap getBondMethodMap() {
        if (bondMethodMap == null) {
            bondMethodMap = new BondMethodMap();
        }
        return bondMethodMap;
    }

    private static boolean isBluetoothDirectBond(int connectType, int deviceType) {
        return isBluetoothBonded(connectType, deviceType, BondMethod.DIRECT);
    }

    private static boolean isBluetoothPairBond(int connectType, int deviceType) {
        return isBluetoothBonded(connectType, deviceType, BondMethod.PAIR);
    }


    private static boolean isBluetoothBonded(int connectType, int deviceType, BondMethod method) {
        return getBondMethodMap().is(connectType, deviceType, method);
    }

}
