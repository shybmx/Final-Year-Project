package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    private DatabaseConnection databaseConnection;
    private String signGIF;
    private String displayWord;
    private TextView symbolText;
    private TextView title;
    private ImageView logoImageView;
    private String username;
    private ImageView logout;
    private MainActivity mainActivity;
    private static final String USERNAME_LABEL = "Username";
    private static final String SYMBOL_LABEL = "Symbol";
    private static final String CURRENT_TEXT_LABEL = "CurrentText";
    private static final String SIGN_LABEL = "Sign";
    private static final String DISPLAY_WORD_LABEL = "DisplayWord";
    private static final String TITLE_LABEL = "CommSigns";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connection to database class
        databaseConnection = new DatabaseConnection();
        //this class stored in a variable
        mainActivity = this;
        setGUI();
        getExtras();
        setToolBar();
        getSignOfTheDay();
        setupOnClick();
    }
    //set up listeners for back and logout button
    public void setupOnClick() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout logout = new Logout(mainActivity);
                startActivity(new Intent(mainActivity, Login.class));
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Gif_Activity.class);
                intent.putExtra(SIGN_LABEL, signGIF);
                intent.putExtra(DISPLAY_WORD_LABEL, displayWord);
                startActivity(intent);
            }
        });
    }
    //get username
    public void getExtras() {
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString(USERNAME_LABEL);
    }
    //set up GUI
    public void setGUI() {
        symbolText = (TextView) findViewById(R.id.Main_SignOfTheDayWord);
        image = (ImageView) findViewById(R.id.Main_SignOfTheDayImg);
    }
    //start text to bsl
    public void textToBSLActivity(View v){
        Intent intent = new Intent(MainActivity.this, Words_Activity.class);
        intent.putExtra(USERNAME_LABEL, username);
        startActivity(intent);
        finishActivity();
    }
    //start bsl to text for symbols
    public void bslSymbolToText (View v){
        Intent intent = new Intent(MainActivity.this, Categories.class);
        intent.putExtra(SYMBOL_LABEL, true);
        intent.putExtra(CURRENT_TEXT_LABEL, "");
        intent.putExtra(USERNAME_LABEL, username);
        startActivity(intent);
        finishActivity();
    }
    //Start bsl to text for signs
    public void bslSignsToText(View v){
        Intent intent = new Intent(MainActivity.this, Categories.class);
        intent.putExtra(SYMBOL_LABEL, false);
        intent.putExtra(CURRENT_TEXT_LABEL, "");
        intent.putExtra(USERNAME_LABEL, username);
        startActivity(intent);
        finishActivity();
    }
    //start previously visited
    public void previouslyVisitedSigns(View v){
        Intent intent = new Intent(MainActivity.this, PreviouslyVisitedSigns.class);
        intent.putExtra(USERNAME_LABEL, username);
        startActivity(intent);
        finishActivity();
    }
    //start minigame
    public void minigame(View v){
        Intent intent = new Intent(MainActivity.this, Minigame.class);
        intent.putExtra(USERNAME_LABEL, username);
        startActivity(intent);
        finishActivity();
    }
    //get the sign of the day from database
    public void getSignOfTheDay(){
        databaseConnection.getSignOfTheDay(image, this, symbolText);
    }
    //get link for gif for sign of the day
    public void setSignGIF(String link){
        signGIF = link;
    }
    //get the label for sign of the day
    public void setWord(String word){
        displayWord = word;
    }
    //set up toolbar with items and images
    public void setToolBar(){
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        logoImageView = (ImageView) findViewById(R.id.Tool_Bar_Back);
        title.setText(TITLE_LABEL);
        logoImageView.setImageResource(R.drawable.logo);
        logout = (ImageView) findViewById(R.id.Tool_Bar_Logout);
    }
    //end this activity
    public void finishActivity(){
        this.finish();
    }
}