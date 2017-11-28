package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void textToBSLActivity(View v){
        startActivity(new Intent(MainActivity.this, Words_Activity.class));
    }

    public void bslSymbolToText (View v){
        startActivity(new Intent(MainActivity.this, Categories.class));
    }

    public void bslSignsToText(View v){
        startActivity(new Intent(MainActivity.this, Categories.class));
    }

    public void previouslyVisitedSigns(View v){
        startActivity(new Intent(MainActivity.this, PreviouslyVisitedSigns.class));
    }
}