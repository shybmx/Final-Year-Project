package com.example.shahzademambaccus.finalyearprojectuser;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

public class MinigameRequest extends StringRequest {
    //URL for php file
    private static String URL = "https://lissome-amperage.000webhostapp.com/Minigame.php";
    private HashMap<String, String> params;

    public MinigameRequest(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
    }

    @Override
    public HashMap<String, String> getParams() {
        return params;
    }
}
