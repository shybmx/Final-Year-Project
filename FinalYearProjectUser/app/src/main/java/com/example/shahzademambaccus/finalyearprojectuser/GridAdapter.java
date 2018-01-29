package com.example.shahzademambaccus.finalyearprojectuser;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private ArrayList<String> arrayOfImageView;
    private ArrayList<String> arrayOfWords;
    private Context context;
    private boolean isSymbol;

    public GridAdapter(ArrayList<String> listOfImageView, ArrayList<String> listOfWords, Context context, boolean isSymbol){
       arrayOfImageView = listOfImageView;
       arrayOfWords = listOfWords;
       this.context = context;
       this.isSymbol = isSymbol;
    }

    @Override
    public int getCount() {
        return arrayOfImageView.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayOfImageView.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_image, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }else{
            holder = (ViewHolder) row.getTag();
        }
        if(isSymbol) {
            new DownloadImage(arrayOfImageView.get(position), holder.getImageView(), arrayOfWords.get(position), holder.getTextView()).execute();
        }else{
            Glide.with(context).load(arrayOfImageView.get(position)).asGif().into(holder.getImageView());
            holder.getTextView().setText(arrayOfWords.get(position));
        }
        return row;
    }
}
