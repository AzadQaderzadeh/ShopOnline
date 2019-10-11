package com.example.shoponline.connect;

import android.os.AsyncTask;

import com.example.shoponline.sign.ActivityUserSignIn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AsyncTaskConnect extends AsyncTask {


    public String link="";

    public AsyncTaskConnect (String link) {
        this.link=link;

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {

            URL url = new URL(link);
            URLConnection connection = url.openConnection();

            BufferedReader reader =new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder =new StringBuilder();

            String line =null;


            while ((line=reader.readLine())!=null){
                builder.append(line);

            }

            ActivityUserSignIn.data=builder.toString();

        }catch (Exception e){

        }
        return "";
    }
}
