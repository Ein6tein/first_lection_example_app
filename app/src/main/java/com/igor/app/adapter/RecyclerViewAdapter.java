package com.igor.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {

    private static final String[] DATA = {
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5"
    };

    private LayoutInflater mLayoutInflater;

    public RecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull @Override public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.row_spinner, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.mTextView.setText(DATA[position]);
    }

    @Override public int getItemCount() {
        return DATA.length;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_row_text) TextView mTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /*
        * Ye olde way:
        * TextView mTextView;
        *
        * public ItemViewHolder(@NonNull View itemView) {
        *     super(itemView);
        *     mTextView = itemView.findViewById(R.id.tv_row_text);
        * }
        */
    }
}
