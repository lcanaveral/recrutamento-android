package com.lcanaveral.movile.traktapp.api.payloads;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lcanaveral on 7/28/16.
 */
public class ShowPayload {
    public String title;
    public String slug;
    @SerializedName("ids")
    public IDs information;
    public Images images;

    public List<SeasonPayload> seasons;


}
