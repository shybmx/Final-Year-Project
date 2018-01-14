package com.example.shahzademambaccus.finalyearprojectuser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Gif_Activity extends AppCompatActivity {

    private String gifLink = "";
    private String displayWord = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        Bundle bundle = getIntent().getExtras();
        gifLink = bundle.getString("Sign");
        displayWord = bundle.getString("DisplayWord");
        playVideoGIF();
        setLabelText(displayWord);
    }

    public void playVideoGIF(){
        ImageView video = (ImageView) findViewById(R.id.GIFPlayer);
        Glide.with(this).load(gifLink).asGif().into(video);
    }

    public void setLabelText(String wordToBeDisplayed){
        TextView textView = (TextView) findViewById(R.id.SelectedWord);
        textView.setText(wordToBeDisplayed);
    }
}
