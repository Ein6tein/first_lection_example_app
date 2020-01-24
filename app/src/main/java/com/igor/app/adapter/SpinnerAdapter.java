package com.igor.app.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igor.app.R;

public class SpinnerAdapter implements android.widget.SpinnerAdapter {

    public static final String[] DATA = {
            "Option 1",
            "Option 2",
            "Option 3"
    };

    private LayoutInflater mLayoutInflater;

    public SpinnerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.row_spinner, parent, false);
        }
        TextView text = view.findViewById(R.id.tv_row_text);

        text.setText(getItem(position));

        return view;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.row_spinner_selected, parent, false);
        }

        TextView text = view.findViewById(R.id.tv_row_text);

        text.setText(getItem(position));

        return view;
    }

    @Override public void registerDataSetObserver(DataSetObserver dataSetObserver) {
    }

    @Override public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
    }

    @Override public int getCount() {
        return DATA.length;
    }

    @Override public String getItem(int i) {
        return DATA[i];
    }

    @Override public long getItemId(int i) {
        return i + 1;
    }

    @Override public boolean hasStableIds() {
        return true;
    }

    @Override public int getItemViewType(int i) {
        return 1;
    }

    @Override public int getViewTypeCount() {
        return 1;
    }

    @Override public boolean isEmpty() {
        return false;
    }
}
