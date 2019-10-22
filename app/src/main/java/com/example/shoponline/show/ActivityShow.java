package com.example.shoponline.show;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.shoponline.R;
import com.google.android.material.appbar.AppBarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityShow extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {
    SliderLayout sliderShow;
    ArrayList<String> urlPics;
    ArrayList<String> names;
    AppBarLayout appBarLayout;
    ImageView wBasket;
    ImageView wSearch;
    ImageView wHambergure;
    LinearLayout linearPoint;
    LinearLayout linearAddToBasket;
    LinearLayout.LayoutParams layoutParams;

    public static SharedPreferences preferences;

    public int hourTimer;
    public int minTimer;
    public int secTimer;
    public TextView txtHour;
    public TextView txtMin;
    public TextView txtSec;
    public TextView txtMore;

    TextView txtTitle;
    TextView txtMark;
    TextView title2;
    TextView txtDesc;
    TextView txtPrice;
    TextView txtColor;
    TextView txtGaurantee;

    LinearLayout btnFanni;
    LinearLayout btnComment;


    RatingBar ratingBar;
    public int pointLength = 0;

    public static Handler handler;

    ArrayList<String> pointTitle;
    ArrayList<String> hardarray;


    String title = "";
    String price = "";
    String desc = "";
    String color = "";
    String gaurantee = "";
    String email = "";
    String id = "";


    public static String timer = "";

    Toolbar toolbar;
    public static String data = "";
    public static String info = "";
    public static String basket = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        linearAddToBasket = (LinearLayout) findViewById(R.id.linearAddBasket);

//        preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
//        email=preferences.getString("email","ورود/ثبت نام");
//
//        // Toast.makeText(G.context,email,Toast.LENGTH_SHORT).show();
//
//
//
//
//
//
//
//
//
//
//        Bundle bundle=getIntent().getExtras();
//        id= bundle.getString("id");
//
//        hardarray=new ArrayList<>();


        // Toast.makeText(G.context,id,Toast.LENGTH_SHORT).show();

        // Log.i("LOG",info);
        // Toast.makeText(G.context,info,Toast.LENGTH_LONG).show();

        txtTitle = findViewById(R.id.showTitle);
        btnComment = findViewById(R.id.btncomment);
        btnFanni = findViewById(R.id.btnfanni);
        txtMark = findViewById(R.id.txtMark);
        title2 = findViewById(R.id.showTitle2);
        txtDesc = findViewById(R.id.showDesc);
        txtPrice = findViewById(R.id.showPrice);
        txtColor = findViewById(R.id.showColor);
        txtGaurantee = findViewById(R.id.showGaurantee);
        txtMore = findViewById(R.id.txtMore);
        pointTitle = new ArrayList<>();
        linearPoint = findViewById(R.id.linearPoint);
        ratingBar = findViewById(R.id.ratingBar);


//
//        btnFanni.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                new AsyncTaskGetProperties("http://192.168.1.3/digikala/fanni.php",id).execute();
//                final ProgressDialog dialog=new ProgressDialog(ActivityShow.this);
//                dialog.setMessage("لطفا منتظر بمانید...");
//                dialog.show();
//
//                final Timer timer=new Timer();
//                timer.scheduleAtFixedRate(new TimerTask() {
//                    @Override
//                    public void run() {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                if(!ActivityProperties.data.equals("")){
//
//
//                                    dialog.cancel();
//                                    Intent intent=new Intent(G.context,ActivityProperties.class);
//                                    startActivity(intent);
//                                    timer.cancel();
//
//
//
//                                }
//
//                            }
//                        });
//                    }
//                },1,1000);
//            }
//        });
//
//
//        btnComment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new AsyncTaskComment("http://192.168.1.3/digikala/comment.php",id).execute();
//                final ProgressDialog dialog=new ProgressDialog(ActivityShow.this);
//                dialog.setMessage("لطفا منتظر بمانید...");
//                dialog.show();
//
//                final Timer timer=new Timer();
//                timer.scheduleAtFixedRate(new TimerTask() {
//                    @Override
//                    public void run() {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                if(!ActivityComment.data.equals("")){
//
//
//                                    dialog.cancel();
//                                    Intent intent=new Intent(G.context,ActivityComment.class);
//                                    intent.putExtra("id",id);
//                                    startActivity(intent);
//                                    //Toast.makeText(G.context,ActivityComment.data,Toast.LENGTH_SHORT).show();
//                                    timer.cancel();
//
//
//
//                                }
//
//                            }
//                        });
//                    }
//                },1,1000);
//            }
//        });
//


        txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtMore.getText().equals("ادامه مطلب")) {

                    txtDesc.setLayoutParams
                            (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    txtMore.setText("بستن");

                } else {
                    txtDesc.setLayoutParams
                            (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 400));
                    txtMore.setText("ادامه مطلب");
                }
            }
        });


        urlPics = new ArrayList<>();
        names = new ArrayList<>();

        names.add("phone");
        names.add("shirt");
        names.add("laptop");
        names.add("coolpad");

        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);
                String pic = object.getString("pic");
                String picUrl = "http://192.168.1.3/digikala/img/" + pic;

                urlPics.add(picUrl);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray jsonArray = new JSONArray(info);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);

                title = object.getString("title");
                price = String.valueOf(object.getInt("price") + " تومان");
                desc = object.getString("intro");
                color = object.getString("color");
                gaurantee = object.getString("gaurantee");

                JSONArray hardJson = object.getJSONArray("hard");

                for (int k = 0; k < hardJson.length(); k++) {
                    hardarray.add(hardJson.getString(k));

                }


                for (int k = 0; k < hardarray.size(); k++) {

                    Log.i("LOG", hardarray.get(k));
                }


                float rating = Float.valueOf(object.getString("rating"));
                ratingBar.setRating(rating);

                txtMark.setText(rating + " از 5");


                JSONArray points = object.getJSONArray("point");


                for (int j = 0; j < points.length(); j++) {
                    pointLength = points.length();
                    String title2 = (String) points.get(j);


//                    CustomPoint customPoint=new CustomPoint(G.context);
//                    customPoint.txtPoint.setText(title2);
//                    //Log.i("LOG",hardarray.get(j)+"");
//                    customPoint.progressBar.setProgress(Integer.parseInt(hardarray.get(j).trim()));
//                    layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//                    linearPoint.addView(customPoint);

                }

                JSONArray rate = object.getJSONArray("comment");
               /* for(int k=0;k<rate.length();k++){
                    JSONObject object2=rate.getJSONObject(k);
                    String one=object2.getString("1");
                    String two=object2.getString("2");
                    String three=object2.getString("3");
                    String four=object2.getString("4");
                    String five=object2.getString("5");
                    Log.i("LOG",one+"/"+two+"/"+three+"/"+four+"/"+five);

                }*/


                // Log.i("LOG",title+" "+price+" "+desc);

                txtTitle.setText(title);
                title2.setText(title);
                txtPrice.setText(price);
                txtDesc.setText(Html.fromHtml(desc));
                txtGaurantee.setText(gaurantee);
                txtColor.setText(color);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


//
//        linearAddToBasket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(email.equals("")){
//                    Toast.makeText(G.context,"لطفا وارد حساب خود شوید",Toast.LENGTH_SHORT).show();
//
//                }else{
//                    BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(ActivityShow.this);
//                    View parent=getLayoutInflater().inflate(R.layout.dialog,null);
//
//                    final TextView txtProductTitle=(TextView)parent.findViewById(R.id.txtProductTitle);
//                    final TextView txtProductColor=(TextView)parent.findViewById(R.id.txtProductColor);
//                    final TextView txtProductGaurantee=(TextView)parent.findViewById(R.id.txtProductGaurantee);
//                    final TextView txtProductPrice=(TextView)parent.findViewById(R.id.txtProductPrice);
//                    final Button btnAddBasket=(Button)parent.findViewById(R.id.btnAddBasket);
//
//                    txtProductTitle.setText(title);
//                    txtProductColor.setText(color);
//                    txtProductGaurantee.setText(gaurantee);
//                    txtProductPrice.setText(price);
//
//                    btnAddBasket.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//
//                            new AsyncTaskAddToBasket("http://192.168.1.3/digikala/addbasket.php",email,id,gaurantee,color).execute();
//
//                            final Timer timer=new Timer();
//                            timer.scheduleAtFixedRate(new TimerTask() {
//                                @Override
//                                public void run() {
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//
//                                            if(!ActivityShow.basket.equals("") )  {
//
//
//                                                Toast.makeText(G.context,basket,Toast.LENGTH_LONG).show();
//                                                timer.cancel();
//
//                                            }
//
//
//
//
//
//                                        }
//                                    });
//                                }
//                            },1,1000);
//
//                        }
//                    });
//
//                    bottomSheetDialog.setContentView(parent);
//                    BottomSheetBehavior bottomSheetBehavior=BottomSheetBehavior.from((View)parent.getParent());
//                    bottomSheetBehavior.setPeekHeight(
//
//                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,400,getResources().getDisplayMetrics())
//
//                    );
//
//                    bottomSheetDialog.show();
//                }
//            }
//        });


        sliderShow = findViewById(R.id.sliderShow);
        txtHour = findViewById(R.id.txthour);
        txtMin = findViewById(R.id.txtmin);
        txtSec = findViewById(R.id.txtsecond);


        for (int i = 0; i < urlPics.size(); i++) {

            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.image(urlPics.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", names.get(i));


            sliderShow.addSlider(textSliderView);

        }

//
//        String[] time=timer.split(":");
//
//        final int hour= Integer.parseInt(time[0]);
//        final int min= Integer.parseInt(time[1]);
//        final  int sec= Integer.parseInt(time[2]);
//
//
//        hourTimer=hour;
//        minTimer=min;
//        secTimer=sec;
//
//
//        txtHour.setText(time[0]);
//        txtMin.setText(time[1]);
//        txtSec.setText(time[2]);
//        handler=new Handler();
//
//        Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//
//                    while(true) {
//                        Thread.sleep(1000);
//                        if (secTimer == 0) {
//
//                            if (minTimer != 0) {
//
//                                minTimer--;
//                                secTimer = 59;
//
//
//                            } else {
//                                hourTimer--;
//                                minTimer = 59;
//                                secTimer = 59;
//                            }
//
//                        } else {
//                            secTimer--;
//
//                        }
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                if(hourTimer<10){
//                                    txtHour.setText("0"+hourTimer + "");
//
//                                }else{
//                                    txtHour.setText(hourTimer + "");
//                                }
//
//
//                                if(minTimer<10){
//                                    txtMin.setText("0"+minTimer + "");
//                                }else{
//                                    txtMin.setText(minTimer + "");
//                                }
//
//                                if(secTimer<10){
//
//                                    txtSec.setText("0"+secTimer + "");
//                                }else{
//                                    txtSec.setText(secTimer + "");
//                                }
//
//                            }
//                        });
//
//
//                    }
//
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//        thread.start();


        appBarLayout = findViewById(R.id.app_bar_layout);
        wBasket = findViewById(R.id.wbasket);
        wHambergure = findViewById(R.id.whambergurMenu);
        wSearch = findViewById(R.id.wsearch);

        toolbar = findViewById(R.id.trancToolbar);

        //  کاربرد این متود (addOnOffsetChangedListener) برای این است
        //که وقتی کاربر صفحه رو در اکتیویتی شوو اسکرول میکنه ایکون های ما از رنگ خاکستری به سفید تغییر پیدا کند
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                //Log.i("LOG", verticalOffset + "");

                int scroll = -(verticalOffset);
                if (scroll >= 760) {
                    wBasket.setImageResource(R.drawable.wbasket);
                    wHambergure.setImageResource(R.drawable.whambergur);
                    wSearch.setImageResource(R.drawable.wsearch);

                } else {
                    wBasket.setImageResource(R.drawable.bbasket);
                    wHambergure.setImageResource(R.drawable.bhambergur);
                    wSearch.setImageResource(R.drawable.bsearch);
                }

            }
        });


    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}

