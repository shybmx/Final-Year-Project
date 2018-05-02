package com.example.shahzademambaccus.finalyearprojectuser;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//This class is used for grid view, variables for all items
public class ViewHolder {

    private ImageView image;
    private TextView word;

    public ViewHolder(View v){
        image = (ImageView) v.findViewById(R.id.Image_Signs_And_Symbol);
        word = (TextView) v.findViewById(R.id.Image_Signs_And_Symbol_Word);
    }

    public ImageView getImageView(){
        return image;
    }

    public TextView getTextView(){
        return word;
    }

}
