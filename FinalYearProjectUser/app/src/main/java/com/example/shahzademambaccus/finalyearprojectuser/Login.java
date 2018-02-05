package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = new DatabaseConnection();
        setupGUI();
        setToolBar();
        getExtras();
    }

    public void getExtras() {
        //Bundle bundle = getIntent().getExtras();
        //String registerUsername = bundle.getString("username");
        //setUserName(registerUsername);
    }

    public void setToolBar() {
        title.setText("Login");
        logo.setImageResource(R.drawable.logo);
    }

    public void loginButtonPressed(View v){
        String user = userName.getText().toString();
        String pass = passWord.getText().toString();
        if(checkFields(user, pass)){
            database.login(user, pass, this);
        }
    }

    public void registerButtonPressed(View v){
        startActivity(new Intent(Login.this, Register.class));
    }

    public boolean checkFields(String username, String password){
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill username and password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void setupGUI(){
        title = (TextView) findViewById(R.id.Tool_Bar_Text);
        logo = (ImageView) findViewById(R.id.Tool_Bar_Back);
        userName = (EditText) findViewById(R.id.Login_userNameET);
        passWord = (EditText) findViewById(R.id.Login_passwordET);
    }

    public void setUserName(String userNameField){
      userName.setText(userNameField);
    }
}
