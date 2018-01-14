package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Words_Activity extends AppCompatActivity {
    private ImageDatabase imageDatabase;
    private GridView grid;
    private ArrayList<String> listOfImageLinks;
    private ArrayList<String> listOfGifLinks;
    private ArrayList<String> listOfWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_);
        grid = (GridView) findViewById(R.id.TranslatedSignsGrid);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Words_Activity.this, Gif_Activity.class);
                intent.putExtra("Sign", listOfGifLinks.get(position));
                intent.putExtra("DisplayWord", listOfWords.get(position));
                startActivity(intent);
            }
        });
        listOfImageLinks = new ArrayList<String>();
        listOfGifLinks = new ArrayList<String>();
        listOfWords = new ArrayList<String>();
        this.imageDatabase = new ImageDatabase();
    }

    public void search(View v) {
        clearScreen();
        clearLists(listOfImageLinks);
        clearLists(listOfGifLinks);
        clearLists(listOfWords);
        String[] tempArray = getSearchTerm().split(" ");
        for(int i = 0; i < tempArray.length; i++){
            imageDatabase.imageFromDatabase(tempArray[i], this);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayWords();
            }
        }, 1000);
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
        EditText searchTermET = (EditText) findViewById(R.id.SearchTerm);
        String searchTermTxt = searchTermET.getText().toString();
        if(searchTermTxt.isEmpty()){
            Toast.makeText(this, "Insert Text to be translated", Toast.LENGTH_LONG).show();
        }
        return searchTermTxt;
    }

    public void displayWords(){
        grid.setAdapter(new GridAdapter(listOfImageLinks, this, true));
    }

    public void makeToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
