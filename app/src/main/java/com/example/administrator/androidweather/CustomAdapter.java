package com.example.administrator.androidweather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.androidweather.R;

import java.util.List;

/**
 * Created by administrator on 5/31/18.
 */

public class CustomAdapter extends ArrayAdapter<Weather> {
    private List<Weather> list;
    private Context context;
    private int resource;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Weather> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_listview,parent,false);
            holder.city = (TextView)convertView.findViewById(R.id.name);
            holder.temp = (TextView)convertView.findViewById(R.id.temp);
            holder.img_weather = (ImageView)convertView.findViewById(R.id.img_weather);
            convertView.setTag(holder);
        } else {
            holder =(Holder) convertView.getTag();
        }
        Weather weather = list.get(position);
        holder.city.setText(weather.getName());
        holder.temp.setText(String.valueOf(weather.getTemp()));
        Glide.with(context).load("http://openweathermap.org/img/w/" + weather.getIcon() + ".png").into(holder.img_weather);
        return convertView;
    }
    public class Holder {
        TextView city;
        TextView temp;
        ImageView img_weather;
    }
}
