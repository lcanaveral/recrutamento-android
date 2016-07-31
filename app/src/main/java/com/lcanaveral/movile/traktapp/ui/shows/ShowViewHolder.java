package com.lcanaveral.movile.traktapp.ui.shows;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcanaveral.movile.traktapp.R;
import com.lcanaveral.movile.traktapp.utils.DownloadImageTask;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by lcanaveral on 7/29/16.
 */
@EViewGroup(R.layout.item_show)
public class ShowViewHolder extends LinearLayout{
    @ViewById protected TextView title;
    @ViewById protected ImageView poster;

    private boolean mSsImageLoaded = false;

    private Show mReference;


    public ShowViewHolder(Context context) {
        super(context);
    }

    public void bind(Show show){
        mReference = show;

        title.setText(show.title);

        if(!mSsImageLoaded) {
            final ImageView reference = poster;
            new DownloadImageTask(reference).execute(show.poster);
            mSsImageLoaded = true;
        }

    }

    public Show getReference() {
        return mReference;
    }
}
