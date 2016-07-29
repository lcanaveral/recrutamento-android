package com.lcanaveral.movile.traktapp.ui.shows;

import android.os.Parcel;
import android.os.Parcelable;

import com.lcanaveral.movile.traktapp.api.payloads.ShowPayload;

/**
 * Created by lcanaveral on 7/29/16.
 */
public class Show implements Parcelable {
    public String title;
    public String poster;
    public String logo;
    public String slug;

    public Show(ShowPayload show){
        title = show.title;
        poster = show.images.poster.thumb;
        logo = show.images.logo.full;
        slug = show.information.slug;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(poster);
        out.writeString(logo);
        out.writeString(slug);
    }


    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Show> CREATOR = new Parcelable.Creator<Show>() {
        public Show createFromParcel(Parcel in) {
            return new Show(in);
        }

        public Show[] newArray(int size) {
            return new Show[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Show(Parcel in) {
        title = in.readString();
        poster = in.readString();
        logo = in.readString();
        slug = in.readString();
    }

    public Show(){}
}

