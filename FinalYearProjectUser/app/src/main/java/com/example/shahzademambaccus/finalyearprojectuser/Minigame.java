package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class Minigame extends AppCompatActivity {

    private ImageView question;
    private EditText answer;
    private TextView title;
    private ImageView logout;
    private ImageView backButton;
    private String username;
    private boolean isSign = true;
    private static final String TITLE_LABEL = "Mini game";
    private static final String USERNAME_LABEL = "Username";
    private DatabaseConnection database;
    private Minigame minigame;
    private String signURL;
    private String symbolURL;
    private String signOrSymbolWord;
    private int numberOfQuestionsAnswered;
    private int score;
    private static int MAX_QUESTION = 10;
    private static int SECONDS_TO_WAIT = 1000;
    private TextView displayScore;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame);
        setUpGUI();
        setToolBar();
        getExtras();
        database = new DatabaseConnection();
        minigame = this;
        numberOfQuestionsAnswered = 0;
        getSignOrSymbol();
        setupOnClick();
    }


    public void setupOnClick() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout logout = new Logout(minigame);
                startActivity(new Intent(minigame, Login.class));
                finishActivity();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(minigame, MainActivity.class);
                intent.putExtra(USERNAME_LABEL, username);
                startActivity(intent);
                finishActivity();
            }
        });
    }

    public void getExtras() {
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString(USERNAME_LABEL);
    }

    public void setToolBar() {
        title.setText(TITLE_LABEL);
        backButton.setImageResource(R.drawable.back);
    }

    public void setUpGUI() {
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
        logout = (ImageView) findViewById(R.id.Tool_Bar_Logout);
        question = (ImageView) findViewById(R.id.Minigame_Sign_Or_Symbol);
        answer = (EditText) findViewById(R.id.Minigame_Guess);
        displayScore = (TextView) findViewById(R.id.Minigame_Score);
        submitButton = (Button) findViewById(R.id.Minigame_Sumbit);
    }

    public void getSignOrSymbol(){
        if(numberOfQuestionsAnswered < MAX_QUESTION) {
            database.getMinigameSignsAndSymbols(this);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isSign) {
                        Picasso.with(minigame).load(signURL).placeholder(R.drawable.loadingwhite).into(question);
                    } else {
                        Glide.with(minigame).load(symbolURL).placeholder(R.drawable.loadingwhite).into(question);
                    }
                }
            }, SECONDS_TO_WAIT);
        }else{
            showResults();
        }

    }

    public void submitAnswer(View v){
        numberOfQuestionsAnswered++;
        clearScreen();
        if(answer.getText().toString().equalsIgnoreCase(getWord())){
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            score++;
        }else{
            Toast.makeText(this, "Incorrect, Word was: " + getWord().toString(), Toast.LENGTH_SHORT).show();
        }
        answer.setText("");
        isSign = !isSign;
        getSignOrSymbol();
    }

    public void showResults(){
        clearScreen();
        submitButton.setVisibility(View.GONE);
        answer.setVisibility(View.GONE);
        displayScore.setText("You got: " + score + "/" + MAX_QUESTION);
    }

    public void clearScreen(){
        question.setImageResource(0);
    }

    public void setSign(String url){
        signURL = url;
    }

    public void setSymbol(String url){
        symbolURL = url;
    }

    public void setWord(String word){
        signOrSymbolWord = word;
    }

    public String getWord(){
        return signOrSymbolWord;
    }

    public void finishActivity(){
        this.finish();
    }
}
