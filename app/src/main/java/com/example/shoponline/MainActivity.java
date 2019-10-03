package com.example.shoponline;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private LinearLayout linearShowAllProduct;
    private SliderLayout sliderShow;
    private ArrayList<String> urlPict;
    private ArrayList<String> name;
    private String drawerMenuList;
    private ListView navigation_listView;
    private ImageView menu_app;
    private DrawerLayout drawerLayout;

  //  private String [] menues = {"منو","ثبت نام","سبد خرید"};
    ArrayList<BuyMenuListItem> items ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_menu);

        sliderShow = findViewById(R.id.slider);
        linearShowAllProduct = findViewById(R.id.linearShowAllProduct);
        menu_app = findViewById(R.id.menu_app);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigation_listView = findViewById(R.id.navigation_listView);

        items = new ArrayList<>();
        items.add(new BuyMenuListItem(R.drawable.basket,"خانه"));
        items.add(new BuyMenuListItem(R.drawable.search,"منو"));

        navigation_listView.setAdapter(new BuyMenuAdapter(MainActivity.this,R.layout.buy_menu_list,items));
        menu_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        urlPict = new ArrayList<>();
        name = new ArrayList<>();
        urlPict.add("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        urlPict.add("http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        urlPict.add("http://cdn3.nflximg.net/images/3093/2043093.jpg");
        urlPict.add("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        name.add("Apple");
        name.add("Phone");
        name.add("Laptop");
        name.add("Shirt");

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
