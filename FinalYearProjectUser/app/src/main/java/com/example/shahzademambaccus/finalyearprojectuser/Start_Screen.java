package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Start_Screen extends AppCompatActivity {

    private static final int MILLISECOND_TO_WAIT = 3000;
    private Start_Screen start_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__screen);
        start_screen = this;
        nextScreen();
    }

    public void nextScreen(){
        //wait a couple of seconds
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //go to login class
                startActivity(new Intent(start_screen, Login.class));
                finishActivity();
            }
        }, MILLISECOND_TO_WAIT);
    }
    //end this activity
    public void finishActivity(){
        this.finish();
    }
}
