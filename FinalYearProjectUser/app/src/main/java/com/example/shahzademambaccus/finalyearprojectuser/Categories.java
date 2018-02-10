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
    private static final String[] categories = new String[15];
    private TextView title;
    private ImageView backButton;
    private String username;
    private Categories category;
    private ImageView logout;
    private static final String TITLE_LABEL = "Category";
    private static final String SYMBOL_LABEL = "Symbol";
    private static final String CATEGORY_LABEL = "Category";
    private static final String CURRENT_TEXT_LABEL = "CurrentText";
    private static final String USERNAME_LABEL = "Username";

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

    public void familyButtonPressed(View v){
        createNewActivity(v,categories[10]);
    }

    public void peopleButtonPressed(View v){createNewActivity(v, categories[13]);}

    public void foodButtonPressed(View v){
        createNewActivity(v, categories[0]);
    }

    public void drinksButtonPressed(View v){createNewActivity(v, categories[14]);}

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
        intent.putExtra(SYMBOL_LABEL, isSymbolCategory);
        intent.putExtra(CATEGORY_LABEL, category);
        intent.putExtra(CURRENT_TEXT_LABEL, getCurrentText());
        intent.putExtra(USERNAME_LABEL, username);
        startActivity(intent);
    }

    public void setToolBar(){
        backButton.setImageResource(R.drawable.back);
        title.setText(TITLE_LABEL);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(category, MainActivity.class);
               intent.putExtra(USERNAME_LABEL, username);
               startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout logout = new Logout(category);
                startActivity(new Intent(category, Login.class));
            }
        });
    }

    public void setGUI(){
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
        translatedSignsAndSymbolsET = (EditText) findViewById(R.id.Category_TranslatedTxt);
        logout = (ImageView) findViewById(R.id.Tool_Bar_Logout);
    }

    public void getExtras() {
        Bundle bundle = getIntent().getExtras();
        isSymbolCategory = bundle.getBoolean(SYMBOL_LABEL);
        String currentText = bundle.getString(CURRENT_TEXT_LABEL);
        username = bundle.getString(USERNAME_LABEL);
        setCurrentText(currentText);
    }

    public void fillArray(){
        categories[0] = "food";
        categories[1] = "householdobjects";
        categories[2] = "questions";
        categories[3] = "verbs";
        categories[4] = "communications";
        categories[5] = "animals";
        categories[6] = "connectives";
        categories[7] = "outdoorobjects";
        categories[8] = "feelings";
        categories[9] = "size";
        categories[10] = "family";
        categories[11] = "rooms";
        categories[12] = "miscellaneous";
        categories[13] = "people";
        categories[14] = "drinks";
    }
}
