package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    }

    public void getExtras() {
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("Username");
    }

    public void setGUI() {
        symbolText = (TextView) findViewById(R.id.SignOfTheDayWord);
        image = (ImageView) findViewById(R.id.SignOfTheDayImg);
    }

    public void textToBSLActivity(View v){
        Intent intent = new Intent(MainActivity.this, Words_Activity.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void bslSymbolToText (View v){
        Intent intent = new Intent(MainActivity.this, Categories.class);
        intent.putExtra("Symbol", true);
        intent.putExtra("CurrentText", "");
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void bslSignsToText(View v){
        Intent intent = new Intent(MainActivity.this, Categories.class);
        intent.putExtra("Symbol", false);
        intent.putExtra("CurrentText", "");
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void previouslyVisitedSigns(View v){
        Intent intent = new Intent(MainActivity.this, PreviouslyVisitedSigns.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }


    public void getSignOfTheDay(){
        databaseConnection.getSignOfTheDay(image, this, symbolText);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Gif_Activity.class);
                intent.putExtra("Sign", signGIF);
                intent.putExtra("DisplayWord", displayWord);
                startActivity(intent);
            }
        });
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
        title.setText("CommSigns");
        logoImageView.setImageResource(R.drawable.logo);
        logout = (ImageView) findViewById(R.id.Tool_Bar_Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout logout = new Logout(mainActivity);
                startActivity(new Intent(mainActivity, Login.class));
            }
        });
    }

}