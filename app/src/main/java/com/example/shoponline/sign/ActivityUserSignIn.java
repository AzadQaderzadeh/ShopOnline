package com.example.shoponline.sign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoponline.G;
import com.example.shoponline.MainActivity;
import com.example.shoponline.R;
import com.example.shoponline.connect.AsyncTaskConnect;

import java.util.Timer;
import java.util.TimerTask;


public class ActivityUserSignIn extends AppCompatActivity {

    CheckBox showPass;
    public static String data = "";
    EditText edtEmail;
    EditText edtPass;
    LinearLayout btnGo;
    TextView txtSignUp;
    SharedPreferences preferences;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String insertDone = bundle.getString("do");

            preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("do", insertDone);
            editor.commit();

            Intent intent = new Intent(G.context, MainActivity.class);
            startActivity(intent);

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.user_sign);
        super.onCreate(savedInstanceState);


        showPass = findViewById(R.id.showPass);
        btnGo = findViewById(R.id.btnGo);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        txtSignUp = findViewById(R.id.txtSingUp);


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();

                // ToDo One Other ip config localhost
                new AsyncTaskConnect("http://192.168.1.2/digikala/", email, pass).execute();


                final ProgressDialog dialog = new ProgressDialog(ActivityUserSignIn.this);
                dialog.setMessage("لطفا منتظر بمانید...");
                dialog.show();

                final Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!data.equals("")) {

                                    dialog.cancel();
                                    if (data.equals("not exist")) {
                                        Toast.makeText(G.context, "پست الکترونیکی یا کلمه عبور اشتباه است", Toast.LENGTH_SHORT).show();
                                        timer.cancel();

                                    } else {
                                        Intent intent = new Intent(G.context, MainActivity.class);
                                        intent.putExtra("email", data);
                                        setResult(RESULT_OK, intent);
                                        timer.cancel();
                                        finish();
                                    }

                                }

                            }
                        });
                    }
                }, 1, 1000);


            }
        });


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
                Intent intent = new Intent(G.context, ActivityUserSignUp.class);
                startActivityForResult(intent, 0);
            }
        });


    }
}
