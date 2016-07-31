package com.lcanaveral.movile.traktapp.ui.episodes;

import com.lcanaveral.movile.traktapp.api.payloads.EpisodePayload;

/**
 * Created by lcanaveral on 7/30/16.
 */
public class Episode {
    public String title;
    public int number;

    public Episode(EpisodePayload episodePayload){
        title = episodePayload.title;
        number = episodePayload.number;

    }
}
