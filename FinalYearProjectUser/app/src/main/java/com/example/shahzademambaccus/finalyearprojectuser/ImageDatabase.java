package com.example.shahzademambaccus.finalyearprojectuser;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ImageDatabase {

    boolean loggedin = false;

    public ImageDatabase() {

    }

    public void imageFromDatabase(final String word, final Words_Activity wordsActivity) {
        if (word.isEmpty()) {
            Toast.makeText(wordsActivity, "Please fill in box", Toast.LENGTH_LONG).show();
            return;
        }
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    JSONArray jsonArray = jsonResponse.getJSONArray("List");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject finalResponse = jsonArray.getJSONObject(i);
                        wordsActivity.getListOfWords().add(finalResponse.getString("word"));
                        wordsActivity.getListOfGifLinks().add(finalResponse.getString("video"));
                        wordsActivity.getListOfImageLinks().add(finalResponse.getString("image"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ImageRequest imageRequest = new ImageRequest(word, responseListener);
        RequestQueue queue = Volley.newRequestQueue(wordsActivity);
        queue.add(imageRequest);
    }

    public void getSignOfTheDay(final ImageView image, final MainActivity mainActivity, final TextView textView) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        String link = jsonResponse.getString("image");
                        String video = jsonResponse.getString("video");
                        String word = jsonResponse.getString("word");
                        //Toast.makeText(mainActivity, "In ID: " + video, Toast.LENGTH_SHORT).show();
                        mainActivity.setSignGIF(video);
                        mainActivity.setWord(word);
                        new DownloadImage(link, image, word, textView).execute();
                    } else {
                        //Toast.makeText(mainActivity, "Sign of the day cannot be retrieved", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        SignOfTheDayRequest signOfTheDayRequest = new SignOfTheDayRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(mainActivity);
        queue.add(signOfTheDayRequest);
    }

    public void getSignsAndSymbols(final String category, final SignsAndSymbols signsAndSymbols, final boolean isSymbol) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    JSONArray jsonArray = jsonResponse.getJSONArray("ListOfSignsAndSymbols");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject finalResponse = jsonArray.getJSONObject(i);
                        String word = finalResponse.getString("word");
                        String links;
                        if (isSymbol) {
                            links = finalResponse.getString("image");
                        } else {
                            links = finalResponse.getString("video");
                        }
                        signsAndSymbols.getListOfWords().add(word);
                        signsAndSymbols.getListOfLinks().add(links);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        SignsAndSymbolsRequest signsAndSymbolsRequest = new SignsAndSymbolsRequest(category, responseListener);
        RequestQueue queue = Volley.newRequestQueue(signsAndSymbols);
        queue.add(signsAndSymbolsRequest);
    }

    public boolean login(String username, String password, Login login){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                      loggedin = true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(login);
        queue.add(loginRequest);
        return loggedin;
    }

}
