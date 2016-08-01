package com.lcanaveral.movile.traktapp.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.lcanaveral.movile.traktapp.utils.Environment;

/**
 * Created by lcanaveral on 7/31/16.
 */
public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            Environment.getInstance().isNetworkAvailable();
        }
    }
}
