package com.example.shahzademambaccus.finalyearprojectuser;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImage extends AsyncTask<Void, Void, Bitmap>{

    private String imageURL;
    private ImageView image;
    private ImageView[] imageArray;

    public DownloadImage(String imageURL, ImageView image){
        this.imageURL = imageURL;
        this.image = image;
    }


    @Override
    protected Bitmap doInBackground(Void... params) {
        String url = imageURL;
        try{
            URLConnection connection = new URL(url).openConnection();
            connection.setConnectTimeout(1000 * 30);
            connection.setReadTimeout(1000 * 30);

            return BitmapFactory.decodeStream((InputStream) connection.getContent(), null, null);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        super.onPostExecute(bitmap);
        if(bitmap != null){
            image.setImageBitmap(bitmap);
        }
    }



}
