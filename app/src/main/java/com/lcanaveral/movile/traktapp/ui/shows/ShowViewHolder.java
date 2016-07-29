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
@EViewGroup(R.layout.show_item)
public class ShowViewHolder extends LinearLayout{
    @ViewById
    protected TextView title;
    @ViewById
    protected ImageView poster;

    private boolean isImageLoaded = false;

    private Show reference;


    public ShowViewHolder(Context context) {
        super(context);
    }

    public void bind(Show show){
        reference = show;

        title.setText(show.title);

        if(!isImageLoaded) {
            final ImageView reference = poster;
            new DownloadImageTask(reference).execute(show.poster);
            isImageLoaded = true;
        }

    }

    public Show getReference() {
        return reference;
    }
}
