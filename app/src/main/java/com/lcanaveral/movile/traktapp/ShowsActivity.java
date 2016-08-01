package com.lcanaveral.movile.traktapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.support.v4.app.DialogFragment;
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
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_shows)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class ShowsActivity extends AppCompatActivity {


    private static final String LOG_TAG = ShowsActivity.class.getSimpleName();

    @ViewById protected RecyclerView shows;
    @Bean protected Api mApi;

    private ProgressDialog mloadingDialog;

    @AfterViews
    protected void init() {
        showLoading();
        fetch();
    }

    @UiThread
    protected void updateView(List<Show> shows){


        this.shows.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        this.shows.setAdapter(new ShowAdapter(getApplicationContext(), shows));

        ItemClickSupport.addTo(this.shows).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.i(LOG_TAG, "onItemClicked");

                ShowViewHolder holder = (ShowViewHolder) v;
                Intent intent = ShowActivity_.intent(getApplicationContext()).get();
                intent.putExtra("SHOW", holder.getReference());
                startActivityForResult(intent,1);
            }
        });

        closeLoading();
    }



    @Background
    protected void fetch(){
        Log.i(LOG_TAG, "fetchShows");

        final List<Show> shows = mApi.getPopularShows();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateView(shows);
            }
        });

    }

    private void showLoading() {
        mloadingDialog = new ProgressDialog(this, DialogFragment.STYLE_NO_INPUT);
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



}
