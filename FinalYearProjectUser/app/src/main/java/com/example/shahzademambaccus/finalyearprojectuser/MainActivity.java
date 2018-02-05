package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    private ImageDatabase imageDatabase;
    private String signGIF;
    private String displayWord;
    private TextView symbolText;
    private TextView title;
    private ImageView logoImageView;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        symbolText = (TextView) findViewById(R.id.SignOfTheDayWord);
        this.imageDatabase = new ImageDatabase();
        image = (ImageView) findViewById(R.id.SignOfTheDayImg);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("Username");
        setTitle();
        getSignOfTheDay();
    }

    public void textToBSLActivity(View v){
        //startActivity(new Intent(MainActivity.this, Words_Activity.class));
        Intent intent = new Intent(MainActivity.this, Words_Activity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void bslSymbolToText (View v){
        Intent intent = new Intent(MainActivity.this, Categories.class);
        intent.putExtra("Symbol", true);
        intent.putExtra("CurrentText", "");
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void bslSignsToText(View v){
        Intent intent = new Intent(MainActivity.this, Categories.class);
        intent.putExtra("Symbol", false);
        intent.putExtra("CurrentText", "");
        startActivity(intent);
    }

    public void previouslyVisitedSigns(View v){
        startActivity(new Intent(MainActivity.this, PreviouslyVisitedSigns.class));
    }


    public void getSignOfTheDay(){
        imageDatabase.getSignOfTheDay(image, this, symbolText);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Gif_Activity.class);
                intent.putExtra("Sign", signGIF);
                intent.putExtra("DisplayWord", displayWord);
                startActivity(intent);
            }
        });
    }

    public void setSignGIF(String link){
        signGIF = link;
    }

    public void setWord(String word){
        displayWord = word;
    }

    public void setTitle(){
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        logoImageView = (ImageView) findViewById(R.id.Tool_Bar_Back);
        title.setText("CommSigns");
        logoImageView.setImageResource(R.drawable.logo);
    }

}