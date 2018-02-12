package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DatabaseConnection {

    private static final String WORD_LABEL = "word";
    private static final String VIDEO_LABEL = "video";
    private static final String IMAGE_LABEL = "image";
    private static final String JSON_LIST_LABEL = "List";
    private static final String SUCCESS_LABEL = "success";
    private static final String USERNAME_LABEL = "Username";

    public DatabaseConnection() {

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
                    JSONArray jsonArray = jsonResponse.getJSONArray(JSON_LIST_LABEL);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject finalResponse = jsonArray.getJSONObject(i);
                        wordsActivity.getListOfWords().add(finalResponse.getString(WORD_LABEL));
                        wordsActivity.getListOfGifLinks().add(finalResponse.getString(VIDEO_LABEL));
                        wordsActivity.getListOfImageLinks().add(finalResponse.getString(IMAGE_LABEL));
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
                    boolean success = jsonResponse.getBoolean(SUCCESS_LABEL);
                    if (success) {
                        String word = jsonResponse.getString(WORD_LABEL);
                        mainActivity.setSignGIF(jsonResponse.getString(VIDEO_LABEL));
                        mainActivity.setWord(word);
                        new DownloadImage(jsonResponse.getString(IMAGE_LABEL), image, word, textView).execute();
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
                    JSONArray jsonArray = jsonResponse.getJSONArray(JSON_LIST_LABEL);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject finalResponse = jsonArray.getJSONObject(i);
                        if (isSymbol) {
                            signsAndSymbols.getListOfLinks().add(finalResponse.getString(IMAGE_LABEL));
                        } else {
                            signsAndSymbols.getListOfLinks().add(finalResponse.getString(VIDEO_LABEL));
                        }
                        signsAndSymbols.getListOfWords().add(finalResponse.getString(WORD_LABEL));
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

    public void login(final String username, final String password, final Login login, final SharedPreferences.Editor editor){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    boolean success = jsonResponse.getBoolean(SUCCESS_LABEL);
                    if(success){
                        Intent intent = new Intent(login.getApplicationContext(), MainActivity.class);
                        editor.putBoolean("saveLogin", true);
                        editor.putString(USERNAME_LABEL, username);
                        editor.commit();
                        intent.putExtra(USERNAME_LABEL, username);
                        login.startActivity(intent);
                    }else{
                        Toast.makeText(login, "Username/Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(login);
        queue.add(loginRequest);
    }

    public void register(final String username, String password, final Register register){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    boolean success = jsonResponse.getBoolean(SUCCESS_LABEL);
                    if(success){
                        Intent intent = new Intent(register.getApplicationContext(), Login.class);
                        intent.putExtra(USERNAME_LABEL, username);
                        register.startActivity(intent);
                    }else{
                        Toast.makeText(register, "Username taken", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        RegisterRequest registerRequest = new RegisterRequest(username, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(register);
        queue.add(registerRequest);
    }

    public void addToPreviouslyVisited(String username, String word, final Context context){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    boolean success = jsonResponse.getBoolean(SUCCESS_LABEL);
                    if(!success){
                       Toast.makeText(context, "Error connecting to database", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        SaveSignRequest saveSignRequest = new SaveSignRequest(username, word, responseListener);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(saveSignRequest);
    }

    public void getPrevisoulyVisited(String username, final PreviouslyVisitedSigns previouslyVisitedSigns){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    JSONArray jsonArray = jsonObject.getJSONArray(JSON_LIST_LABEL);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject finalResponse = jsonArray.getJSONObject(i);
                        previouslyVisitedSigns.getListOfLinks().add(finalResponse.getString(IMAGE_LABEL));
                        previouslyVisitedSigns.getListOfGifs().add(finalResponse.getString(VIDEO_LABEL));
                        previouslyVisitedSigns.getListOfWords().add(finalResponse.getString(WORD_LABEL));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        GetPreviouslyVisitedRequest getPreviouslyVisitedRequest = new GetPreviouslyVisitedRequest(username, responseListener);
        RequestQueue queue = Volley.newRequestQueue(previouslyVisitedSigns);
        queue.add(getPreviouslyVisitedRequest);
    }

    public void getMinigameSignsAndSymbols(final Minigame minigame){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    if(jsonResponse.getBoolean("success")){
                        minigame.setSign(jsonResponse.getString(IMAGE_LABEL));
                        minigame.setSymbol(jsonResponse.getString(VIDEO_LABEL));
                        minigame.setWord(jsonResponse.getString(WORD_LABEL));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        MinigameRequest minigameRequest = new MinigameRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(minigame);
        queue.add(minigameRequest);
    }

}
