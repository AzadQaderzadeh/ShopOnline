package com.example.shoponline.product;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.shoponline.G;
import com.example.shoponline.MainActivity;
import com.example.shoponline.R;

public class ProductAmazing extends LinearLayout {

    LinearLayout linearLayout;
    public int id;
    public static ImageView pic;
    public static TextView title;
    public static TextView pPrice;
    public static TextView price;

    public ProductAmazing(Context context) {
        super(context);
        init(context);
    }

    public ProductAmazing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProductAmazing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.product_amazing, this, true);
        pic = view.findViewById(R.id.imgPic);
        title = view.findViewById(R.id.txttitle);
        pPrice = view.findViewById(R.id.txtPPrice);
        price = view.findViewById(R.id.txtPrice);
        linearLayout = view.findViewById(R.id.amazingLinear);


//        linearLayout.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(G.context, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("id",id);
//                G.context.startActivity(intent);
//            }
//        });
    }
}
