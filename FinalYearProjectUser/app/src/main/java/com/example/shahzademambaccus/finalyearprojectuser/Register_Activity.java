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
        if(getEMail().isEmpty() || getFirstName().isEmpty() || getLastName().isEmpty() || getDateOfBirth().isEmpty() || getPhoneNumber().isEmpty() || getPassword().isEmpty() || getReTypePassword().isEmpty()){
            Toast.makeText(this, "Fill in all fields", Toast.LENGTH_LONG).show();
            return;
        }
        if(register.registerToDatabase(getEMail(), getFirstName(), getLastName(), getDateOfBirth(), getPhoneNumber(), getPassword(), getReTypePassword())){
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
        EditText dateOfBirthET = (EditText) findViewById(R.id.RegisterBirthdayDate);
        EditText monthOfBirthET = (EditText) findViewById(R.id.RegisterBirthdayMonth);
        EditText yearOfBirthET = (EditText) findViewById(R.id.RegisterBirthdayYear);
        EditText phoneNumberET = (EditText) findViewById(R.id.RegisterPhoneNumber);
        EditText passwordET = (EditText) findViewById(R.id.RegisterPassword);
        EditText passwordReET = (EditText) findViewById(R.id.RegisterPasswordRe);
        eMailET.setText("");
        firstNameET.setText("");
        lastNameET.setText("");
        dateOfBirthET.setText("");
        monthOfBirthET.setText("");
        yearOfBirthET.setText("");
        phoneNumberET.setText("");
        passwordET.setText("");
        passwordReET.setText("");
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
        EditText birthDateET = (EditText) findViewById(R.id.RegisterBirthdayDate);
        EditText birthMonthET = (EditText) findViewById(R.id.RegisterBirthdayMonth);
        EditText birthYearET = (EditText) findViewById(R.id.RegisterBirthdayYear);
        int birthDateInt = Integer.parseInt(birthDateET.getText().toString());
        int birthMonthInt = Integer.parseInt(birthMonthET.getText().toString());
        int birthYearInt = Integer.parseInt(birthYearET.getText().toString());
        if(birthDateInt > 31){
            Toast.makeText(this, "Invalid Date", Toast.LENGTH_LONG).show();
            birthDateET.setText("");
            return "";
        }
        if(birthMonthInt > 12){
            Toast.makeText(this, "Invalid Month", Toast.LENGTH_LONG).show();
            birthMonthET.setText("");
            return "";
        }
        if((birthYearInt < 1900) || (birthYearInt > 2018)){
            Toast.makeText(this, "Invalid Year", Toast.LENGTH_LONG).show();
            birthYearET.setText("");
            return "";
        }
        String date = Integer.toString(birthDateInt) + "/" + Integer.toString(birthMonthInt) + "/" + Integer.toString(birthYearInt);
        return date;
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

    public String getReTypePassword(){
        EditText passWordReET = (EditText) findViewById(R.id.RegisterPasswordRe);
        String passwordReTxt = passWordReET.getText().toString();
        return passwordReTxt;
    }

}
