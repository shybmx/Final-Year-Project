package com.example.shahzademambaccus.finalyearprojectuser;

import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register {

    private Register_Activity registerActivity;
    private boolean registerComplete = true;

    public Register(Register_Activity register_activity){
        this.registerActivity = register_activity;
    }

    public boolean registerToDatabase(String eMail, String firstName, String lastName, String dateOfBirth, String phoneNumber, String passWord, String passWordRe){
        if(!passWord.equals(passWordRe)){
            Toast.makeText(registerActivity, "Password do not match", Toast.LENGTH_LONG).show();
            registerComplete = false;
            return registerComplete;
        }
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}")+1));
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        registerComplete = true;
                        //Toast.makeText(registerActivity, "Register Successful", Toast.LENGTH_LONG).show();
                    }else{
                        registerComplete = false;
                        //Toast.makeText(registerActivity, "Not", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    registerComplete = false;
                    //Toast.makeText(registerActivity, "Error", Toast.LENGTH_LONG).show();
                }
            }
        };
        //Toast.makeText(registerActivity, eMail + firstName + lastName + dateOfBirth + phoneNumber + passWord, Toast.LENGTH_LONG).show();
        RegisterRequest registerRequest = new RegisterRequest(eMail, firstName, lastName, dateOfBirth, Integer.parseInt(phoneNumber), passWord, responseListener);
        RequestQueue queue = Volley.newRequestQueue(registerActivity);
        queue.add(registerRequest);
        return registerComplete;
    }
}
