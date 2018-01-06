package com.example.shahzademambaccus.finalyearprojectuser;

import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ImageDatabase {

    int count = 0;
    int counter = 0;
    private String[] listOfWords;

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
                    String link = jsonResponse.getString("image");
                    if(!link.equals("")) {
                        wordsActivity.getListOfLinks().add(link); //TODO: crashes when invalid word is typed in
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

    public void getSignOfTheDay(final ImageView image, final MainActivity mainActivity) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        String link = jsonResponse.getString("image");
                        String video = jsonResponse.getString("video");
                        Toast.makeText(mainActivity, "In ID: " + video, Toast.LENGTH_SHORT).show();
                        mainActivity.setSignGIF(video);
                        new DownloadImage(link, image).execute();
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

    public void signsAndSymbolsCounter(String category, SignsAndSymbols signsAndSymbols) {
        count = 0;
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        count = jsonResponse.getInt("count");
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        CountRequest countRequest = new CountRequest(category, responseListener);
        RequestQueue queue = Volley.newRequestQueue(signsAndSymbols);
        queue.add(countRequest);
    }

    public void getSignsAndSymbols(String category, SignsAndSymbols signsAndSymbols, int numberToDisplay) {
        counter = 0;
        signsAndSymbols.clearLists(signsAndSymbols.getListOfWords());
        signsAndSymbols.clearLists(signsAndSymbols.getListOfLinks());
        while (counter < numberToDisplay){
            MyResponseListener responseListener = new MyResponseListener(counter, signsAndSymbols);
            SignsAndSymbolsRequest signsAndSymbolsRequest = new SignsAndSymbolsRequest(category, responseListener);
            RequestQueue queue = Volley.newRequestQueue(signsAndSymbols);
            queue.add(signsAndSymbolsRequest);
            counter++;
        }
    }

    public int getSignsAndSymbolsCount() {
        return count;
    }

}
