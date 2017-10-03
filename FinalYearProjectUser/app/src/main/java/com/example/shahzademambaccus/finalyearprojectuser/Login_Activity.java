package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {

    private Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        this.login = new Login(this);
    }

    public void registerActivity(View v){
        startActivity(new Intent(Login_Activity.this, Register_Activity.class));
    }

    public void loginToDatabase(View v){
        if(login.loginToDatabase(getEMail(), getPassword())){
            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    public String getEMail(){
        EditText eMailET = (EditText) findViewById(R.id.LoginEMail);
        String eMailTxt = eMailET.getText().toString();
        return eMailTxt;
    }

    public String getPassword(){
        EditText passwordET = (EditText) findViewById(R.id.LoginPassword);
        String passwordTxt = passwordET.getText().toString();
        return passwordTxt;
    }
}
