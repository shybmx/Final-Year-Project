package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
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
    private DatabaseConnection database;
    private String username;
    private ImageView logout;
    private static final String USERNAME_LABEL = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previously_visited_signs);
        //this class as a variable
        previouslyVisitedSigns = this;
        //connection to database
        database = new DatabaseConnection();
        setGUI();
        setToolBar();
        getExtras();
        setArrayLists();
        getAllSigns();
    }
    //list of arrays to store links and words
    public void setArrayLists() {
        listOfWords = new ArrayList<String>();
        listOfLinks = new ArrayList<String>();
        listOfGifs = new ArrayList<String>();
    }

    public void getExtras() {
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("Username");
    }

    public void setToolBar(){
       title.setText("Previously Visited Signs");
       backButton.setImageResource(R.drawable.back);
       backButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            Intent intent = new Intent(previouslyVisitedSigns, MainActivity.class);
            intent.putExtra(USERNAME_LABEL, username);
            startActivity(intent);
            finishActivity();
           }
       });
        logout = (ImageView) findViewById(R.id.Tool_Bar_Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout logout = new Logout(previouslyVisitedSigns);
                startActivity(new Intent(previouslyVisitedSigns, Login.class));
            }
        });
    }

    private void setGUI() {
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
        grid = (GridView) findViewById(R.id.Previously_Visited_Grid);
    }
    //populate arrays with values from database
    public ArrayList<String> getListOfWords(){
        return listOfWords;
    }

    public ArrayList<String> getListOfLinks(){
        return listOfLinks;
    }

    public ArrayList<String> getListOfGifs(){
        return listOfGifs;
    }
    //get all signs to display based off username
    public void getAllSigns(){
        database.getPrevisoulyVisited(username, this);
        //wait a second for the request to be sent
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayAllSigns();
            }
        }, 1000);
    }
    //display all items on screen
    public void displayAllSigns(){
        grid.setAdapter(new GridAdapter(listOfLinks, listOfWords, this, true));
    }

    public void finishActivity(){
        this.finish();
    }
}