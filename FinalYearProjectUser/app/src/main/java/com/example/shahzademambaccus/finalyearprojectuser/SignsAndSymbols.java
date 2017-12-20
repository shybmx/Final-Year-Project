package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class SignsAndSymbols extends AppCompatActivity {

    boolean isSymbolCategory = false;
    String category = "";
    private String[] listOfWords;
    private ImageDatabase imageDatabase;
    private TableLayout table;
    private static int NUMBER_OF_COLUMNS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_and_symbols);

        Bundle bundle = getIntent().getExtras();
        isSymbolCategory = bundle.getBoolean("Symbol");
        category = bundle.getString("Category");
        String currentText = bundle.getString("CurrentText");

        TableLayout table = (TableLayout) findViewById(R.id.tableForSignsAndSymbols);
        this.table = table;

        this.imageDatabase = new ImageDatabase();
        imageDatabase.signsAndSymbolsCounter(category, this);
        Handler handler = new Handler();
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

    public String[] getListOfWords(){
        return listOfWords;
    }


    public void getAllSignsAndSymbols(int numberOfSignsAndSymbols){
        imageDatabase.getSignsAndSymbols(category, this, numberOfSignsAndSymbols);
        //Toast.makeText(this, "Number of Symbols: " + numberOfSignsAndSymbols + " ", Toast.LENGTH_LONG).show();
//        for (int row = 0; row < counter; row++){
//            //create table
//            TableRow tableRow = new TableRow(this);
//            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
//            table.addView(tableRow);
//            for(int col = 0; col < NUMBER_OF_COLUMNS; col++){
//                ImageView image = new ImageView(this);
//                image.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
//                tableRow.addView(image);
//                //imageDatabase.imageFromDatabase(getwordsfromStringarray, image, this);
//            }
//        }
    }

}
