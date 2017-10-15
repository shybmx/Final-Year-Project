package com.example.shahzademambaccus.finalyearprojectuser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


public class Words_Activity extends AppCompatActivity {

    ImageView image;
    private ImageDatabase imageDatabase;
    private ImageView[] imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_);
        image = (ImageView) findViewById(R.id.Image);
        this.imageDatabase = new ImageDatabase(this);
    }

    public void search(View v){
        image.setImageResource(0);
        imageDatabase.imageFromDatabase(getSearchTerm());
    }

    public String getSearchTerm(){
        EditText searchTermET = (EditText) findViewById(R.id.SearchTerm);
        String searchTermTxt = searchTermET.getText().toString();
        return searchTermTxt;
    }

    public ImageView[] getImageArray(){
        return imageArray;
    }

}
