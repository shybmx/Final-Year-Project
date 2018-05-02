package com.example.shahzademambaccus.finalyearprojectuser;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{
    //URL for php file
    private static final String URL = "https://lissome-amperage.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        //pass username and password to php file
        params.put("user", username);
        params.put("pass", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
