package com.example.shahzademambaccus.finalyearprojectuser;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PreviouslyVisitedSigns extends AppCompatActivity {

    private TextView title;
    private ImageView backButton;
    private PreviouslyVisitedSigns previouslyVisitedSigns;
    private ArrayList<String> listOfWords;
    private ArrayList<String> listOfLinks;
    private ArrayList<String> listOfGifs;
    private GridView grid;
    private ImageDatabase database;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previously_visited_signs);
        setGUI();
        setToolBar();
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("Username");
        previouslyVisitedSigns = this;
        listOfWords = new ArrayList<String>();
        listOfLinks = new ArrayList<String>();
        listOfGifs = new ArrayList<String>();
        database = new ImageDatabase();
        getAllSigns();
    }

    public void setToolBar(){
       title.setText("Previously Visited Signs");
       backButton.setImageResource(R.drawable.back);
       backButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               previouslyVisitedSigns.finish();
           }
       });
    }

    private void setGUI() {
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
        grid = (GridView) findViewById(R.id.Previously_Visited_Grid);
    }

    public ArrayList<String> getListOfWords(){
        return listOfWords;
    }

    public ArrayList<String> getListOfLinks(){
        return listOfLinks;
    }

    public ArrayList<String> getListOfGifs(){
        return listOfGifs;
    }

    public void getAllSigns(){
        database.getPrevisoulyVisited(username, this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayAllSigns();
            }
        }, 1000);
    }

    public void displayAllSigns(){
        grid.setAdapter(new GridAdapter(listOfLinks, listOfWords, this, true));
    }


}
