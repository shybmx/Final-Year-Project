package com.example.shahzademambaccus.finalyearprojectuser;

import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageDatabase {

    private Words_Activity wordsActivity;

    public ImageDatabase(Words_Activity words_activity){
        wordsActivity = words_activity;
    }

    public void imageFromDatabase(String word, final ImageView image){
        if(word.isEmpty()){
            Toast.makeText(wordsActivity, "Please fill in box", Toast.LENGTH_LONG).show();
            return;
        }
        final String [] wordSplitted = word.split(" ");
        for(int i = 0; wordSplitted.length > i; i++) {
            //Toast.makeText(wordsActivity, wordsSplitted[i], Toast.LENGTH_LONG).show();
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            String link = jsonResponse.getString("image");
                            new DownloadImage(link, image).execute();
                        } else {
                            Toast.makeText(wordsActivity, "Some Symbols cannot be found", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            ImageRequest imageRequest = new ImageRequest(wordSplitted[i], responseListener);
            RequestQueue queue = Volley.newRequestQueue(wordsActivity);
            queue.add(imageRequest);
        }
    }

}
