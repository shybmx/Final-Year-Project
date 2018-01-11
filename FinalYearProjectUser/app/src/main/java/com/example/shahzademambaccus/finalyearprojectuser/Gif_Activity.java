package com.example.shahzademambaccus.finalyearprojectuser;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Gif_Activity extends AppCompatActivity {

    private String gifLink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        Bundle bundle = getIntent().getExtras();
        this.gifLink = bundle.getString("Sign");
        //Toast.makeText(this, "in GA: " + gifLink, Toast.LENGTH_SHORT).show();
        playVideoGIF();
    }

    public void playVideoGIF(){
        ImageView video = (ImageView) findViewById(R.id.GIFPlayer);
        Glide.with(this).load(gifLink).asGif().into(video);
    }
}
