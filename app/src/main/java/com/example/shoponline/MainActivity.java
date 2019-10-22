package com.example.shoponline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.shoponline.menu.BuyMenuAdapter;
import com.example.shoponline.menu.BuyMenuListItem;
import com.example.shoponline.menu.ProductMenuAdapter;
import com.example.shoponline.menu.ProductMenuListItem;
import com.example.shoponline.menu.SettingMenuAdapter;
import com.example.shoponline.menu.SettingMenuListItem;
import com.example.shoponline.product.ProductAmazing;
import com.example.shoponline.productuniqe.ProductUnique;
import com.example.shoponline.sign.ActivityUserSignIn;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private LinearLayout linearShowAllProduct;
    private LinearLayout userEmail;
    private SliderLayout sliderShow;
    private ArrayList<String> urlPict;
    private ArrayList<String> name;
    ProductAmazing productAmazing;
    ProductUnique productUniqe;
    LinearLayout.LayoutParams layoutParams;
    LinearLayout linearProductAmazing;
    LinearLayout linearProductNew;
    LinearLayout linearProductMostSell;
    LinearLayout linearProductUniqe;

    ArrayList<String> bannersArray;
    ImageView banner1;
    ImageView banner2;
    ImageView banner3;
    ImageView banner4;
    ImageView banner5;
    ImageView banner6;


    public static SharedPreferences preferences;


    private ImageView menu_app;

    private DrawerLayout drawerLayout;
    private ListView navigation_listView;
    private ListView product_listView;
    private ListView setting_listView;
    private TextView txtSignIn;
    private TextView txtBasketCount;
    private TextView txtExit;

    public static String timer = "";
    public static String basket = "";
    public static String amazingProduct = "";
    public static String newProduct = "";
    public static String mostSellProduct = "";
    public static String uniqueProduct = "";
    public static String banners = "";

    TextView txtHour;
    TextView txtMin;
    TextView txtSec;

    public static Handler handler;

    int hourTimer = 0;
    int minTimer = 0;
    int secTimer = 0;

    //  private String [] menues = {"منو","ثبت نام","سبد خرید"};
    ArrayList<BuyMenuListItem> buyItems;
    ArrayList<ProductMenuListItem> productItems;
    ArrayList<SettingMenuListItem> settingItems;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String email = bundle.getString("email");
            txtSignIn.setText(email);

            preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", email);
            editor.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_menu);

        bannersArray = new ArrayList<String>();

        banner1 = findViewById(R.id.banner1);
        banner2 = findViewById(R.id.banner2);
        banner3 = findViewById(R.id.banner3);
        banner4 = findViewById(R.id.banner4);
        banner5 = findViewById(R.id.banner5);
        banner6 = findViewById(R.id.banner6);

        sliderShow = findViewById(R.id.slider);
        linearShowAllProduct = findViewById(R.id.linearShowAllProduct);
        linearProductNew = findViewById(R.id.linearProductNew);
        linearProductMostSell = findViewById(R.id.linearProductMostSell);
        linearProductUniqe = findViewById(R.id.linearProductUniqe);

        menu_app = findViewById(R.id.menu_app);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigation_listView = findViewById(R.id.navigation_listView);
        product_listView = findViewById(R.id.product_listView);
        setting_listView = findViewById(R.id.setting_listView);
        userEmail = findViewById(R.id.user_Email);
        txtExit = findViewById(R.id.exit);

        //Add Product
        linearProductAmazing = findViewById(R.id.linearProductAmazing);

        txtHour = findViewById(R.id.txt_hour);
        txtMin = findViewById(R.id.txt_min);
        txtSec = findViewById(R.id.txt_second);

        txtSignIn = findViewById(R.id.txt_Sing_in);

        preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
        String email = preferences.getString("email", "ورود/ثبت نام");

        String[] time = timer.split(":");

        final int hour = Integer.parseInt(time[0]);
        final int min = Integer.parseInt(time[1]);
        final int sec = Integer.parseInt(time[2]);

        hourTimer = hour;
        minTimer = min;
        secTimer = sec;

        txtHour.setText(time[0]);
        txtMin.setText(time[1]);
        txtSec.setText(time[2]);
        handler = new Handler();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (true) {
                        Thread.sleep(1000);
                        if (secTimer == 0) {

                            if (minTimer != 0) {

                                minTimer--;
                                secTimer = 59;


                            } else {
                                hourTimer--;
                                minTimer = 59;
                                secTimer = 59;
                            }

                        } else {
                            secTimer--;

                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (hourTimer < 10) {
                                    txtHour.setText("0" + hourTimer + "");

                                } else {
                                    txtHour.setText(hourTimer + "");
                                }


                                if (minTimer < 10) {
                                    txtMin.setText("0" + minTimer + "");
                                } else {
                                    txtMin.setText(minTimer + "");
                                }

                                if (secTimer < 10) {

                                    txtSec.setText("0" + secTimer + "");
                                } else {
                                    txtSec.setText(secTimer + "");
                                }

                            }
                        });


                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();


        showAmazingProduct();
        showNewProduct();
        ShowMostSellProduct();
        showUniqueProduct();
        showBanners();

        if (email.equals("")) {

            txtSignIn.setText("ورود/ثبت نام");

        } else {
            txtSignIn.setText(email);
        }


        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtSignIn.getText().toString().equals("ورود/ثبت نام")) {

                    Intent intent = new Intent(G.context, ActivityUserSignIn.class);
                    startActivityForResult(intent, 0);


                } else {

                    if (userEmail.getVisibility() == View.VISIBLE) {
                        userEmail.setVisibility(View.GONE);

                    } else {
                        userEmail.setVisibility(View.VISIBLE);
                    }

                }

            }
        });

        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("email", "");
                editor.putString("do", "");
                editor.commit();
                drawerLayout.closeDrawer(Gravity.RIGHT);


            }
        });
        //
        buyItems = new ArrayList<>();

        buyItems.add(new BuyMenuListItem(R.drawable.redhome, "خانه"));
        buyItems.add(new BuyMenuListItem(R.drawable.redmenu, "لیست دسته بندی محصولات"));
        buyItems.add(new BuyMenuListItem(R.drawable.redbasket, "سبد خرید"));
        //
        productItems = new ArrayList<>();

        productItems.add(new ProductMenuListItem("پیشنهاد ویژه دیجی کالا"));
        productItems.add(new ProductMenuListItem("پرفروش ترین ها"));
        productItems.add(new ProductMenuListItem("جدیدترین ها"));
        productItems.add(new ProductMenuListItem("پر بازدیدترین ها"));
        //
        settingItems = new ArrayList<>();

        settingItems.add(new SettingMenuListItem("تنظیمات"));
        settingItems.add(new SettingMenuListItem("سوالات متداول"));
        settingItems.add(new SettingMenuListItem("درباره ما"));
        //
        navigation_listView.setAdapter(new BuyMenuAdapter(MainActivity.this, R.layout.buy_menu_list, buyItems));
        //
        product_listView.setAdapter(new ProductMenuAdapter(MainActivity.this, R.layout.product_menu_list, productItems));
        //
        setting_listView.setAdapter(new SettingMenuAdapter(MainActivity.this, R.layout.setting_menu_list, settingItems));
        //

        menu_app.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("RtlHardcoded")
            @Override
            public void onClick(View v) {
                preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
                String email = preferences.getString("email", "ورود/ثبت نام");
                String insertDone = preferences.getString("do", "");

                if (email.equals("")) {

                    if (insertDone.equals("")) {
                        txtSignIn.setText("ورود/ثبت نام");
                    } else {

                        txtSignIn.setText(insertDone);
                    }

                } else {
                    txtSignIn.setText(email);
                }

                userEmail.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        //
        urlPict = new ArrayList<>();

        urlPict.add("http://192.168.1.3/digikala/img/1.jpg");
        urlPict.add("http://192.168.1.3/digikala/img/2.jpg");
        urlPict.add("http://192.168.1.3/digikala/img/3.jpg");
        urlPict.add("http://192.168.1.3/digikala/img/4.jpg");
        //
        name = new ArrayList<>();

        name.add("Apple");
        name.add("Phone");
        name.add("Laptop");
        name.add("Shirt");
        //
        for (int i = 0; i < urlPict.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.image(urlPict.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name.get(i));

            sliderShow.addSlider(textSliderView);

        }


        linearShowAllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(G.context, "Shop Online", Toast.LENGTH_LONG).show();
            }
        });

//        TextSliderView textSliderView = new TextSliderView(this);
//        textSliderView
//                .description("Game of Thrones")
//                .image("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
//
//        sliderShow.addSlider(textSliderView);
    }


    public void createProductAmazing(int id, String title, String pPrice, String price, String picUrl) {

        productAmazing = new ProductAmazing(G.context);
        productAmazing.id = id;
        productAmazing.title.setText(title);
        productAmazing.pPrice.setText(pPrice);
        productAmazing.price.setText(price);
        Picasso.with(G.context).load(picUrl).into(productAmazing.pic);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearProductAmazing.addView(productAmazing);

    }

    public void createProductNew(String title, String pPrice, String price, String picUrl) {

        productAmazing = new ProductAmazing(G.context);
        ProductAmazing.title.setText(title);
        ProductAmazing.pPrice.setText(pPrice);
        ProductAmazing.price.setText(price);
        Picasso.with(G.context).load(picUrl).into(ProductAmazing.pic);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearProductNew.addView(productAmazing);

    }

    public void createProductUnique(String title, String price, String picUrl) {

        ProductUnique productUnique = new ProductUnique(G.context);
        productUniqe.title.setText(title);
        productUniqe.price.setText(price);
        Picasso.with(G.context).load(picUrl).into(ProductUnique.pic);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearProductUniqe.addView(productUnique);

    }

    public void createProductMostSell(String title, String pPrice, String price, String picUrl) {

        productAmazing = new ProductAmazing(G.context);
        ProductAmazing.title.setText(title);
        ProductAmazing.pPrice.setText(pPrice);
        ProductAmazing.price.setText(price);
        Picasso.with(G.context).load(picUrl).into(ProductAmazing.pic);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearProductMostSell.addView(productAmazing);

    }

    public void showNewProduct() {

        try {
            JSONArray jsonArray = new JSONArray(newProduct);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt("id");
                String title = object.getString("title");
                String pPrice = String.valueOf(object.getInt("pprice") + " تومان");
                String price = String.valueOf(object.getInt("price") + " تومان");
                String pic = object.getString("pic");

                String picUrl = "http://192.168.1.3/digikala/img/" + pic;

                createProductNew(title, pPrice, price, picUrl);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void showUniqueProduct() {

        try {
            JSONArray jsonArray = new JSONArray(uniqueProduct);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt("id");
                String title = object.getString("title");
                String price = String.valueOf(object.getInt("price") + " تومان");
                String pic = object.getString("pic");

                String picUrl = "http://192.168.1.3/digikala/img/" + pic;

                createProductUnique(title, price, picUrl);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void showAmazingProduct() {


        try {
            JSONArray jsonArray = new JSONArray(amazingProduct);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt("id");
                String title = object.getString("title");
                String pPrice = String.valueOf(object.getInt("pprice") + " تومان");
                String price = String.valueOf(object.getInt("price") + " تومان");
                String pic = object.getString("pic");

                String picUrl = "http://192.168.1.3/digikala/img/" + pic;

                createProductAmazing(id, title, pPrice, price, picUrl);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void ShowMostSellProduct() {

        try {
            JSONArray jsonArray = new JSONArray(mostSellProduct);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt("id");
                String title = object.getString("title");
                String pPrice = String.valueOf(object.getInt("pprice") + " تومان");
                String price = String.valueOf(object.getInt("price") + " تومان");
                String pic = object.getString("pic");

                String picUrl = "http://192.168.1.3/digikala/img/" + pic;

                createProductMostSell(title, pPrice, price, picUrl);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void showBanners() {

        try {
            JSONArray jsonArray = new JSONArray(banners);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt("id");
                String pic = object.getString("pic");

                String picUrl = "http://192.168.1.3/digikala/img/" + pic;

                bannersArray.add(i, picUrl);

            }

            Picasso.with(G.context).load(bannersArray.get(0)).into(banner1);
            Picasso.with(G.context).load(bannersArray.get(1)).into(banner2);
            Picasso.with(G.context).load(bannersArray.get(2)).into(banner3);
            Picasso.with(G.context).load(bannersArray.get(3)).into(banner4);
            Picasso.with(G.context).load(bannersArray.get(4)).into(banner5);
            Picasso.with(G.context).load(bannersArray.get(5)).into(banner6);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_LONG).show();

    }
}
