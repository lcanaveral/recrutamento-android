package com.lcanaveral.movile.traktapp.ui.episodes;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcanaveral.movile.traktapp.R;
import com.lcanaveral.movile.traktapp.ui.layout.CustomLinearLayoutManager;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by lcanaveral on 7/30/16.
 */
@EViewGroup(R.layout.item_episode)
public class EpisodeViewHolder extends LinearLayout {

    @ViewById protected TextView title;
    @ViewById protected TextView number;

    public EpisodeViewHolder(Context context) {
        super(context);
    }

    public void bind(Episode episode){
        setNestedScrollingEnabled(false);
        title.setText(episode.title);
        number.setText("E"+episode.number);
    }

}
