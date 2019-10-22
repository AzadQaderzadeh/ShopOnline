package com.example.shoponline.productuniqe;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shoponline.R;

public class ProductUnique extends LinearLayout {

    public static ImageView pic;
    public static TextView title;
    public static TextView price;

    public ProductUnique(Context context) {
        super(context);
        init(context);
    }

    public ProductUnique(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProductUnique(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.product_unique, this, true);

        pic = view.findViewById(R.id.imgUnique);
        title = view.findViewById(R.id.titleUnique);
        price = view.findViewById(R.id.priceUnique);

    }
}
