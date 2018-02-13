package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Words_Activity extends AppCompatActivity {
    private DatabaseConnection databaseConnection;
    private GridView grid;
    private ArrayList<String> listOfImageLinks;
    private ArrayList<String> listOfGifLinks;
    private ArrayList<String> listOfWords;
    private int timeDelay = 1000;
    private TextView title;
    private ImageView backButton;
    private EditText searchTermET;
    private String username;
    private Words_Activity words_activity;
    private ImageView logoutButton;
    private static final String USERNAME_LABEL = "Username";
    private static final String SIGN_LABEL = "Sign";
    private static final String DISPLAY_WORD_LABEL = "DisplayWord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_);
        this.databaseConnection = new DatabaseConnection();
        words_activity = this;
        setGUI();
        setToolBar();
        getExtras();
        setArrayLists();
        setupOnClick();
    }

    public void setupOnClick() {
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Words_Activity.this, Gif_Activity.class);
                intent.putExtra(SIGN_LABEL, listOfGifLinks.get(position));
                intent.putExtra(DISPLAY_WORD_LABEL, listOfWords.get(position));
                databaseConnection.addToPreviouslyVisited(username, listOfWords.get(position), words_activity);
                startActivity(intent);
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout logout = new Logout(words_activity);
                startActivity(new Intent(words_activity, Login.class));
            }
        });
    }

    public void setArrayLists() {
        listOfImageLinks = new ArrayList<String>();
        listOfGifLinks = new ArrayList<String>();
        listOfWords = new ArrayList<String>();
    }

    public void getExtras() {
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString(USERNAME_LABEL);
    }

    public void setGUI() {
        grid = (GridView) findViewById(R.id.Words_TranslatedSignsGrid);
        searchTermET = (EditText) findViewById(R.id.Words_SearchTerm);
    }

    public void search(View v) {
        clearScreen();
        clearLists(listOfImageLinks);
        clearLists(listOfGifLinks);
        clearLists(listOfWords);
        Handler handler = new Handler();
        databaseConnection.imageFromDatabase(getSearchTerm(), this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayWords();
            }
        }, timeDelay);
    }

    public ArrayList<String> getListOfImageLinks(){
        return listOfImageLinks;
    }

    public ArrayList<String> getListOfGifLinks(){
        return listOfGifLinks;
    }

    public ArrayList<String> getListOfWords(){
        return listOfWords;
    }

    public void clearScreen(){
        grid.setAdapter(null);
    }

    public void clearLists(ArrayList<String> list){
        if(list == null){
            return;
        }else{
            list.clear();
        }
    }

    public String getSearchTerm() {
        String searchTermTxt = searchTermET.getText().toString();
        if(searchTermTxt.isEmpty()){
            Toast.makeText(this, "Insert Text to be translated", Toast.LENGTH_LONG).show();
        }
        return searchTermTxt;
    }

    public void displayWords(){
        grid.setAdapter(new GridAdapter(listOfImageLinks, listOfWords,this, true));
    }

    public void makeToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void setToolBar(){
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        title.setText("BSL Symbols");
        backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
        backButton.setImageResource(R.drawable.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                words_activity.finish();
            }
        });
        logoutButton = (ImageView) findViewById(R.id.Tool_Bar_Logout);
    }

}
