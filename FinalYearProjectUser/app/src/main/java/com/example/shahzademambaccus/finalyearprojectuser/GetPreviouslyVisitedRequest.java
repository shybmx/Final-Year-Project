package com.example.shahzademambaccus.finalyearprojectuser;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

public class GetPreviouslyVisitedRequest extends StringRequest{
    private static final String GET_PREVIOUSLY_VISITED = "https://lissome-amperage.000webhostapp.com/PreviouslyVisited.php";
    private HashMap<String, String> params;

    public GetPreviouslyVisitedRequest(String username, Response.Listener<String> listener) {
        super(Method.POST, GET_PREVIOUSLY_VISITED, listener, null);
        params = new HashMap<>();
        params.put("user", username);
    }

    @Override
    public HashMap<String, String> getParams() {
        return params;
    }
}
