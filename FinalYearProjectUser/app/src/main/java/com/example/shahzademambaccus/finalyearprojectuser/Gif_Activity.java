package com.example.shahzademambaccus.finalyearprojectuser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Gif_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        Bundle bundle = new Bundle();
        bundle.get("Sign");
    }
}
