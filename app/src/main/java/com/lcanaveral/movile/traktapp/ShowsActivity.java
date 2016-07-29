package com.lcanaveral.movile.traktapp;

import android.app.ProgressDialog;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.lcanaveral.movile.traktapp.api.Api;
import com.lcanaveral.movile.traktapp.api.payload.Show;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.WindowFeature;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@EActivity(R.layout.activity_shows)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class ShowsActivity extends AppCompatActivity {

    private ProgressDialog loading;


    private static final String LOG_TAG = "ShowsActivity";

    @AfterViews
    protected void AfterViews() {
        Log.i(LOG_TAG, "afterViews");

        loading = ProgressDialog.show(this, "Loading", "Please wait...", false, false);
        fetchShows();
    }

    @UiThread
    protected void onShowsFeched(){
        if(loading != null){
            loading.dismiss();
        }
    }

    @Background
    protected void fetchShows(){
        Log.i(LOG_TAG, "fetchShows");

        List<Show> shows = Api.getTrakt().getPopularShows();

        /*for(Show s: shows){
            s.seasons = Api.getTrakt().getSeasons(s.information.slug);
        }*/

        onShowsFeched();

    }

}
