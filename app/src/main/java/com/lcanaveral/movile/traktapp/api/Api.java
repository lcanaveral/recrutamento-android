package com.lcanaveral.movile.traktapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lcanaveral.movile.traktapp.BuildConfig;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by lcanaveral on 7/28/16.
 */
public class Api {
    private static TraktServices services;

    static {
        Gson gson = new GsonBuilder()
                .create();

        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint(BuildConfig.TRAKT_END_POINT)
                .setConverter(new GsonConverter(gson))
                .build();

        if(BuildConfig.TRAKT_RETROFIT_DEBUG) {
            retrofit.setLogLevel(RestAdapter.LogLevel.FULL);
        }

        services = retrofit.create(TraktServices.class);
    }

    public static TraktServices getTrakt(){
        return services;
    }


}
