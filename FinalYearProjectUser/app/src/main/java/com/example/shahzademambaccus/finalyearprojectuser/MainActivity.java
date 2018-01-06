package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    private ImageDatabase imageDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.imageDatabase = new ImageDatabase();
        image = (ImageView) findViewById(R.id.SignOfTheDayImg);
        getSignOfTheDay();
    }

    public void textToBSLActivity(View v){
        startActivity(new Intent(MainActivity.this, Words_Activity.class));
    }

    public void bslSymbolToText (View v){
        Intent intent = new Intent(MainActivity.this, Categories.class);
        intent.putExtra("Symbol", true);
        intent.putExtra("CurrentText", "");
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
        imageDatabase.getSignOfTheDay(image, this);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Gif_Activity.class);
                intent.putExtra("Sign", "");
                startActivity(intent);
            }
        });
    }

}