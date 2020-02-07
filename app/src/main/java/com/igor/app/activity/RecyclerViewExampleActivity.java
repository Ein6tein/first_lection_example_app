package com.igor.app.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.igor.app.R;
import com.igor.app.fragment.ExampleFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class RecyclerViewExampleActivity extends DaggerAppCompatActivity {

    private Unbinder mBind;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
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
