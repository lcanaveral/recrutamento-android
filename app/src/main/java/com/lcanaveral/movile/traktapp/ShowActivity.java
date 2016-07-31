package com.lcanaveral.movile.traktapp;

import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcanaveral.movile.traktapp.api.Api;
import com.lcanaveral.movile.traktapp.api.payloads.EpisodePayload;
import com.lcanaveral.movile.traktapp.api.payloads.RatingPayload;
import com.lcanaveral.movile.traktapp.api.payloads.SeasonPayload;
import com.lcanaveral.movile.traktapp.api.payloads.ShowPayload;
import com.lcanaveral.movile.traktapp.ui.episodes.Episode;
import com.lcanaveral.movile.traktapp.ui.episodes.EpisodeAdapter;
import com.lcanaveral.movile.traktapp.ui.layout.CustomLinearLayoutManager;
import com.lcanaveral.movile.traktapp.ui.seasons.Season;
import com.lcanaveral.movile.traktapp.ui.seasons.SeasonAdapter;
import com.lcanaveral.movile.traktapp.ui.shows.Show;
import com.lcanaveral.movile.traktapp.ui.shows.ShowAdapter;
import com.lcanaveral.movile.traktapp.utils.DownloadImageTask;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_show)
public class ShowActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {


    private static final String LOG_TAG = ShowActivity.class.getSimpleName();


    private Show mShow;

    @ViewById protected ImageView header;
    @ViewById protected ImageView poster;
    @ViewById protected TextView rating;
    @ViewById protected RecyclerView seasons;
    //@ViewById protected RecyclerView episodes;

    @AfterViews
    void init() {

        Bundle extras = getIntent().getExtras();

        mShow = new Show();
        mShow.poster = "https://walter.trakt.us/images/shows/000/001/390/posters/thumb/93df9cd612.jpg";
        mShow.logo = "https://walter.trakt.us/images/shows/000/001/390/posters/thumb/93df9cd612.jpg";
        mShow.slug = "game-of-thrones";
        mShow.title = "game of thrones";

        if (extras != null) {
            String value = extras.getString("REFERENCE_TITLE");
            mShow = extras.getParcelable("SHOW");
        }

        new DownloadImageTask(header).execute(mShow.logo);
        new DownloadImageTask(poster).execute(mShow.poster);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(mShow.title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        AppBarLayout appbar = (AppBarLayout) findViewById(R.id.app_bar);
        appbar.addOnOffsetChangedListener(this);


        //fetchRaiting();
        fetchSeasons();

    }

    @UiThread
    protected void onEpisodesFeched(List<Season> seasons) {
        Log.d(LOG_TAG, "onEpisodesFeched " + seasons.size());



        this.seasons.setAdapter(new SeasonAdapter(getApplicationContext(), seasons));
        this.seasons.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



        //this.episodes.setAdapter(new EpisodeAdapter(getApplicationContext(), seasons.get(0).episodes));
        //this.episodes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }

    @Background
    protected void fetchSeasons() {
        List<SeasonPayload> _seasons = Api.getTrakt().getSeasons(mShow.slug);

        final List<Episode> episodes = new ArrayList<Episode>();
        final List<Season> seasons = new ArrayList<Season>();

        for (SeasonPayload _s : _seasons) {
            Season s = new Season();
            s.number = _s.number;
            s.episodes = new ArrayList<Episode>();
            for (EpisodePayload episode : _s.episodes) {
                s.episodes.add(new Episode(episode));
            }
            seasons.add(s);
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onEpisodesFeched(seasons);
            }
        });

    }

    @Background
    protected void fetchRaiting() {
        final RatingPayload _rating = Api.getTrakt().getRating(mShow.slug);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rating.setText("" + String.valueOf(_rating.rating).substring(0, 3));
            }
        });

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        float fixedPercentage = 1 - percentage;

        poster.setAlpha(fixedPercentage);
        //poster.getLayoutParams().width = (int)((150)*(fixedPercentage));
        //poster.getLayoutParams().height = (int)((225)*(fixedPercentage));

        //Log.i(LOG_TAG, "" +poster.getLayoutParams().width + " " + fixedPercentage);
    }
}
