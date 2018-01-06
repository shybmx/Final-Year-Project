package com.example.shahzademambaccus.finalyearprojectuser;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private ArrayList<String> arrayList;
    private Context context;

    public GridAdapter(ArrayList<String> list, Context context){
       arrayList = list;
       this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
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
        new DownloadImage(arrayList.get(position), holder.getImageView()).execute();
        //Toast.makeText(context, "Position: " + position + " word: " +list.get(position), Toast.LENGTH_SHORT).show();
        return row;
    }
}
