package com.example.shahzademambaccus.finalyearprojectuser;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

public class GetPreviouslyVisitedRequest extends StringRequest{
    //URL for php file
    private static final String URL = "https://lissome-amperage.000webhostapp.com/PreviouslyVisited.php";
    private HashMap<String, String> params;

    public GetPreviouslyVisitedRequest(String username, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        //Pass in username to php file
        params.put("user", username);
    }

    @Override
    public HashMap<String, String> getParams() {
        return params;
    }
}
