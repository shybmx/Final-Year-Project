package com.example.shahzademambaccus.finalyearprojectuser;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private ArrayList<String> arrayList;
    private Context context;
    private boolean isSymbol;

    public GridAdapter(ArrayList<String> list, Context context, boolean isSymbol){
       arrayList = list;
       this.context = context;
       this.isSymbol = isSymbol;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
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
            new DownloadImage(arrayList.get(position), holder.getImageView()).execute();
        }else{
            Glide.with(context).load(arrayList.get(position)).asGif().into(holder.getImageView());
        }
        return row;
    }
}
