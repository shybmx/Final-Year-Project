package com.example.shahzademambaccus.finalyearprojectuser;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ImageRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "https://lissome-amperage.000webhostapp.com/UploadImage.php";
    private Map<String, String> params;

    public ImageRequest(String word, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("word", word);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}