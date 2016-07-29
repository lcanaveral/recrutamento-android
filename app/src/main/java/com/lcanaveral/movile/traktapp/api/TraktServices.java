package com.lcanaveral.movile.traktapp.api;

import com.lcanaveral.movile.traktapp.BuildConfig;
import com.lcanaveral.movile.traktapp.api.payload.Season;
import com.lcanaveral.movile.traktapp.api.payload.Show;

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
    void getPopularShowsWithCallback(Callback<ArrayList<Show>> response);


    @Headers({
            "Content-Type: application/json",
            "trakt-api-version:2",
            "trakt-api-key:"+ BuildConfig.TRAKT_CLIENT_ID
    })
    @GET("/shows/popular?extended=images")
    ArrayList<Show> getPopularShows();

    @Headers({
            "Content-Type: application/json",
            "trakt-api-version:2",
            "trakt-api-key:"+ BuildConfig.TRAKT_CLIENT_ID
    })
    @GET("/shows/{slug}/seasons?extended=episodes")
    ArrayList<Season> getSeasons(@Path("slug") String slug);
}
