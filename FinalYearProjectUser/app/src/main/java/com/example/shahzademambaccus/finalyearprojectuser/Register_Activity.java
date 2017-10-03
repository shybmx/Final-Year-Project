package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity {

    private Register register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        this.register = new Register(this);
    }

    public void registerToDatabase(View v){
        if(register.registerToDatabase(getEMail(), getFirstName(), getLastName(), getDateOfBirth(), getPhoneNumber(), getPassword())){
            Toast.makeText(this, "Register Successful", Toast.LENGTH_LONG).show();
            clearFields();
            startActivity(new Intent(Register_Activity.this, Login_Activity.class));
        }else{
            Toast.makeText(this, "Register Unsuccessful", Toast.LENGTH_LONG).show();
        }
    }

    public void clearFields(){
        EditText eMailET = (EditText) findViewById(R.id.RegisterEMail);
        EditText firstNameET = (EditText) findViewById(R.id.RegisterFirstName);
        EditText lastNameET = (EditText) findViewById(R.id.RegisterLastName);
        EditText dateOfBirthET = (EditText) findViewById(R.id.RegisterBirthday);
        EditText phoneNumberET = (EditText) findViewById(R.id.RegisterPhoneNumber);
        EditText passwordET = (EditText) findViewById(R.id.RegisterPassword);
        eMailET.setText("");
        firstNameET.setText("");
        lastNameET.setText("");
        dateOfBirthET.setText("");
        phoneNumberET.setText("");
        passwordET.setText("");
    }

    public String getEMail(){
        EditText eMailET = (EditText) findViewById(R.id.RegisterEMail);
        String eMailTxt = eMailET.getText().toString();
        return eMailTxt;
    }

    public String getFirstName(){
        EditText firstNameET = (EditText) findViewById(R.id.RegisterFirstName);
        String firstNameTxt = firstNameET.getText().toString();
        return firstNameTxt;
    }

    public String getLastName(){
        EditText lastNameET = (EditText) findViewById(R.id.RegisterLastName);
        String lastNameTxt = lastNameET.getText().toString();
        return lastNameTxt;
    }

    public String getDateOfBirth(){
        EditText dateOfBirthET = (EditText) findViewById(R.id.RegisterBirthday);
        String dateOfBirthTxt = dateOfBirthET.getText().toString();
        return dateOfBirthTxt;
    }

    public String getPhoneNumber(){
        EditText phoneNumberET = (EditText) findViewById(R.id.RegisterPhoneNumber);
        String phoneNumberTxt = phoneNumberET.getText().toString();
        return phoneNumberTxt;
    }

    public String getPassword(){
        EditText passwordET = (EditText) findViewById(R.id.RegisterPassword);
        String passwordTxt = passwordET.getText().toString();
        return passwordTxt;
    }

}
