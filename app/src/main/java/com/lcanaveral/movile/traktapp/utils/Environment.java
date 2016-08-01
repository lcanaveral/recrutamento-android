package com.lcanaveral.movile.traktapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by lcanaveral on 7/31/16.
 */
@EBean
public class Environment {

    @RootContext protected Context mContext;

    private static Environment sInstance = new Environment();

    public boolean isNetworkAvailable(){
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        return nf != null && nf.isConnected();
    }

    public boolean isWifiConnection(){
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        return nf != null && nf.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public boolean isMobileConnection(){
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        return nf != null && nf.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    protected Environment(){}

    public static Environment getInstance(){
        return sInstance;
    }





}
