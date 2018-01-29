package com.example.shahzademambaccus.finalyearprojectuser;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {

    private ImageView image;
    private TextView word;

    public ViewHolder(View v){
        image = (ImageView) v.findViewById(R.id.SignsAndSymbolsImage);
        word = (TextView) v.findViewById(R.id.SignsAndSymbolsWord);
    }

    public ImageView getImageView(){
        return image;
    }

    public TextView getTextView(){
        return word;
    }

}
