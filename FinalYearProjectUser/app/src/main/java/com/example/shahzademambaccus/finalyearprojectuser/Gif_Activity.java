package com.example.shahzademambaccus.finalyearprojectuser;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class Gif_Activity extends AppCompatActivity {

    String gifLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        Bundle bundle = new Bundle();
        String gifLink = bundle.getString("Sign");
        Toast.makeText(this, "in GA: " + gifLink, Toast.LENGTH_SHORT).show();
        playVideoGIF();
    }

    public void playVideoGIF(){
        ImageView video = (ImageView) findViewById(R.id.GIFPlayer);
        new DownloadImage(gifLink, video);
    }
}
