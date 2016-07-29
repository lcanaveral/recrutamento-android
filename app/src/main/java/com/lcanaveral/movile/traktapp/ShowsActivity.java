package com.lcanaveral.movile.traktapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.lcanaveral.movile.traktapp.api.Api;
import com.lcanaveral.movile.traktapp.api.payloads.ShowPayload;
import com.lcanaveral.movile.traktapp.ui.ItemClickSupport;
import com.lcanaveral.movile.traktapp.ui.shows.Show;
import com.lcanaveral.movile.traktapp.ui.shows.ShowAdapter;
import com.lcanaveral.movile.traktapp.ui.shows.ShowViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_shows)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class ShowsActivity extends AppCompatActivity {

    private ProgressDialog loading;


    private static final String LOG_TAG = "Shows";

    @ViewById
    protected RecyclerView shows;



    @AfterViews
    protected void AfterViews() {
        Log.i(LOG_TAG, "afterViews");

        loading = ProgressDialog.show(this, "Loading", "Please wait...", false, false);

        fetchShows();
    }

    @UiThread
    protected void onShowsFeched(List<Show> shows){
        Log.i(LOG_TAG, "onShowsFeched");
        if(loading != null){
            loading.dismiss();
        }
        this.shows.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        this.shows.setAdapter(new ShowAdapter(getApplicationContext(), shows));

        ItemClickSupport.addTo(this.shows).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.i(LOG_TAG, "onItemClicked");

                ShowViewHolder holder = (ShowViewHolder) v;

                //ShowsActivity_.intent(getApplicationContext()).start();

                Intent intent = ShowActivity_.intent(getApplicationContext()).get();

                intent.putExtra("REFERENCE_TITLE", holder.getReference().title);
                intent.putExtra("SHOW", holder.getReference());

                startActivityForResult(intent,1);
            }
        });
    }



    @Background
    protected void fetchShows(){
        Log.i(LOG_TAG, "fetchShows");

        List<ShowPayload> _shows = Api.getTrakt().getPopularShows();
        final List<Show> shows = new ArrayList<Show>();

        for(ShowPayload showPaiload: _shows){
            shows.add(new Show(showPaiload));
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onShowsFeched(shows);
            }
        });

    }



}
