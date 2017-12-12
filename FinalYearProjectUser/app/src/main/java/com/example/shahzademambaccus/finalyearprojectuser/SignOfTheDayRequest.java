package com.example.shahzademambaccus.finalyearprojectuser;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignOfTheDayRequest extends StringRequest{
    private static final String SIGN_OF_THE_DAY = "https://lissome-amperage.000webhostapp.com/SignOfTheDay.php";
    private Map<String, String> params;

    public SignOfTheDayRequest(Response.Listener<String> listener){
        super(Request.Method.POST, SIGN_OF_THE_DAY, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
