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

    private static final String WORD = "word";
    private static final String VIDEO = "video";
    private static final String IMAGE = "image";
    private static final String JSON_LIST = "List";

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
                    JSONArray jsonArray = jsonResponse.getJSONArray(JSON_LIST);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject finalResponse = jsonArray.getJSONObject(i);
                        wordsActivity.getListOfWords().add(finalResponse.getString(WORD));
                        wordsActivity.getListOfGifLinks().add(finalResponse.getString(VIDEO));
                        wordsActivity.getListOfImageLinks().add(finalResponse.getString(IMAGE));
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
                        String link = jsonResponse.getString(IMAGE);
                        String video = jsonResponse.getString(VIDEO);
                        String word = jsonResponse.getString(WORD);
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
                        String links;
                        if (isSymbol) {
                            links = finalResponse.getString(IMAGE);
                        } else {
                            links = finalResponse.getString(VIDEO);
                        }
                        signsAndSymbols.getListOfWords().add(finalResponse.getString(WORD));
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

    public void login(final String username, final String password, final Login login, final SharedPreferences.Editor editor){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        Intent intent = new Intent(login.getApplicationContext(), MainActivity.class);
                        editor.putBoolean("saveLogin", true);
                        editor.putString("Username", username);
                        editor.commit();
                        intent.putExtra("Username", username);
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
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        Intent intent = new Intent(register.getApplicationContext(), Login.class);
                        intent.putExtra("Username", username);
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
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
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
                    JSONArray jsonArray = jsonObject.getJSONArray(JSON_LIST);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject finalResponse = jsonArray.getJSONObject(i);
                        previouslyVisitedSigns.getListOfLinks().add(finalResponse.getString(IMAGE));
                        previouslyVisitedSigns.getListOfGifs().add(finalResponse.getString(VIDEO));
                        previouslyVisitedSigns.getListOfWords().add(finalResponse.getString(WORD));
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

}
