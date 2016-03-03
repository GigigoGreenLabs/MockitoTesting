package es.beni.testing.exercise3;

import android.bluetooth.BluetoothAdapter;

/** We have to use spyies to test this class */

public class BluetoothMacProvider {

    public BluetoothAdapter provideBluetoothDefaultAdapter() {
        return BluetoothAdapter.getDefaultAdapter();
    }

    public String provideBluetoothMac(BluetoothAdapter mBluetoothAdapter) {
        if (mBluetoothAdapter == null) {
            return null;
        }else {
            return mBluetoothAdapter.getAddress();
        }
    }
}
