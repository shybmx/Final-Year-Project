package com.example.shahzademambaccus.finalyearprojectuser;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignsAndSymbolsRequest extends StringRequest {
    private static final String COUNT_URL = "https://lissome-amperage.000webhostapp.com/SignsAndSymbols.php";
    private Map<String, String> params;

    public SignsAndSymbolsRequest(String category, Response.Listener<String> listener) {
        super(Method.POST, COUNT_URL, listener, null);
        params = new HashMap<>();
        params.put("category", category);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
