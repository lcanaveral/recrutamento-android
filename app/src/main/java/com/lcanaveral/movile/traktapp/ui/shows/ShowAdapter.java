package com.lcanaveral.movile.traktapp.ui.shows;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lcanaveral on 7/29/16.
 */
public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.InternalViewHolder>{

    private Context context;

    private List<Show> shows;

    public ShowAdapter(Context context,List<Show> shows){
        this.context = context;
        this.shows = shows;
    }


    @Override
    public InternalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InternalViewHolder();
    }

    @Override
    public void onBindViewHolder(InternalViewHolder holder, int position) {
        holder.bind(this.shows.get(position));
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }


    public class InternalViewHolder extends RecyclerView.ViewHolder {
        public InternalViewHolder() {
            super(ShowViewHolder_.build(context));
        }

        public void bind(Show show) {
            ((ShowViewHolder) this.itemView).bind(show);
        }
    }

}
