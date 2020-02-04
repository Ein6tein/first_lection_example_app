package com.igor.app.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.igor.app.R;
import com.igor.app.fragment.ExampleFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecyclerViewExampleActivity extends AppCompatActivity {

    private Unbinder mBind;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);
        mBind = ButterKnife.bind(this);
    }

    @Override protected void onStart() {
        super.onStart();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_fragment_container, new ExampleFragment(), ExampleFragment.TAG)
                .commit();
    }

    @Override protected void onStop() {
        if (mBind != null) {
            mBind.unbind();
            mBind = null;
        }
        super.onStop();
    }
}
