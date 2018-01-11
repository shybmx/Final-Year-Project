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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SignsAndSymbols extends AppCompatActivity{

    private String category = "";
    private ArrayList<String> listOfWords;
    private ArrayList<String> listOfLinks;
    private ImageDatabase imageDatabase;
    private GridView grid;
    private boolean isSymbolCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_and_symbols);

        Bundle bundle = getIntent().getExtras();
        isSymbolCategory = bundle.getBoolean("Symbol");
        category = bundle.getString("Category");
        String currentText = bundle.getString("CurrentText");

        grid = (GridView) findViewById(R.id.SignsAndSymbolsGrid);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //EditText textField = (EditText) findViewById(R.id.TranslatedSignSymbolTxt);
                //textField.getText().append(" " + listOfWords.get(position));
            }
        });

        listOfWords = new ArrayList<String>();
        listOfLinks = new ArrayList<String>();

        this.imageDatabase = new ImageDatabase();
        imageDatabase.signsAndSymbolsCounter(category, this);
        Handler handler = new Handler();
        loadingToast(".");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               getAllSignsAndSymbols(imageDatabase.getSignsAndSymbolsCount());
            }
        },1000 );
        setCurrentText(currentText);
    }

    public void backButtonPressed(View v){
        Intent intent = new Intent(SignsAndSymbols.this, Categories.class);
        intent.putExtra("Symbol", isSymbolCategory);
        intent.putExtra("CurrentText", getCurrentText());
        startActivity(intent);
    }

    public String getCurrentText(){
        EditText translatedSignsAndSymbolsET = (EditText) findViewById(R.id.TranslatedSignSymbolTxt);
        String translatedSignsAndSymbolsTxt = translatedSignsAndSymbolsET.getText().toString();
        return translatedSignsAndSymbolsTxt;
    }

    public void setCurrentText(String currentText){
        EditText translatedSignsAndSymbolsET = (EditText) findViewById(R.id.TranslatedSignSymbolTxt);
        translatedSignsAndSymbolsET.setText(currentText);
    }

    public void clearSignAndSymbolButtonPressed(View view){
        EditText translatedSignsAndSymbolsET = (EditText) findViewById(R.id.TranslatedSignSymbolTxt);
        translatedSignsAndSymbolsET.setText("");
    }

    public ArrayList<String> getListOfWords(){
        return listOfWords;
    }

    public void clearLists(ArrayList<String> list){
        if(list == null){
            return;
        }else{
            list.clear();
        }
    }

    public ArrayList<String> getListOfLinks(){
        return listOfLinks;
    }

    public void getAllSignsAndSymbols(int numberOfSignsAndSymbols){
        imageDatabase.getSignsAndSymbols(category, this, numberOfSignsAndSymbols, isSymbolCategory);
        Handler handler = new Handler();
        loadingToast("..");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                placeSignsAndSymbolsOnScreen();
            }
        }, 1000);
    }

    public void placeSignsAndSymbolsOnScreen(){
        grid.setAdapter(new GridAdapter(listOfLinks, this, isSymbolCategory));
    }

    public void loadingToast(String number){
        Toast.makeText(this, "Loading" + number, Toast.LENGTH_SHORT).show();
    }
}
