package com.lcanaveral.movile.traktapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcanaveral.movile.traktapp.api.Api;
import com.lcanaveral.movile.traktapp.api.exceptions.NoNetworkException;
import com.lcanaveral.movile.traktapp.api.payloads.RatingPayload;
import com.lcanaveral.movile.traktapp.ui.layout.ExpansiveLayoutManager;
import com.lcanaveral.movile.traktapp.ui.seasons.Season;
import com.lcanaveral.movile.traktapp.ui.seasons.SeasonAdapter;
import com.lcanaveral.movile.traktapp.ui.shows.Show;
import com.lcanaveral.movile.traktapp.utils.DownloadImageTask;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.net.UnknownHostException;
import java.util.List;


@EActivity(R.layout.activity_show)
public class ShowActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {


    private static final String LOG_TAG = ShowActivity.class.getSimpleName();


    private Show mShow;

    @ViewById protected ImageView header;
    @ViewById protected ImageView poster;
    @ViewById protected TextView rating;
    @ViewById protected RecyclerView seasons;
    @Bean protected Api mApi;

    private ProgressDialog mloadingDialog;

    //@ViewById protected RecyclerView episodes;

    @AfterViews
    protected void init() {

        Bundle extras = getIntent().getExtras();

        mShow = new Show();
        mShow.poster = "https://walter.trakt.us/images/shows/000/001/390/posters/thumb/93df9cd612.jpg";
        mShow.logo = "https://walter.trakt.us/images/shows/000/001/390/fanarts/thumb/76d5df8aed.jpg";
        mShow.slug = "game-of-thrones";
        mShow.title = "Game Of Thrones";

        if (extras != null) {
            mShow = extras.getParcelable("SHOW");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(mShow.title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        AppBarLayout appbar = (AppBarLayout) findViewById(R.id.app_bar);
        appbar.addOnOffsetChangedListener(this);

        showLoading();
        fetch();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode,event);
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        //startActivity(new Intent(ThisActivity.this, NextActivity.class));
        Intent intent = ShowsActivity_.intent(getApplicationContext()).get();
        startActivity(intent);
        finish();

    }

    @UiThread
    protected void updateView(List<Season> seasons, double rating) {

        Log.d(LOG_TAG, "onEpisodesFeched " + seasons.size());



        this.seasons.setAdapter(new SeasonAdapter(getApplicationContext(), seasons));
        this.seasons.setLayoutManager(new ExpansiveLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        this.rating.setText("" + String.valueOf(rating).substring(0, 3));

        closeLoading();
    }

    @Background
    protected void fetch() {

        final List<Season> seasons ;
        final RatingPayload rating;
        try {
            seasons = mApi.getSeasons(mShow.slug);
            rating = mApi.getRating(mShow.slug);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateView(
                            seasons,
                            rating.rating
                    );
                }
            });

            new DownloadImageTask(header).execute(mShow.logo);
            new DownloadImageTask(poster).execute(mShow.poster);

        } catch (final Exception e){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showError(e.getMessage());
                }
            });

        }


    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
        float fixedPercentage = 1 - percentage;

        poster.setAlpha(fixedPercentage);

        if(percentage == 1){
            //rating.getParent().setVisibility(View.INVISIBLE);
        }
        //poster.getLayoutParams().width = (int)((150)*(fixedPercentage));
        //poster.getLayoutParams().height = (int)((225)*(fixedPercentage));

        //Log.i(LOG_TAG, "" +poster.getLayoutParams().width + " " + fixedPercentage);
    }

    private void showLoading() {
        mloadingDialog = new ProgressDialog(this,DialogFragment.STYLE_NO_INPUT);
        mloadingDialog.setTitle(R.string.loading);
        mloadingDialog.setCancelable(false);
        mloadingDialog.setIndeterminate(true);
        mloadingDialog.show();
    }

    private void closeLoading(){
        try {
            if (mloadingDialog != null && mloadingDialog.isShowing()){
                mloadingDialog.dismiss();
            }
        } catch (Exception anyExceptionIgnored) {
        } finally {
            mloadingDialog = null;
        }
    }

    private void showError(String message) {
        closeLoading();
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.episode_error_title))
                .setMessage(message)
                .setPositiveButton(getString(R.string.try_again_message), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        init();
                    }
                })
                .setNegativeButton(getString(R.string.close), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }


}
