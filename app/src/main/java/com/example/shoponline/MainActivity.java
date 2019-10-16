package com.example.shoponline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
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
import com.example.shoponline.sign.ActivityUserSignIn;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private LinearLayout linearShowAllProduct;
    private LinearLayout userEmail;
    private SliderLayout sliderShow;
    private ArrayList<String> urlPict;
    private ArrayList<String> name;

    public static SharedPreferences preferences;


    private ImageView menu_app;
    private DrawerLayout drawerLayout;
    private ListView navigation_listView;
    private ListView product_listView;
    private ListView setting_listView;
    private TextView txtSignIn;
    private TextView txtBasketCount;
    private TextView txtExit;

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


        sliderShow = findViewById(R.id.slider);
        linearShowAllProduct = findViewById(R.id.linearShowAllProduct);
        menu_app = findViewById(R.id.menu_app);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigation_listView = findViewById(R.id.navigation_listView);
        product_listView = findViewById(R.id.product_listView);
        setting_listView = findViewById(R.id.setting_listView);
        userEmail = findViewById(R.id.user_Email);
        txtExit = findViewById(R.id.exit);
        txtSignIn = findViewById(R.id.txt_Sing_in);

        preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
        String email = preferences.getString("email", "ورود/ثبت نام");

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
            @SuppressLint("RtlHardcoded")
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
                preferences= PreferenceManager.getDefaultSharedPreferences(G.context);
                String email=preferences.getString("email","ورود/ثبت نام");
                String insertDone=preferences.getString("do","");

                if(email.equals("")){

                    if(insertDone.equals("")){
                        txtSignIn.setText("ورود/ثبت نام");
                    }else{

                        txtSignIn.setText(insertDone);
                    }

                }else{
                    txtSignIn.setText(email);
                }

                userEmail.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        //
        urlPict = new ArrayList<>();

        urlPict.add("https://sporteex.com/wp-content/uploads/2019/03/%DA%A9%D9%81%D8%B4-%D8%A7%D8%B3%DA%A9%DB%8C%DA%86%D8%B1%D8%B2-%D8%B7%D8%A8%DB%8C-%D8%A7%D9%88%D8%B1%D8%AC%DB%8C%D9%86%D8%A7%D9%84skechers-dual-lite-23.jpg");
        urlPict.add("https://sporteex.com/wp-content/uploads/2019/09/1.jpg");
        urlPict.add("http://cdn3.nflximg.net/images/3093/2043093.jpg");
        urlPict.add("https://sporteex.com/wp-content/uploads/2019/09/%DA%A9%D8%AA%D8%A7%D9%86%DB%8C-%D8%A7%D9%88%D8%B1%D8%AC%DB%8C%D9%86%D8%A7%D9%84-%D9%86%DB%8C%D9%88-%D8%A8%D8%A7%D9%84%D8%A7%D9%86%D8%B3-new-balance-lekkie-m%C4%99skie-buty-do-biegania-m680lb6.jpg");
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
