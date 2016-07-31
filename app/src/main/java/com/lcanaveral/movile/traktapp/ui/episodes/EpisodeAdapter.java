package com.lcanaveral.movile.traktapp.ui.episodes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lcanaveral on 7/30/16.
 */
public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.InternalViewHolder> {


    private Context mContext;

    private List<Episode> mEpisodes;

    public EpisodeAdapter(Context context,List<Episode> episodes){
        this.mContext = context;
        this.mEpisodes = episodes;
    }

    @Override
    public InternalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InternalViewHolder();
    }

    @Override
    public void onBindViewHolder(InternalViewHolder holder, int position) {
        holder.bind(this.mEpisodes.get(position));
    }

    @Override
    public int getItemCount() {
        return mEpisodes.size();
    }

    public class InternalViewHolder extends RecyclerView.ViewHolder {
        public InternalViewHolder() {
            super(EpisodeViewHolder_.build(mContext));
        }

        public void bind(Episode episode) {
            ((EpisodeViewHolder) this.itemView).bind(episode);
        }
    }
}
