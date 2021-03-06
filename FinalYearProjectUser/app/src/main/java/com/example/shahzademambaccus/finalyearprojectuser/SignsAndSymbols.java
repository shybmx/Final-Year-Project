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
    private DatabaseConnection databaseConnection;
    private GridView grid;
    private boolean isSymbolCategory;
    private EditText translatedText;
    private TextView title;
    private ImageView backButton;
    int millisecondsToLoad = 1000;
    private String username;
    private SignsAndSymbols signsAndSymbols;
    private ImageView logout;
    private static final String SYMBOL_LABEL = "Symbol";
    private static final String CATEGORY_LABEL = "Category";
    private static final String CURRENT_TEXT_LABEL = "CurrentText";
    private static final String USERNAME_LABEL = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_and_symbols);
        signsAndSymbols = this;
        setGUI();
        setToolbar();
        setArrayLits();
        //database connection
        databaseConnection = new DatabaseConnection();
        getExtras();
        //get text to place in title
        if(isSymbolCategory){
            loadingWord = "Symbols";
        }else{
            loadingWord = "Signs";
        }
        title.setText(loadingWord);
        clearLists(listOfLinks);
        clearLists(listOfWords);
        loadingToast("Gathering " + loadingWord);
        setupOnClick();
        getAllSignsAndSymbols();
    }
    //set up listeners for back, logout and grid
    public void setupOnClick() {
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //when item is pressed, a request is sent to store word with username
                databaseConnection.addToPreviouslyVisited(username, listOfWords.get(position).toString(), signsAndSymbols);
                if(!translatedText.getText().equals("")) {
                    //place word within text box
                    translatedText.getText().append(" " + listOfWords.get(position));
                    return;
                }
                translatedText.setText(listOfWords.get(position));
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                Intent intent = new Intent(SignsAndSymbols.this, Categories.class);
                intent.putExtra(SYMBOL_LABEL, isSymbolCategory);
                intent.putExtra(USERNAME_LABEL, username);
                intent.putExtra(CURRENT_TEXT_LABEL, getCurrentText());
                startActivity(intent);
                finishActivity();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout logout = new Logout(signsAndSymbols);
                startActivity(new Intent(signsAndSymbols, Login.class));
                finishActivity();
            }
        });
    }
    //array of words and links for urls
    public void setArrayLits() {
        listOfWords = new ArrayList<String>();
        listOfLinks = new ArrayList<String>();
    }

    public void setGUI() {
        logout = (ImageView) findViewById(R.id.Tool_Bar_Logout);
        backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        grid = (GridView) findViewById(R.id.Signs_And_Symbols_Grid);
        translatedText = (EditText) findViewById(R.id.Signs_And_Symbols_TranslatedTxt);
    }

    public void getExtras() {
        Bundle bundle = getIntent().getExtras();
        isSymbolCategory = bundle.getBoolean(SYMBOL_LABEL);
        category = bundle.getString(CATEGORY_LABEL);
        username = bundle.getString(USERNAME_LABEL);
        setCurrentText(bundle.getString(CURRENT_TEXT_LABEL));
    }
    //get the current text in text box
    public String getCurrentText(){
        return translatedText.getText().toString();
    }
    //set textbox with text from previous activity
    public void setCurrentText(String currentText){
        translatedText.setText(currentText);
    }
    //clear the text box
    public void clearSignAndSymbolButtonPressed(View view){
        translatedText.setText("");
    }
    //array for list of words
    public ArrayList<String> getListOfWords(){
        return listOfWords;
    }
    //clear all arrays
    public void clearLists(ArrayList<String> list){
        if(list == null){
            return;
        }else{
            list.clear();
        }
    }
    //array of links
    public ArrayList<String> getListOfLinks(){
        return listOfLinks;
    }
    //gets all signs and symbols based of category selected
    public void getAllSignsAndSymbols(){
        databaseConnection.getSignsAndSymbols(category, this, isSymbolCategory);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                placeSignsAndSymbolsOnScreen();
            }
        }, millisecondsToLoad);
    }
    //displays signs or symbols based of categories
    public void placeSignsAndSymbolsOnScreen(){
        loadingToast("Displaying " + loadingWord);
        grid.setAdapter(new GridAdapter(listOfLinks, listOfWords,this, isSymbolCategory));
    }
    //loading text
    public void loadingToast(String word){
        Toast.makeText(this, "Loading: " + word, Toast.LENGTH_SHORT).show();
    }
    //set toolbar
    public void setToolbar(){
        backButton.setImageResource(R.drawable.back);
    }
    //clear grid view
    public void clearScreen(){
        grid.setAdapter(null);
    }
    //end activity
    public void finishActivity(){
        this.finish();
    }
}
