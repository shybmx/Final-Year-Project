package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Categories extends AppCompatActivity {

    boolean isSymbolCategory = false;
    private static final String[] categories = new String[13];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Bundle bundle = getIntent().getExtras();
        isSymbolCategory = bundle.getBoolean("Symbol");
        String currentText = bundle.getString("CurrentText");
        setCurrentText(currentText);
        categories[0] = "foodanddrinks";
        categories[1] = "householdobjects";
        categories[2] = "questions";
        categories[3] = "verbs";
        categories[4] = "greetings";
        categories[5] = "demands";
        categories[6] = "animals";
        categories[7] = "connectives";
        categories[8] = "outdoorobjects";
        categories[9] = "feelings";
        categories[10] = "movement";
        categories[11] = "size";
        categories[12] = "familyandpeople";
    }

    public void familyAndPeopleButtonPressed(View v){
        createNewActivity(v,categories[12]);
    }

    public void foodAndDrinksButtonPressed(View v){
        createNewActivity(v, categories[0]);
    }

    public void householdObjectsButtonPressed(View v){
        createNewActivity(v, categories[1]);
    }

    public void questionButtonPressed(View v){
        createNewActivity(v, categories[2]);
    }

    public void verbsButtonPressed(View v){
        createNewActivity(v, categories[3]);
    }

    public void greetingsButtonPressed(View v){
        createNewActivity(v, categories[4]);
    }

    public void demandsButtonPressed(View v){
        createNewActivity(v, categories[5]);
    }

    public void animalsButtonPressed(View v){
        createNewActivity(v, categories[6]);
    }

    public void connectivesButtonPressed(View v){
        createNewActivity(v, categories[7]);
    }

    public void outdoorObjectsButtonPressed(View v){
        createNewActivity(v, categories[8]);
    }

    public void feelingsButtonPressed(View v){
        createNewActivity(v, categories[9]);
    }

    public void movementButtonPressed(View v){
        createNewActivity(v, categories[10]);
    }

    public void sizeAndPositionButtonPressed(View v){
        createNewActivity(v, categories[11]);
    }

    public String getCurrentText(){
        EditText translatedSignsAndSymbolsET = (EditText) findViewById(R.id.TranslatedTxt);
        String translatedSignsAndSymbolsTxt = translatedSignsAndSymbolsET.getText().toString();
        return translatedSignsAndSymbolsTxt;
    }

    public void setCurrentText(String currentText){
        EditText translatedSignsAndSymbolsET = (EditText) findViewById(R.id.TranslatedTxt);
        translatedSignsAndSymbolsET.setText(currentText);
    }

    public void createNewActivity(View v, String category){
        Intent intent = new Intent(Categories.this, SignsAndSymbols.class);
        intent.putExtra("Symbol", isSymbolCategory);
        intent.putExtra("Category", category);
        intent.putExtra("CurrentText", getCurrentText());
        startActivity(intent);
    }
}
