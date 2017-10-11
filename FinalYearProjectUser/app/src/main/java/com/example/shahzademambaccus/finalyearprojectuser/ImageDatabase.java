package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Intent;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageDatabase {

    private Words_Activity wordsActivity;
    private boolean getImage = true;
    public String url = "";

    public ImageDatabase(Words_Activity words_activity){
        wordsActivity = words_activity;
    }

    public String imageFromDatabase(String word){
        getImage = false;
        if(word.isEmpty()){
            Toast.makeText(wordsActivity, "Please fill in box", Toast.LENGTH_LONG).show();
            return "";
        }
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}")+1));
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        String link = jsonResponse.getString("image");
                        new DownloadImage(link, wordsActivity.image).execute();

                    }else{
                        Toast.makeText(wordsActivity, "Cannot get Image", Toast.LENGTH_LONG).show();
                        getImage = false;
                    }
                } catch (JSONException e) {
                    getImage = false;
                    e.printStackTrace();
                }
            }
        };
        ImageRequest imageRequest = new ImageRequest(word, responseListener);
        RequestQueue queue = Volley.newRequestQueue(wordsActivity);
        queue.add(imageRequest);
        return url;
    }

}
