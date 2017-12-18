package com.example.shahzademambaccus.finalyearprojectuser;

import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class ImageDatabase {

    int count;
    private String[] listOfWords;
    int counter;

    public ImageDatabase() {

    }

    public void imageFromDatabase(final String word, final ImageView image, Words_Activity wordsActivity) {
        if (word.isEmpty()) {
            Toast.makeText(wordsActivity, "Please fill in box", Toast.LENGTH_LONG).show();
            return;
        }
        final String[] wordSplitted = word.split(" ");
        for (int i = 0; wordSplitted.length > i; i++) {
            //Toast.makeText(wordsActivity, wordsSplitted[i], Toast.LENGTH_LONG).show();
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            String link = jsonResponse.getString("image");
                            //Toast.makeText(wordsActivity, link, Toast.LENGTH_LONG).show();
                            new DownloadImage(link, image).execute();
                        } else {
                            //Toast.makeText(wordsActivity, "Some Symbols cannot be found", Toast.LENGTH_LONG).show();
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

    public void getSignOfTheDay(final ImageView image, MainActivity mainActivity) {
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

    public void getSignsAndSymbols(String category, final SignsAndSymbols signsAndSymbols) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        //String link = jsonResponse.getString("image");
                        //String word = jsonResponse.getString("word");
                        
                        //signsAndSymbols.getListOfWords()[counter] = jsonResponse.getString("word");
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        SignsAndSymbolsRequest signsAndSymbolsRequest = new SignsAndSymbolsRequest(category, responseListener);
        RequestQueue queue = Volley.newRequestQueue(signsAndSymbols);
        queue.add(signsAndSymbolsRequest);
        counter++;

    }

    public int getSignsAndSymbolsCount() {
        return count;
    }

}
