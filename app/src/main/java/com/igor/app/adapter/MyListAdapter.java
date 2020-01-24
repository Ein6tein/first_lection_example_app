package com.igor.app.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igor.app.R;

public class MyListAdapter implements android.widget.ListAdapter {

    private static final String[] DATA = {
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5"
    };

    private LayoutInflater mLayoutInflater;

    public MyListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override public void registerDataSetObserver(DataSetObserver observer) {
    }

    @Override public void unregisterDataSetObserver(DataSetObserver observer) {
    }

    @Override public int getCount() {
        return DATA.length;
    }

    @Override public String getItem(int position) {
        return DATA[position];
    }

    @Override public long getItemId(int position) {
        return position + 1;
    }

    @Override public boolean hasStableIds() {
        return true;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.row_spinner, parent, false);
        }

        TextView text = view.findViewById(R.id.tv_row_text);
        text.setText(getItem(position));

        return view;
    }

    @Override public int getItemViewType(int position) {
        return 1;
    }

    @Override public int getViewTypeCount() {
        return 1;
    }

    @Override public boolean isEmpty() {
        return false;
    }
}
