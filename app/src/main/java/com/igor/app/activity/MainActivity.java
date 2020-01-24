package com.igor.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.igor.app.R;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override protected void onStart() {
        super.onStart();
        findViewById(R.id.btn_sign_in).setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                classicWay();
            }
        });

        findViewById(R.id.btn_forgot_password).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainButterKnifeActivity.class);
            startActivity(intent);
        });
    }

    private void classicWay() {
        EditText login = findViewById(R.id.et_login);
        EditText password = findViewById(R.id.et_password);

        String loginText = login.getText().toString();
        String passwordText = password.getText().toString();

        if (TextUtils.isEmpty(loginText)) {
            login.setError("Should not be empty");
        } else {
            login.setError(null);
        }
        if (TextUtils.isEmpty(passwordText)) {
            password.setError("Should not be empty");
            return;
        } else {
             password.setError(null);
        }

        View rootView = findViewById(R.id.rl_activity_content);
        String text = "Login: " + loginText + ", Password: " + passwordText;
        Snackbar.make(rootView, text, Snackbar.LENGTH_LONG).show();
    }
}
