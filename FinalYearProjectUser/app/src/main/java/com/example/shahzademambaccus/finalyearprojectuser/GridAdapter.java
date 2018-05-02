package com.example.shahzademambaccus.finalyearprojectuser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

    public class GridAdapter extends BaseAdapter {

        private ArrayList<String> arrayOfImageView;
        private ArrayList<String> arrayOfWords;
        private Context context;
        private boolean isSymbol;

        public GridAdapter(ArrayList<String> listOfImageView, ArrayList<String> listOfWords, Context context, boolean isSymbol){
            //items used within this class
            arrayOfImageView = listOfImageView;
            arrayOfWords = listOfWords;
            this.context = context;
            this.isSymbol = isSymbol;
        }
        //size of image view to loop the class
        @Override
        public int getCount() {
            return arrayOfImageView.size();
        }
        //each image view to place an item inside
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
            //get the current view
            View row = convertView;
            ViewHolder holder;
            //if the view is empty
            if(row == null){
                //populate the view
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_image, parent, false);
                holder = new ViewHolder(row);
                row.setTag(holder);
            }else{
                //use previous view
                holder = (ViewHolder) row.getTag();
            }
            //populate the image view with either a sign or symbol
            if(isSymbol) {
                Picasso.with(context).load(arrayOfImageView.get(position)).placeholder(R.drawable.loadingwhite).into(holder.getImageView());
            }else{
                Glide.with(context).load(arrayOfImageView.get(position)).asGif().placeholder(R.drawable.loadingwhite).error(R.drawable.error).into(holder.getImageView());
            }
            //fill in the label
            holder.getTextView().setText(arrayOfWords.get(position));
            return row;
        }

    }