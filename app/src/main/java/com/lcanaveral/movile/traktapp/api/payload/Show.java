package com.lcanaveral.movile.traktapp.api.payload;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lcanaveral on 7/28/16.
 */
public class Show {
    public String title;
    @SerializedName("ids")
    public IDs information;
    public Images images;

    public List<Season> seasons;


}
