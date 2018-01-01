package com.example.shahzademambaccus.finalyearprojectuser;

import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyResponseListener implements Response.Listener<String> {

    int positionOfSignOrSymbol;
    private SignsAndSymbols signsAndSymbols;
    private static final String NAME_OF_ARRAY = "ListOfSignsAndSymbols";

    public MyResponseListener(int positionOfSignOrSymbol, SignsAndSymbols signsAndSymbols){
        this.positionOfSignOrSymbol = positionOfSignOrSymbol;
        this.signsAndSymbols = signsAndSymbols;
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
            JSONArray jsonArray = jsonResponse.getJSONArray(NAME_OF_ARRAY);
            JSONObject finalResponse = jsonArray.getJSONObject(positionOfSignOrSymbol);
            String word = finalResponse.getString("word");
            String links = finalResponse.getString("image");
            signsAndSymbols.getListOfWords().add(word);
            signsAndSymbols.getListOfLinks().add(links);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
