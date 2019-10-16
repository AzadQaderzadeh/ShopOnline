package com.example.shoponline.sign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoponline.G;
import com.example.shoponline.R;
import com.example.shoponline.connect.AsyncTaskInsertUser;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityUserSignUp extends AppCompatActivity {

    public static String data = "";
    EditText edtEmail;
    EditText edtPass;
    EditText edtRePass;
    LinearLayout linearSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPass);
        edtRePass = (EditText) findViewById(R.id.edtRePass);

        linearSignUp = (LinearLayout) findViewById(R.id.LinearSignUp);
        linearSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                String rePass = edtRePass.getText().toString();

                if (pass.equals(rePass)) {

                    new AsyncTaskInsertUser("http://192.168.1.2/digikala/insertuser.php", email, pass).execute();

                    final ProgressDialog dialog = new ProgressDialog(ActivityUserSignUp.this);
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
                                        // Toast.makeText(G.context, data, Toast.LENGTH_SHORT).show();


                                        if (data.equals("not ok")) {
                                            Toast.makeText(G.context, "متاسفانه ارتباط با سرور برقرار نشد", Toast.LENGTH_SHORT).show();
                                            timer.cancel();

                                        } else if (data.equals("exist")) {
                                            Toast.makeText(G.context, "این ایمیل قبلا ثبت نام شده است", Toast.LENGTH_SHORT).show();
                                            timer.cancel();

                                        } else {
                                            Intent intent = new Intent(G.context, ActivityUserSignIn.class);
                                            intent.putExtra("do", data);
                                            setResult(RESULT_OK, intent);
                                            timer.cancel();
                                            finish();
                                        }


                                    }
                                }

                            });
                        }

                    }, 1, 1000);


                } else {
                    Toast.makeText(G.context, "کلمه عبور با تکرار آن مطابقت ندارد", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}