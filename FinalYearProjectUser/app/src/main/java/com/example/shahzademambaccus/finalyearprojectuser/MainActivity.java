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
        databaseConnection = new DatabaseConnection();
        mainActivity = this;
        setGUI();
        getExtras();
        setToolBar();
        getSignOfTheDay();
        setupOnClick();
    }

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

    public void getExtras() {
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString(USERNAME_LABEL);
    }

    public void setGUI() {
        symbolText = (TextView) findViewById(R.id.Main_SignOfTheDayWord);
        image = (ImageView) findViewById(R.id.Main_SignOfTheDayImg);
    }

    public void textToBSLActivity(View v){
        Intent intent = new Intent(MainActivity.this, Words_Activity.class);
        intent.putExtra(USERNAME_LABEL, username);
        startActivity(intent);
        finishActivity();
    }

    public void bslSymbolToText (View v){
        Intent intent = new Intent(MainActivity.this, Categories.class);
        intent.putExtra(SYMBOL_LABEL, true);
        intent.putExtra(CURRENT_TEXT_LABEL, "");
        intent.putExtra(USERNAME_LABEL, username);
        startActivity(intent);
        finishActivity();
    }

    public void bslSignsToText(View v){
        Intent intent = new Intent(MainActivity.this, Categories.class);
        intent.putExtra(SYMBOL_LABEL, false);
        intent.putExtra(CURRENT_TEXT_LABEL, "");
        intent.putExtra(USERNAME_LABEL, username);
        startActivity(intent);
        finishActivity();
    }

    public void previouslyVisitedSigns(View v){
        Intent intent = new Intent(MainActivity.this, PreviouslyVisitedSigns.class);
        intent.putExtra(USERNAME_LABEL, username);
        startActivity(intent);
        finishActivity();
    }

    public void minigame(View v){
        Intent intent = new Intent(MainActivity.this, Minigame.class);
        intent.putExtra(USERNAME_LABEL, username);
        startActivity(intent);
        finishActivity();
    }

    public void getSignOfTheDay(){
        databaseConnection.getSignOfTheDay(image, this, symbolText);
    }

    public void setSignGIF(String link){
        signGIF = link;
    }

    public void setWord(String word){
        displayWord = word;
    }

    public void setToolBar(){
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        logoImageView = (ImageView) findViewById(R.id.Tool_Bar_Back);
        title.setText(TITLE_LABEL);
        logoImageView.setImageResource(R.drawable.logo);
        logout = (ImageView) findViewById(R.id.Tool_Bar_Logout);
    }

    public void finishActivity(){
        this.finish();
    }
}