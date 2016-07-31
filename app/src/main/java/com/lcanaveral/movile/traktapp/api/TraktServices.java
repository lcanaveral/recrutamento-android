package com.lcanaveral.movile.traktapp.api;

import com.lcanaveral.movile.traktapp.BuildConfig;
import com.lcanaveral.movile.traktapp.api.payloads.RatingPayload;
import com.lcanaveral.movile.traktapp.api.payloads.SeasonPayload;
import com.lcanaveral.movile.traktapp.api.payloads.ShowPayload;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by lcanaveral on 7/28/16.
 */
public interface TraktServices {


    @Headers({
            "Content-Type: application/json",
            "trakt-api-version:2",
            "trakt-api-key:"+ BuildConfig.TRAKT_CLIENT_ID
    })
    @GET("/shows/popular?extended=images")
    void getPopularShowsWithCallback(Callback<ArrayList<ShowPayload>> response);


    @Headers({
            "Content-Type: application/json",
            "trakt-api-version:2",
            "trakt-api-key:"+ BuildConfig.TRAKT_CLIENT_ID
    })
    @GET("/shows/popular?extended=images")
    ArrayList<ShowPayload> getPopularShows();

    @Headers({
            "Content-Type: application/json",
            "trakt-api-version:2",
            "trakt-api-key:"+ BuildConfig.TRAKT_CLIENT_ID
    })
    @GET("/shows/{slug}/seasons?extended=episodes")
    ArrayList<SeasonPayload> getSeasons(@Path("slug") String slug);


    @Headers({
            "Content-Type: application/json",
            "trakt-api-version:2",
            "trakt-api-key:"+ BuildConfig.TRAKT_CLIENT_ID
    })
    @GET("/shows/{slug}/ratings")
    RatingPayload getRating(@Path("slug") String slug);
}
