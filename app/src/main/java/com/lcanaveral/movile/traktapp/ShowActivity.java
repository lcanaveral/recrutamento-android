package com.lcanaveral.movile.traktapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcanaveral.movile.traktapp.api.Api;
import com.lcanaveral.movile.traktapp.api.payloads.RatingPayload;
import com.lcanaveral.movile.traktapp.api.payloads.SeasonPayload;
import com.lcanaveral.movile.traktapp.ui.shows.Show;
import com.lcanaveral.movile.traktapp.utils.DownloadImageTask;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_show)
public class ShowActivity extends AppCompatActivity {


    private static final String LOG_TAG = "Show";


    private Show show;

    @ViewById
    protected ImageView header;
    @ViewById
    protected ImageView poster;
    @ViewById
    protected TextView rating;

    @AfterViews
    void init(){
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();

        show = new Show();
        if (extras != null) {
            String value = extras.getString("REFERENCE_TITLE");
            show = extras.getParcelable("SHOW");
        }

        new DownloadImageTask(header).execute(show.logo);
        new DownloadImageTask(poster).execute(show.poster);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            //toolbar.setTitle("Luis Carlos");
            getSupportActionBar().setTitle(show.title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        fetchSeasons();
        //fetchRaiting();

    }


    @Background
    protected void fetchSeasons(){
        List<SeasonPayload> _seasons = Api.getTrakt().getSeasons(show.slug);

    }

    @Background
    protected void fetchRaiting(){
        RatingPayload _rating = Api.getTrakt().getRating(show.slug);

        rating.setText(""+_rating.rating);

    }
}
