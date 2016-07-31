package com.lcanaveral.movile.traktapp.ui.seasons;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.lcanaveral.movile.traktapp.ui.shows.Show;

import java.util.List;

/**
 * Created by lcanaveral on 7/30/16.
 */
public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.InternalViewHolder> {
    private Context context;

    private List<Season> seasons;

    public SeasonAdapter(Context context,List<Season> shows){
        this.context = context;
        this.seasons = shows;
    }

    @Override
    public InternalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InternalViewHolder();
    }

    @Override
    public void onBindViewHolder(InternalViewHolder holder, int position) {
        holder.bind(this.seasons.get(position));
    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }


    public class InternalViewHolder extends RecyclerView.ViewHolder {
        public InternalViewHolder() {
            super(SeasonViewHolder_.build(context));
        }
        public void bind(Season season) {
            ((SeasonViewHolder) this.itemView).bind(season);
        }
    }

}
