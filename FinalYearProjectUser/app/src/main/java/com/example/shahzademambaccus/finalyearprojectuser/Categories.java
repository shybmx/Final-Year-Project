package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Categories extends AppCompatActivity {
    //All global variables used
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
        //creates screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        //set this class into a variable
        category = this;
        //
        setGUI();
        getExtras();
        setToolBar();
        fillArray();
        setupOnClick();
    }
    //When the family button is pressed
    public void familyButtonPressed(View v){
        createNewActivity(v,categories[10]);
    }
    //When people button is pressed
    public void peopleButtonPressed(View v){

        createNewActivity(v, categories[13]);
    }
    //When food button is pressed
    public void foodButtonPressed(View v){

        createNewActivity(v, categories[0]);
    }
    //When drinks button is pressed
    public void drinksButtonPressed(View v){

        createNewActivity(v, categories[14]);
    }
    //When household objects button is pressed
    public void householdObjectsButtonPressed(View v){

        createNewActivity(v, categories[1]);
    }
    //When questions button is pressed
    public void questionButtonPressed(View v){
        createNewActivity(v, categories[2]);
    }
    //When verbs is pressed
    public void verbsButtonPressed(View v){
        createNewActivity(v, categories[3]);
    }
    //When communication is pressed
    public void communicationButtonPressed(View v){
        createNewActivity(v, categories[4]);
    }
    //When animal is pressed
    public void animalsButtonPressed(View v){
        createNewActivity(v, categories[5]);
    }
    //When connectives is pressed
    public void connectivesButtonPressed(View v){
        createNewActivity(v, categories[6]);
    }
    //When outdoors is pressed
    public void outdoorObjectsButtonPressed(View v){
        createNewActivity(v, categories[7]);
    }
    //When feelings is pressed
    public void feelingsButtonPressed(View v){
        createNewActivity(v, categories[8]);
    }
    //When size and positions is pressed
    public void sizeAndPositionButtonPressed(View v){
        createNewActivity(v, categories[9]);
    }
    //When room is pressed
    public void roomButtonPressed(View v){
        createNewActivity(v, categories[11]);
    }
    //When miscellaneous is pressed
    public void miscellaneousButtonPressed(View v){
        createNewActivity(v, categories[12]);
    }

    //Gets current text passed in through other activities
    public String getCurrentText(){
        String translatedSignsAndSymbolsTxt = translatedSignsAndSymbolsET.getText().toString();
        return translatedSignsAndSymbolsTxt;
    }
    //Places the text from other activites into the text bar
    public void setCurrentText(String currentText){
        translatedSignsAndSymbolsET.setText(currentText);
    }
    //clears text bar when button is pressed
    public void clearButtonPressed(View v){
        if(!translatedSignsAndSymbolsET.getText().equals("")){
            translatedSignsAndSymbolsET.getText().clear();
        }
    }
    //goes to the next activity passing the category values, username and if it is for signs or symbols
    public void createNewActivity(View v, String category){
        Intent intent = new Intent(Categories.this, SignsAndSymbols.class);
        intent.putExtra(SYMBOL_LABEL, isSymbolCategory);
        intent.putExtra(CATEGORY_LABEL, category);
        intent.putExtra(CURRENT_TEXT_LABEL, getCurrentText());
        intent.putExtra(USERNAME_LABEL, username);
        startActivity(intent);
    }
    //sets up the listener for back button and logout on the tool bar
    public void setupOnClick() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(category, MainActivity.class);
                intent.putExtra(USERNAME_LABEL, username);
                startActivity(intent);
                finishActivity();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout logout = new Logout(category);
                startActivity(new Intent(category, Login.class));
                finishActivity();
            }
        });
    }
    //sets up the toolbar
    public void setToolBar(){
        backButton.setImageResource(R.drawable.back);
        title.setText(TITLE_LABEL);
    }
    //sets up all items on the GUI
    public void setGUI(){
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        backButton = (ImageView) findViewById(R.id.Tool_Bar_Back);
        translatedSignsAndSymbolsET = (EditText) findViewById(R.id.Category_TranslatedTxt);
        logout = (ImageView) findViewById(R.id.Tool_Bar_Logout);
    }
    //gets username and if the user has selected either signs or symbols
    public void getExtras() {
        Bundle bundle = getIntent().getExtras();
        isSymbolCategory = bundle.getBoolean(SYMBOL_LABEL);
        String currentText = bundle.getString(CURRENT_TEXT_LABEL);
        username = bundle.getString(USERNAME_LABEL);
        setCurrentText(currentText);
    }
    //array of categories
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
    //ends this activity
    public void finishActivity(){
        this.finish();
    }
}
