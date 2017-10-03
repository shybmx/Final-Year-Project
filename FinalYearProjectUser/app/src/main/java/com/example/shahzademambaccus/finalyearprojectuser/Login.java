package com.example.shahzademambaccus.finalyearprojectuser;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login {

    private Login_Activity loginActivity;
    private boolean loginComplete = true;

    public Login(Login_Activity login_activity){
        this.loginActivity = login_activity;
    }

    public boolean loginToDatabase(String eMail, String passWord){
        if(eMail.isEmpty() || passWord.isEmpty() ){
            loginComplete = false;
            return loginComplete;
        }
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}")+1));
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        loginComplete = true;
                    }else{
                        loginComplete = false;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    loginComplete = false;
                }
            }
        };
        LoginRequest loginRequest = new LoginRequest(eMail, passWord, responseListener);
        RequestQueue queue = Volley.newRequestQueue(loginActivity);
        queue.add(loginRequest);
        return loginComplete;
    }
}