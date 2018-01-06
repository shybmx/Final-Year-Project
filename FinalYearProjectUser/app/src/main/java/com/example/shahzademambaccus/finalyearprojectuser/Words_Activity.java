package com.example.shahzademambaccus.finalyearprojectuser;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class Words_Activity extends AppCompatActivity {
    private ImageDatabase imageDatabase;
    private GridView grid;
    private ArrayList<String> listOfLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_);
        grid = (GridView) findViewById(R.id.TranslatedSignsGrid);
        listOfLinks = new ArrayList<String>();
        this.imageDatabase = new ImageDatabase();
    }

    public void search(View v) {
        clearScreen();
        clearLists(listOfLinks);
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

    public ArrayList<String> getListOfLinks(){
        return listOfLinks;
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
        grid.setAdapter(new GridAdapter(listOfLinks, this));
    }

}
