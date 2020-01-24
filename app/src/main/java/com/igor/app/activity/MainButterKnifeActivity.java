package com.igor.app.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.igor.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.rl_activity_content) View mActivityContent;
    @BindView(R.id.tv_app_title) TextView mTitle;
    @BindView(R.id.et_login) EditText mLogin;
    @BindView(R.id.et_password) EditText mPassword;

    private Unbinder mUnbinder;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override protected void onStart() {
        super.onStart();
        mTitle.setText(R.string.butterknife_screen);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override protected void onStop() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        super.onStop();
    }

    @OnClick(R.id.btn_sign_in) void onSignInClicked() {
        String loginText = mLogin.getText().toString();
        String passwordText = mPassword.getText().toString();

        if (TextUtils.isEmpty(loginText)) {
            mLogin.setError("Should not be empty");
        } else {
            mLogin.setError(null);
        }
        if (TextUtils.isEmpty(passwordText)) {
            mPassword.setError("Should not be empty");
            return;
        } else {
            mPassword.setError(null);
        }

        String text = "Login: " + loginText + ", Password: " + passwordText;
        Snackbar.make(mActivityContent, text, Snackbar.LENGTH_LONG).show();
    }
}
