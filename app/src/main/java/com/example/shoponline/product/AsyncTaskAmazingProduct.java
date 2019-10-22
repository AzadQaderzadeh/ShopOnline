package com.example.shoponline.product;

import android.os.AsyncTask;

import com.example.shoponline.MainActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AsyncTaskAmazingProduct extends AsyncTask {

    public String link = "";


    public AsyncTaskAmazingProduct(String link) {

        this.link = link;

    }

    @Override
    protected Object doInBackground(Object[] params) {

        try {

            URL url = new URL(link);
            URLConnection connection = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String line = null;

            while ((line = reader.readLine()) != null) {
                builder.append(line);

            }

            MainActivity.amazingProduct = builder.toString();

        } catch (Exception e) {

        }
        return "";
    }

}
