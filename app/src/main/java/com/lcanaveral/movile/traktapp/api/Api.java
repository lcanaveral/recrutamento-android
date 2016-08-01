package com.lcanaveral.movile.traktapp.api;

import android.app.Application;
import android.media.Rating;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lcanaveral.movile.traktapp.BuildConfig;
import com.lcanaveral.movile.traktapp.api.exceptions.NoNetworkException;
import com.lcanaveral.movile.traktapp.api.payloads.EpisodePayload;
import com.lcanaveral.movile.traktapp.api.payloads.RatingPayload;
import com.lcanaveral.movile.traktapp.api.payloads.SeasonPayload;
import com.lcanaveral.movile.traktapp.api.payloads.ShowPayload;
import com.lcanaveral.movile.traktapp.ui.episodes.Episode;
import com.lcanaveral.movile.traktapp.ui.seasons.Season;
import com.lcanaveral.movile.traktapp.ui.shows.Show;
import com.lcanaveral.movile.traktapp.utils.Environment;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import static com.lcanaveral.movile.traktapp.utils.Environment.*;

/**
 * Created by lcanaveral on 7/28/16.
 */
@EBean
public class Api {
    private static TraktServices sTraktServices;
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

        sTraktServices = retrofit.create(TraktServices.class);
    }

    @Bean protected Environment mEnvironment;

    /*public static TraktServices getTrakt(){
        return services;
    }*/

    public List<Season> getSeasons(String slug) throws NoNetworkException {
        /*if(!(getInstance().isNetworkAvailable())){
            //throw new Environment.NoNetworkAvailableException();
        }*/

        if(!mEnvironment.isNetworkAvailable()){
            throw new NoNetworkException();
        }

        List<SeasonPayload> seasons = sTraktServices.getSeasons(slug);
        return Translator.toSeasons(seasons);
    }

    public List<Show> getPopularShows(){
        List<ShowPayload> shows = sTraktServices.getPopularShows();
        return Translator.toShows(shows);
    }

    public RatingPayload getRating(String slug){
        return sTraktServices.getRating(slug);
    }


    private static class Translator {
        public static List<Show> toShows(List<ShowPayload> _shows){
            List<Show> shows = new ArrayList<Show>();
            for(ShowPayload showPaiload: _shows){
                shows.add(new Show(showPaiload));
            }
            return shows;
        }
        public static List<Season> toSeasons(List<SeasonPayload> _seasons) {
            List<Season> seasons = new ArrayList<Season>();
            for (SeasonPayload _s : _seasons) {
                Season s = new Season();
                s.number = _s.number;
                s.episodes = new ArrayList<Episode>();
                for (EpisodePayload episode : _s.episodes) {
                    s.episodes.add(new Episode(episode));
                }
                seasons.add(s);
            }
            return seasons;
        }
    }


}
