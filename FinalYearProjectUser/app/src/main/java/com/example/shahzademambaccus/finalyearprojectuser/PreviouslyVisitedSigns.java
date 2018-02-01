package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PreviouslyVisitedSigns extends AppCompatActivity {

    private TextView title;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previously_visited_signs);
        setToolBar();
    }

    public void setToolBar(){
       title = (TextView) findViewById(R.id.Tool_Bar_Text);
       backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
       title.setText("Previously Visited Signs");
       backButton.setImageResource(R.drawable.back);
       backButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(PreviouslyVisitedSigns.this, MainActivity.class));
           }
       });
    }
}
