package com.example.shahzademambaccus.finalyearprojectuser;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SaveSignRequest extends StringRequest{
    //URL for php file
    private static final String URL = "https://lissome-amperage.000webhostapp.com/SaveSign.php";
    private Map<String, String> params;

    public SaveSignRequest(String username, String word, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        //pass username and selected word to file
        params.put("user", username);
        params.put("word", word);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
