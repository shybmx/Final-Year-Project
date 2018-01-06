package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SignsAndSymbols extends AppCompatActivity {

    boolean isSymbolCategory = false;
    String category = "";
    private ArrayList<String> listOfWords;
    private ArrayList<String> listOfLinks;
    private ImageDatabase imageDatabase;


    //private TableLayout table;
    private GridView grid;

    private static int NUMBER_OF_COLUMNS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_and_symbols);

        Bundle bundle = getIntent().getExtras();
        isSymbolCategory = bundle.getBoolean("Symbol");
        category = bundle.getString("Category");
        String currentText = bundle.getString("CurrentText");


        grid = (GridView) findViewById(R.id.SignsAndSymbolsGrid);

        //TableLayout table = (TableLayout) findViewById(R.id.tableForSignsAndSymbols);
        //this.table = table;

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
        imageDatabase.getSignsAndSymbols(category, this, numberOfSignsAndSymbols);
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
        grid.setAdapter(new GridAdapter(listOfLinks, this));
        /*for (int row = 0; row < listOfLinks.size() - 1; row++){
            //create table
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            table.addView(tableRow);
            for(int col = 0; col < NUMBER_OF_COLUMNS; col++){
                ImageView image = new ImageView(this);
                image.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText textField = (EditText) findViewById(R.id.TranslatedSignSymbolTxt);
                        //textField.setText(listOfWords.get(0));
                        textField.getText().append(" " + listOfWords.get(0));
                    }
                });
                tableRow.addView(image);
                new DownloadImage(listOfLinks.get(row), image).execute();
            }
        }*/
    }

    public void loadingToast(String number){
        Toast.makeText(this, "Loading" + number, Toast.LENGTH_SHORT).show();
    }

}
