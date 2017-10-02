package com.example.shahzademambaccus.myapplication;

import android.renderscript.Script;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register {

    private Register_Activity registerActivity;

    public Register(){

    }

    public boolean registerToDatabase(){
        if(getEmail().isEmpty() || getFirstName().isEmpty() || getLastName().isEmpty()
                || getDateOfBirth().isEmpty() || getPhoneNumber() == 0){
            return false;
        }
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"),
                            response.lastIndexOf("}") + 1));
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        //TODO: Clear field
                        //TODO: Return to previous page
                        //TODO: Make a toast to signal success
                    }else{
                        //TODO: need to return false
                        //TODO: Make a toast to signal unsuccessful
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        RegisterRequest registerRequest = new RegisterRequest(getEmail(), getFirstName(),
                getLastName(), getDateOfBirth(), getPhoneNumber(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(registerActivity);
        queue.add(registerRequest);
        return true;
    }

    public String getEmail(){
        EditText eMailET = (EditText) registerActivity.findViewById(R.id.RegisterE_Mail);
        String eMailTxt = eMailET.getText().toString();
        return eMailTxt;
    }

    public String getFirstName(){
        EditText eFirstName = (EditText) registerActivity.findViewById(R.id.RegisterFirstName);
        String firstNameTxt = eFirstName.getText().toString();
        return firstNameTxt;
    }

    public String getLastName(){
        EditText eLastName = (EditText) registerActivity.findViewById(R.id.RegisterLastName);
        String lastName = eLastName.getText().toString();
        return lastName;
    }

    public String getDateOfBirth(){
        EditText eDateOfBirth = (EditText) registerActivity.findViewById(R.id.RegisterBirthday);
        String dateOfBirth = eDateOfBirth.getText().toString();
        return dateOfBirth;
    }

    public int getPhoneNumber(){
        EditText ePhoneNumber = (EditText) registerActivity.findViewById(R.id.RegisterNumber);
        int phoneNumber = Integer.parseInt(ePhoneNumber.getText().toString());
        return phoneNumber;
    }
}