package com.example.shoponline.menu;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoponline.R;

import java.util.ArrayList;

public class BuyMenuAdapter extends ArrayAdapter {

    public int resourseId;
    public Activity context;
    public ArrayList<BuyMenuListItem> object;


    public BuyMenuAdapter(@NonNull Activity context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);

        this.resourseId = resource;
        this.context = context;
        this.object = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        view = this.context.getLayoutInflater().inflate(this.resourseId,null);

        ImageView img = view.findViewById(R.id.img_Menu_List);
        TextView txtTitle = view.findViewById(R.id.txt_Title_Menu);

        BuyMenuListItem buyMenuListItem = object.get(position);
        txtTitle.setText(buyMenuListItem.title);
        img.setImageResource(buyMenuListItem.img);
        return view;
    }
}
