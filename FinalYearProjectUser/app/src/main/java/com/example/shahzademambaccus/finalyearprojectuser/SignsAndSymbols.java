package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignsAndSymbols extends AppCompatActivity {

    boolean isSymbolCategory = false;
    String category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_and_symbols);
        Bundle bundle = getIntent().getExtras();
        isSymbolCategory = bundle.getBoolean("Symbol");
        category = bundle.getString("Category");
        String currentText = bundle.getString("CurrentText");
        //Toast.makeText(this, category, Toast.LENGTH_LONG).show();
        //getSignsAndSymbols();
        setCurrentText(currentText);
    }

    public void getSignsAndSymbols(){

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
}
