package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Categories extends AppCompatActivity {

    boolean isSymbolCategory = false;
    private EditText translatedSignsAndSymbolsET;
    private static final String[] categories = new String[13];
    private TextView title;
    private ImageView backButton;
    private String username;
    private Categories category;
    private ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        category = this;
        setGUI();
        getExtras();
        setToolBar();
        fillArray();
    }

    public void familyAndPeopleButtonPressed(View v){
        createNewActivity(v,categories[10]);
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

    public void communicationButtonPressed(View v){
        createNewActivity(v, categories[4]);
    }

    public void animalsButtonPressed(View v){
        createNewActivity(v, categories[5]);
    }

    public void connectivesButtonPressed(View v){
        createNewActivity(v, categories[6]);
    }

    public void outdoorObjectsButtonPressed(View v){
        createNewActivity(v, categories[7]);
    }

    public void feelingsButtonPressed(View v){
        createNewActivity(v, categories[8]);
    }

    public void sizeAndPositionButtonPressed(View v){
        createNewActivity(v, categories[9]);
    }

    public void roomButtonPressed(View v){
        createNewActivity(v, categories[11]);
    }

    public void miscellaneousButtonPressed(View v){
        createNewActivity(v, categories[12]);
    }

    public String getCurrentText(){
        String translatedSignsAndSymbolsTxt = translatedSignsAndSymbolsET.getText().toString();
        return translatedSignsAndSymbolsTxt;
    }

    public void setCurrentText(String currentText){
        translatedSignsAndSymbolsET.setText(currentText);
    }

    public void clearButtonPressed(View v){
        if(!translatedSignsAndSymbolsET.getText().equals("")){
            translatedSignsAndSymbolsET.getText().clear();
        }
    }

    public void createNewActivity(View v, String category){
        Intent intent = new Intent(Categories.this, SignsAndSymbols.class);
        intent.putExtra("Symbol", isSymbolCategory);
        intent.putExtra("Category", category);
        intent.putExtra("CurrentText", getCurrentText());
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    public void setToolBar(){
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
        backButton.setImageResource(R.drawable.back);
        title.setText("Catgegories");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               category.finish();
            }
        });
        logout = (ImageView) findViewById(R.id.Tool_Bar_Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout logout = new Logout(category);
                startActivity(new Intent(category, Login.class));
            }
        });
    }

    public void setGUI(){
        translatedSignsAndSymbolsET = (EditText) findViewById(R.id.TranslatedTxt);
    }

    public void getExtras() {
        Bundle bundle = getIntent().getExtras();
        isSymbolCategory = bundle.getBoolean("Symbol");
        String currentText = bundle.getString("CurrentText");
        username = bundle.getString("Username");
        setCurrentText(currentText);
    }

    public void fillArray(){
        categories[0] = "foodanddrinks";
        categories[1] = "householdobjects";
        categories[2] = "questions";
        categories[3] = "verbs";
        categories[4] = "communications";
        categories[5] = "animals";
        categories[6] = "connectives";
        categories[7] = "outdoorobjects";
        categories[8] = "feelings";
        categories[9] = "size";
        categories[10] = "familyandpeople";
        categories[11] = "rooms";
        categories[12] = "miscellaneous";
    }
}
