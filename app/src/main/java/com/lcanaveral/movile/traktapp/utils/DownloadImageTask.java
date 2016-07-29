package com.lcanaveral.movile.traktapp.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by lcanaveral on 7/29/16.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmpImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmpImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmpImage.setImageBitmap(result);
    }

}