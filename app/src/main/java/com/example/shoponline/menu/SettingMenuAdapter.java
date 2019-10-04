package com.example.shoponline.menu;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoponline.R;

import java.util.ArrayList;

public class SettingMenuAdapter extends ArrayAdapter {


    public int resourseId;
    public Activity context;
    public ArrayList<SettingMenuListItem> object;


    public SettingMenuAdapter(@NonNull Activity context, int resource, @NonNull ArrayList objects) {
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


        TextView txtTitle = view.findViewById(R.id.setting_Title_Menu);

        SettingMenuListItem settingMenuListItem = object.get(position);
        txtTitle.setText(settingMenuListItem.title);

        return view;
    }
}
