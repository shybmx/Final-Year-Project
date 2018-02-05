package com.example.shahzademambaccus.finalyearprojectuser;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SaveSignRequest extends StringRequest{
    private static final String SAVE_SYMBOL_URL = "https://lissome-amperage.000webhostapp.com/SaveSign.php";
    private Map<String, String> params;

    public SaveSignRequest(String username, String word, Response.Listener<String> listener) {
        super(Method.POST, SAVE_SYMBOL_URL, listener, null);
        params = new HashMap<>();
        params.put("user", username);
        params.put("word", word);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
