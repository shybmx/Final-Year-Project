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
        //connection to database
        database = new DatabaseConnection();
        //this class stored in a variable
        minigame = this;
        numberOfQuestionsAnswered = 0;
        getSignOrSymbol();
        setupOnClick();
    }

    //set up listeners for back button and logout
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
    //get username
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
    //get sign or symbol from database
    public void getSignOrSymbol(){
        //checks if all questions have been answered
        if(numberOfQuestionsAnswered < MAX_QUESTION) {
            //send request to database
            database.getMinigameSignsAndSymbols(this);
            //wait a second for request to be sent
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isSign) {
                        //display symbol
                        Picasso.with(minigame).load(signURL).placeholder(R.drawable.loadingwhite).into(question);
                    } else {
                        //display sign
                        Glide.with(minigame).load(symbolURL).placeholder(R.drawable.loadingwhite).into(question);
                    }
                }
            }, SECONDS_TO_WAIT);
        }else{
            showResults();
        }

    }

    public void submitAnswer(View v){
        //increase the number of questions answered
        numberOfQuestionsAnswered++;
        clearScreen();
        //check if answer is correct
        if(answer.getText().toString().equalsIgnoreCase(getWord())){
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            //increment score
            score++;
        }else{
            Toast.makeText(this, "Incorrect, Word was: " + getWord().toString(), Toast.LENGTH_SHORT).show();
        }
        //clear text bar
        answer.setText("");
        //change question type
        isSign = !isSign;
        getSignOrSymbol();
    }

    public void showResults(){
        clearScreen();
        submitButton.setVisibility(View.GONE);
        answer.setVisibility(View.GONE);
        displayScore.setText("You got: " + score + "/" + MAX_QUESTION);
    }
    //clear the image view
    public void clearScreen(){
        question.setImageResource(0);
    }
    //set the url for sign
    public void setSign(String url){
        signURL = url;
    }
    //set url for symbol
    public void setSymbol(String url){
        symbolURL = url;
    }
    //set word for answer
    public void setWord(String word){
        signOrSymbolWord = word;
    }
    //get the word for answer
    public String getWord(){
        return signOrSymbolWord;
    }
    //end acitivty
    public void finishActivity(){
        this.finish();
    }
}
