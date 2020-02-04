package com.igor.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.igor.app.R;
import com.igor.app.dialog.InfoDialog;

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
    private InfoDialog mDialog;

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
        mDialog = new InfoDialog.Builder()
                .setTitle("Some title")
                .setImage(R.drawable.smaller_android)
                .setButton1("Close", v -> mDialog.dismiss())
                .build();
        mDialog.show(getSupportFragmentManager(), InfoDialog.TAG);
    }

    @OnClick(R.id.btn_forgot_password) void onForgotPassword() {
        startActivity(new Intent(this, RecyclerViewExampleActivity.class));
    }
}
