package com.lcanaveral.movile.traktapp.ui.seasons;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcanaveral.movile.traktapp.R;
import com.lcanaveral.movile.traktapp.ui.episodes.EpisodeAdapter;
import com.lcanaveral.movile.traktapp.ui.layout.CustomLinearLayoutManager;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by lcanaveral on 7/29/16.
 */
@EViewGroup(R.layout.item_season)
public class SeasonViewHolder extends LinearLayout {
    @ViewById protected TextView title;
    @ViewById protected RecyclerView episodes;

    Context context;

    public SeasonViewHolder(Context context) {
        super(context);
        this.context = context;
    }

    public void bind(Season season){
        Log.d("--SeasonViewHolder.bind", ""+season.number);

        title.setText("Season " + season.number);

        this.episodes.setAdapter(new EpisodeAdapter(context, season.episodes));
        this.episodes.setLayoutManager(new CustomLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

    }
}
