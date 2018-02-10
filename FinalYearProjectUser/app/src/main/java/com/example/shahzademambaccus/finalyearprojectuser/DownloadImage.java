package com.example.shahzademambaccus.finalyearprojectuser;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImage extends AsyncTask<Void, Void, Bitmap>{

    private String imageURL;
    private ImageView image;
    private String word;
    private TextView textView;
    private boolean cancelled = false;

    public DownloadImage(String imageURL, ImageView image, String word, TextView textView){
        this.imageURL = imageURL;
        this.image = image;
        this.word = word;
        this.textView = textView;
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
        image.setImageBitmap(null);
        if(bitmap != null){
             image.setImageBitmap(bitmap);
             textView.setText(word);
        }
    }

    public void isCancel(boolean isCanceled){
        cancelled = isCanceled;
    }

    public boolean checkIsCanceled(){
        return cancelled;
    }

}