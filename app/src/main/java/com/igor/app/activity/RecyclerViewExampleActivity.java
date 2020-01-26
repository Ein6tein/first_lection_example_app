package com.igor.app.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.app.R;
import com.igor.app.adapter.RecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecyclerViewExampleActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private Unbinder mBind;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);
        mBind = ButterKnife.bind(this);
    }

    @Override protected void onStart() {
        super.onStart();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(new RecyclerViewAdapter(this));
    }

    @Override protected void onStop() {
        if (mBind != null) {
            mBind.unbind();
            mBind = null;
        }
        super.onStop();
    }
}
