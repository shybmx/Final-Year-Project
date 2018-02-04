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

public class SignsAndSymbols extends AppCompatActivity{

    private String category = "";
    private String loadingWord = "";
    private ArrayList<String> listOfWords;
    private ArrayList<String> listOfLinks;
    private ImageDatabase imageDatabase;
    private GridView grid;
    private boolean isSymbolCategory;
    private EditText translatedText;
    private TextView title;
    private ImageView backButton;
    int millisecondsToLoad = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_and_symbols);

        Bundle bundle = getIntent().getExtras();
        isSymbolCategory = bundle.getBoolean("Symbol");
        category = bundle.getString("Category");
        String currentText = bundle.getString("CurrentText");

        grid = (GridView) findViewById(R.id.SignsAndSymbolsGrid);

        translatedText = (EditText) findViewById(R.id.TranslatedSignSymbolTxt);

        setToolbar();

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!translatedText.getText().equals("")) {
                    translatedText.getText().append(" " + listOfWords.get(position));
                    return;
                }
                translatedText.setText(listOfWords.get(position));
            }
        });

        listOfWords = new ArrayList<String>();
        listOfLinks = new ArrayList<String>();

        this.imageDatabase = new ImageDatabase();

        if(isSymbolCategory){
            loadingWord = "Symbols";
        }else{
            loadingWord = "Signs";
        }

        loadingToast("Gathering " + loadingWord);

        getAllSignsAndSymbols();
        setCurrentText(currentText);
    }

    public String getCurrentText(){
        String translatedSignsAndSymbolsTxt = translatedText.getText().toString();
        return translatedSignsAndSymbolsTxt;
    }

    public void setCurrentText(String currentText){
        translatedText.setText(currentText);
    }

    public void clearSignAndSymbolButtonPressed(View view){
        translatedText.setText("");
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

    public void getAllSignsAndSymbols(){
        imageDatabase.getSignsAndSymbols(category, this, isSymbolCategory);
        Handler handler = new Handler();
        loadingToast("Displaying " + loadingWord);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                placeSignsAndSymbolsOnScreen();
            }
        }, millisecondsToLoad);
    }

    public void placeSignsAndSymbolsOnScreen(){
        grid.setAdapter(new GridAdapter(listOfLinks, listOfWords,this, isSymbolCategory));
    }

    public void loadingToast(String word){
        Toast.makeText(this, "Loading: " + word, Toast.LENGTH_SHORT).show();
    }

    public void setToolbar(){
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        if(isSymbolCategory) {
            title.setText("Symbols");
        }else{
            title.setText("Signs");
        }
        backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
        backButton.setImageResource(R.drawable.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignsAndSymbols.this, Categories.class);
                intent.putExtra("Symbol", isSymbolCategory);
                intent.putExtra("CurrentText", getCurrentText());
                startActivity(intent);
            }
        });
    }
}
