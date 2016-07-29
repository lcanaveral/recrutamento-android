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

    private static final String LOG_TAG = "DownloadImageTask";

    public DownloadImageTask(ImageView bmImage) {
        this.bmpImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap bitmap = null;
        Log.i(LOG_TAG, "doInBackground " + urldisplay);
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        Log.i(LOG_TAG, "onPostExecute");
        bmpImage.setImageBitmap(result);
    }

}