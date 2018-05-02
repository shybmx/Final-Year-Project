package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Gif_Activity extends AppCompatActivity {

    private String gifLink = "";
    private String displayWord = "";
    private ImageView backButton;
    private Gif_Activity gif_activity;
    private ImageView logout;
    private static final String SIGN_LABEL = "Sign";
    private static final String DISPLAY_WORD_LABEL = "DisplayWord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //display items on screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        getExtras();
        setToolBar();
        playVideoGIF();
        setupOnClick();
        setLabelText(displayWord);
        //give this class a variable
        gif_activity = this;
    }
    //set up listener for when the logout and back button is pressed
    public void setupOnClick() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout logout = new Logout(gif_activity);
                startActivity(new Intent(gif_activity, Login.class));
                finishActivity();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
    }
    //get extra values such as the sign and word to display
    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        gifLink = bundle.getString(SIGN_LABEL);
        displayWord = bundle.getString(DISPLAY_WORD_LABEL);
    }
    //set up toolbar
    private void setToolBar() {
        backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
        logout = (ImageView) findViewById(R.id.Tool_Bar_Logout);
    }
    //play the selected gif based off item selected
    public void playVideoGIF(){
        ImageView video = (ImageView) findViewById(R.id.GIF_GIFPlayer);
        Glide.with(this).load(gifLink).asGif().placeholder(R.drawable.loadingblack).error(R.drawable.error).into(video);
    }
    //set the label under
    public void setLabelText(String wordToBeDisplayed){
        TextView textView = (TextView) findViewById(R.id.GIF_SelectedWord);
        textView.setText(wordToBeDisplayed);
        backButton.setImageResource(R.drawable.back);
    }
    //close this activity
    public void finishActivity(){
        this.finish();
    }
}
