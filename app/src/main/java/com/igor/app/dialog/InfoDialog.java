package com.igor.app.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.igor.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoDialog extends DialogFragment {

    public static final String TAG = InfoDialog.class.getSimpleName();

    @BindView(R.id.tv_title) TextView mTitleView;
    @BindView(R.id.tv_text) TextView mTextView;
    @BindView(R.id.iv_image) ImageView mImageView;
    @BindView(R.id.btn_first) Button mButton1View;
    @BindView(R.id.btn_second) Button mButton2View;

    private String mTitle;
    private String mText;
    private @DrawableRes int mImage;
    private String mButton1;
    private String mButton2;
    private View.OnClickListener mOnBtn1Click;
    private View.OnClickListener mOnBtn2Click;

    private InfoDialog() {}

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_info, container, false);
        return view;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        setupViews();
    }

    @Override public void onResume() {
        super.onResume();

        int dialogWidth = getResources().getDimensionPixelSize(R.dimen.info_dialog_width);
        if (getDialog() != null && getDialog().getWindow() != null) getDialog().getWindow().setLayout(dialogWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void setupViews() {
        mTitleView.setVisibility(TextUtils.isEmpty(mTitle) ? View.GONE : View.VISIBLE);
        mTextView.setVisibility(TextUtils.isEmpty(mText) ? View.GONE : View.VISIBLE);
        mImageView.setVisibility(mImage == 0 ? View.GONE : View.VISIBLE);
        mButton1View.setVisibility(TextUtils.isEmpty(mButton1) ? View.GONE : View.VISIBLE);
        mButton2View.setVisibility(TextUtils.isEmpty(mButton2) ? View.GONE : View.VISIBLE);

        mTitleView.setText(mTitle);
        mTextView.setText(mText);
        mImageView.setImageResource(mImage);
        mButton1View.setText(mButton1);
        mButton2View.setText(mButton2);
        mButton1View.setOnClickListener(mOnBtn1Click);
        mButton2View.setOnClickListener(mOnBtn2Click);
    }

    public static class Builder {

        private String mTitle;
        private String mText;
        private @DrawableRes int mImage;
        private String mButton1;
        private String mButton2;
        private View.OnClickListener mOnBtn1Click;
        private View.OnClickListener mOnBtn2Click;

        public Builder() {}

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setText(String text) {
            mText = text;
            return this;
        }

        public Builder setImage(int image) {
            mImage = image;
            return this;
        }

        public Builder setButton1(String button1, View.OnClickListener onClick) {
            mButton1 = button1;
            mOnBtn1Click = onClick;
            return this;
        }

        public Builder setButton2(String button2, View.OnClickListener onClick) {
            mButton2 = button2;
            mOnBtn2Click = onClick;
            return this;
        }

        public InfoDialog build() {
            InfoDialog dialog = new InfoDialog();

            dialog.mTitle = mTitle;
            dialog.mText = mText;
            dialog.mImage = mImage;
            dialog.mButton1 = mButton1;
            dialog.mButton2 = mButton2;
            dialog.mOnBtn1Click = mOnBtn1Click;
            dialog.mOnBtn2Click = mOnBtn2Click;

            return dialog;
        }
    }
}
