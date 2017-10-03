package com.example.shahzademambaccus.finalyearprojectuser;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{

    private static final String LOGIN_REQUEST_URL = "https://lissome-amperage.000webhostapp.com/Login.php";
    private Map<String, String> params;

    public LoginRequest(String eMail, String passWord, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", eMail);
        params.put("pass", passWord);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
