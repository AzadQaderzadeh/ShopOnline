package com.example.shoponline.sign;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoponline.G;
import com.example.shoponline.R;
import com.example.shoponline.connect.AsyncTaskConnect;


public class ActivityUserSignIn extends AppCompatActivity {

    CheckBox showPass;
    public static String data = " ";
    EditText edtEmail;
    EditText edtPass;
    //test btnGo
    LinearLayout btnGo;
    TextView txtSignUp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.user_sign);
        super.onCreate(savedInstanceState);
        showPass = findViewById(R.id.showPass);

        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        txtSignUp = findViewById(R.id.txtSingUp);

        // test btnGo
        btnGo = findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTaskConnect("192.168.1.3/digikala/").execute();

                Toast.makeText(G.context,data,Toast.LENGTH_LONG).show();
               // Log.i("LOG",data);
            }
        });

        //

        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showPass.isChecked()) {
                    edtPass.setInputType(InputType.TYPE_CLASS_TEXT);

                } else {
                    edtPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);

                }

            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(G.context,ActivityUserSignUp.class));

//                Intent intent=new Intent(G.context,ActivityUserSignUp.class);
//                startActivityForResult(intent,0);
            }
        });
    }
}
