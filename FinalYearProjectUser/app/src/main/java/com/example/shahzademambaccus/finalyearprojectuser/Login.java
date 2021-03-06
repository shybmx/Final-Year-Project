package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private TextView title;
    private ImageView logo;
    private EditText userName;
    private EditText passWord;
    private DatabaseConnection database;
    private SharedPreferences sharedPreference;
    private SharedPreferences.Editor editor;
    private Boolean saveLogin;
    private ImageView logout;
    private static final String USERNAME_LABEL = "Username";
    private static final String LOGIN_LABEL = "Login";
    private static final String LOGIN_STATUS_LABEL = "saveLogin";
    private static final String LOGIN_REF_LABEL = "loginRef";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //create a connection for the database
        database = new DatabaseConnection();
        sharedPreferences();
        setupGUI();
        setToolBar();
        getExtras();
    }
    //get user session
    public void sharedPreferences() {
        sharedPreference = getSharedPreferences(LOGIN_REF_LABEL, MODE_PRIVATE);
        editor = sharedPreference.edit();
        saveLogin = sharedPreference.getBoolean(LOGIN_STATUS_LABEL, false);
        //if session found go to main menu
        if(saveLogin){
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.putExtra(USERNAME_LABEL, sharedPreference.getString(USERNAME_LABEL, null));
            startActivity(intent);
            finishActivity();
        }
    }

    public void getExtras() {
        //Bundle bundle = getIntent().getExtras();
        //String registerUsername = bundle.getString("username");
        //setUserName(registerUsername);
    }

    public void setToolBar() {
        title.setText(LOGIN_LABEL);
        logo.setImageResource(R.drawable.logo);
        logout.setImageResource(0);
    }
    //get username and password when login is pressed
    public void loginButtonPressed(View v){
        String user = userName.getText().toString();
        String pass = passWord.getText().toString();
        if(checkFields(user, pass)){
            //encrypt password
            Encryption encryption = new Encryption();
            String encryptedPassword = encryption.cipher(pass);
            editor = sharedPreference.edit();
            //send login deatils to database
            database.login(user, encryptedPassword, this, editor);
        }
    }
    //when resgiter button is pressed
    public void registerButtonPressed(View v){
        startActivity(new Intent(Login.this, Register.class));
        finishActivity();
    }
    //check if fields are empty
    public boolean checkFields(String username, String password){
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill username and password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //setup GUI
    public void setupGUI(){
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        logo = (ImageView) findViewById(R.id.Tool_Bar_Back);
        userName = (EditText) findViewById(R.id.Login_userNameET);
        passWord = (EditText) findViewById(R.id.Login_passwordET);
        logout = (ImageView) findViewById(R.id.Tool_Bar_Logout);
    }

    public void setUserName(String userNameField){
      userName.setText(userNameField);
    }
    //end this activity
    public void finishActivity(){
        this.finish();
    }
}
