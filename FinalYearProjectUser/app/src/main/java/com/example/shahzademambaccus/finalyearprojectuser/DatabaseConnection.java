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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DatabaseConnection {
    //static values used in this class
    private static final String WORD_LABEL = "word";
    private static final String VIDEO_LABEL = "video";
    private static final String IMAGE_LABEL = "image";
    private static final String JSON_LIST_LABEL = "List";
    private static final String SUCCESS_LABEL = "success";
    private static final String USERNAME_LABEL = "Username";

    public DatabaseConnection() {

    }
    //gets images from database for text to BSL
    public void imageFromDatabase(final String word, final Words_Activity wordsActivity) {
        //if the input is empty
        if (word.isEmpty()) {
            Toast.makeText(wordsActivity, "Please fill in box", Toast.LENGTH_LONG).show();
            return;
        }
        //sends a request to the database
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Get JSON
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    //gets the JSON array
                    JSONArray jsonArray = jsonResponse.getJSONArray(JSON_LIST_LABEL);
                    for(int i = 0; i < jsonArray.length(); i++){
                        //places values from array into appropriate array within the application
                        JSONObject finalResponse = jsonArray.getJSONObject(i);
                        wordsActivity.getListOfWords().add(finalResponse.getString(WORD_LABEL));
                        wordsActivity.getListOfGifLinks().add(finalResponse.getString(VIDEO_LABEL));
                        wordsActivity.getListOfImageLinks().add(finalResponse.getString(IMAGE_LABEL));
                    }
                } catch (JSONException e) {
                    //catches any errors
                    e.printStackTrace();
                }
            }
        };
        //sends data to request class
        ImageRequest imageRequest = new ImageRequest(word, responseListener);
        //make a request queue
        RequestQueue queue = Volley.newRequestQueue(wordsActivity);
        //adds request to queue
        queue.add(imageRequest);
    }
    //get sign of the day from database
    public void getSignOfTheDay(final ImageView image, final MainActivity mainActivity, final TextView textView) {
        //sends a request to the database
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    //checks if getting the data is a success, based of json value
                    boolean success = jsonResponse.getBoolean(SUCCESS_LABEL);
                    if (success) {
                        //gets the JSON object
                        String word = jsonResponse.getString(WORD_LABEL);
                        //Get values from JSON and place into appropriate values
                        mainActivity.setSignGIF(jsonResponse.getString(VIDEO_LABEL));
                        mainActivity.setWord(word);
                        Picasso.with(mainActivity).load(jsonResponse.getString(IMAGE_LABEL)).placeholder(R.drawable.loadingwhite).into(image);
                        textView.setText(word);
                    } else {
                        //Toast.makeText(mainActivity, "Sign of the day cannot be retrieved", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    //catches errors
                    e.printStackTrace();
                }
            }
        };
        //sends data to request class
        SignOfTheDayRequest signOfTheDayRequest = new SignOfTheDayRequest(responseListener);
        //make a request queue
        RequestQueue queue = Volley.newRequestQueue(mainActivity);
        //adds request to queue
        queue.add(signOfTheDayRequest);
    }
    //get signs and symbols from database for converting back into text
    public void getSignsAndSymbols(final String category, final SignsAndSymbols signsAndSymbols, final boolean isSymbol) {
        //sends a request to the database
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Get JSON
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    //gets the JSON array
                    JSONArray jsonArray = jsonResponse.getJSONArray(JSON_LIST_LABEL);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        //Get values from JSON and place into appropriate values
                        JSONObject finalResponse = jsonArray.getJSONObject(i);
                        if (isSymbol) {
                            signsAndSymbols.getListOfLinks().add(finalResponse.getString(IMAGE_LABEL));
                        } else {
                            signsAndSymbols.getListOfLinks().add(finalResponse.getString(VIDEO_LABEL));
                        }
                        signsAndSymbols.getListOfWords().add(finalResponse.getString(WORD_LABEL));
                    }
                } catch (JSONException e) {
                    //catches errors
                    e.printStackTrace();
                }
            }
        };
        //sends data to request class
        SignsAndSymbolsRequest signsAndSymbolsRequest = new SignsAndSymbolsRequest(category, responseListener);
        //make a request queue
        RequestQueue queue = Volley.newRequestQueue(signsAndSymbols);
        //adds request to queue
        queue.add(signsAndSymbolsRequest);
    }

    public void login(final String username, final String password, final Login login, final SharedPreferences.Editor editor){
        //sends a request to the database
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Get JSON
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    //Check if username was found on database
                    boolean success = jsonResponse.getBoolean(SUCCESS_LABEL);
                    if(success){
                        //create new activity
                        Intent intent = new Intent(login.getApplicationContext(), MainActivity.class);
                        //save session and add extra values
                        editor.putBoolean("saveLogin", true);
                        editor.putString(USERNAME_LABEL, username);
                        editor.commit();
                        intent.putExtra(USERNAME_LABEL, username);
                        //start new activity
                        login.startActivity(intent);
                    }else{
                        Toast.makeText(login, "Username/Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    //catch error
                    e.printStackTrace();
                }
            }
        };
        //sends data to request class
        LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
        //make a request queue
        RequestQueue queue = Volley.newRequestQueue(login);
        //adds request to queue
        queue.add(loginRequest);
    }

    public void register(final String username, String password, final Register register){
        //sends a request to the database
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Get JSON
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    //Check if username was found on database
                    boolean success = jsonResponse.getBoolean(SUCCESS_LABEL);
                    if(success){
                        //create new activity
                        Intent intent = new Intent(register.getApplicationContext(), Login.class);
                        intent.putExtra(USERNAME_LABEL, username);
                        //start new activity
                        register.startActivity(intent);
                    }else{
                        Toast.makeText(register, "Username taken", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    //catch error
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
                    //catch error
                    e.printStackTrace();
                }
            }
        };
        //sends data to request class
        SaveSignRequest saveSignRequest = new SaveSignRequest(username, word, responseListener);
        //make a request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        //adds request to queue
        queue.add(saveSignRequest);
    }

    public void getPrevisoulyVisited(String username, final PreviouslyVisitedSigns previouslyVisitedSigns){
        //sends a request to the database
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Get JSON
                    JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    //Get JSON array
                    JSONArray jsonArray = jsonObject.getJSONArray(JSON_LIST_LABEL);
                    for(int i = 0; i < jsonArray.length(); i++){
                        //Get values from JSON array and place into appropriate values
                        JSONObject finalResponse = jsonArray.getJSONObject(i);
                        previouslyVisitedSigns.getListOfLinks().add(finalResponse.getString(IMAGE_LABEL));
                        previouslyVisitedSigns.getListOfGifs().add(finalResponse.getString(VIDEO_LABEL));
                        previouslyVisitedSigns.getListOfWords().add(finalResponse.getString(WORD_LABEL));
                    }
                } catch (JSONException e) {
                    //catch error
                    e.printStackTrace();
                }
            }
        };
        //sends data to request class
        GetPreviouslyVisitedRequest getPreviouslyVisitedRequest = new GetPreviouslyVisitedRequest(username, responseListener);
        //make a request queue
        RequestQueue queue = Volley.newRequestQueue(previouslyVisitedSigns);
        //adds request to queue
        queue.add(getPreviouslyVisitedRequest);
    }

    public void getMinigameSignsAndSymbols(final Minigame minigame){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Get JSON
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    if(jsonResponse.getBoolean("success")){
                        //Get values from JSON array and place into appropriate values
                        minigame.setSign(jsonResponse.getString(IMAGE_LABEL));
                        minigame.setSymbol(jsonResponse.getString(VIDEO_LABEL));
                        minigame.setWord(jsonResponse.getString(WORD_LABEL));
                    }
                } catch (JSONException e) {
                    //catch error
                    e.printStackTrace();
                }
            }
        };
        //sends data to request class
        MinigameRequest minigameRequest = new MinigameRequest(responseListener);
        //make a request queue
        RequestQueue queue = Volley.newRequestQueue(minigame);
        //adds request to queue
        queue.add(minigameRequest);
    }

}
