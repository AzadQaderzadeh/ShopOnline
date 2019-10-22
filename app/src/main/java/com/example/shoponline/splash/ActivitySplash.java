package com.example.shoponline.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoponline.G;
import com.example.shoponline.MainActivity;
import com.example.shoponline.R;
import com.example.shoponline.product.AsyncTaskAmazingProduct;
import com.example.shoponline.product.AsyncTaskMostSellProduct;
import com.example.shoponline.product.AsyncTaskNewProduct;
import com.example.shoponline.productuniqe.AsyncTaskBanners;
import com.example.shoponline.productuniqe.AsyncTaskUniqueProduct;
import com.example.shoponline.timer.AsyncTaskTimer;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity {
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


//        preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
//        String email = preferences.getString("email", "ورود/ثبت نام");
//
//
//        if (email.equals("")) {
//
//        } else {
//            new AsyncTaskReadBasket("http://192.168.1.2/digikala/readbasket.php", email).execute();
//        }

        // ToDo Three Other ip config localhost
        new AsyncTaskTimer("http://192.168.1.3/digikala/androidtimer.php").execute();
        new AsyncTaskAmazingProduct("http://192.168.1.3/digikala/readamazing.php").execute();
        new AsyncTaskNewProduct("http://192.168.1.3/digikala/newproduct.php").execute();
        new AsyncTaskMostSellProduct("http://192.168.1.3/digikala/mostsell.php").execute();
        new AsyncTaskUniqueProduct("http://192.168.1.3/digikala/uniqe.php").execute();
        new AsyncTaskBanners("http://192.168.1.3/digikala/readbanner.php").execute();


        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(!MainActivity.timer.equals("") &  !MainActivity.amazingProduct.equals("")
                                &!MainActivity.newProduct.equals("") & !MainActivity.mostSellProduct.equals("")
                                &!MainActivity.uniqueProduct.equals("") & !MainActivity.banners.equals("")) {

                            // in shart paiin badan jaigozin mishavad


                            Log.i("LOG", MainActivity.amazingProduct);

                            Intent intent = new Intent(G.context, MainActivity.class);
                            startActivity(intent);

                            timer.cancel();
                            finish();
                        }


                    }
                });
            }
        }, 1, 1000);


    }
}