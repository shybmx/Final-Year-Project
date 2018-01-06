package com.example.shahzademambaccus.finalyearprojectuser;

import android.view.View;
import android.widget.ImageView;

public class ViewHolder {

    private ImageView image;

    public ViewHolder(View v){
        image = (ImageView) v.findViewById(R.id.SignsAndSymbolsImage);
    }

    public ImageView getImageView(){
        return image;
    }

}
