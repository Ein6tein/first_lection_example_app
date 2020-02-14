package com.igor.app.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.igor.app.R;
import com.igor.app.model.Employee;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.ItemViewHolder> {

    private final LayoutInflater mInflater;
    private final Context mContext;

    private List<Employee> mEmployees;

    public EmployeesAdapter(Context context) {
        mEmployees = Collections.emptyList();

        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull @Override public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(mInflater.inflate(R.layout.row_employee, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Employee employee = mEmployees.get(position);

        StringBuilder builder = new StringBuilder();
        String age = "Age: ";
        String salary = "Yearly Salary: ";
        builder.append(age).append(employee.getAge())
                .append("\n")
                .append(salary).append(NumberFormat.getCurrencyInstance(Locale.US).format(employee.getSalary()));

        SpannableString span = new SpannableString(builder);
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, android.R.color.black)), 0, age.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, android.R.color.black)), builder.indexOf(salary),
                (builder.indexOf(salary) + salary.length()), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span.setSpan(new StyleSpan(Typeface.BOLD), 0, age.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span.setSpan(new StyleSpan(Typeface.BOLD), builder.indexOf(salary), (builder.indexOf(salary) + salary.length()), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        holder.mName.setText(employee.getName());
        holder.mDetails.setText(span);
        Glide.with(mContext).load(employee.getImage()).into(holder.mPhoto);
    }

    @Override public int getItemCount() {
        return mEmployees.size();
    }

    public void setEmployees(List<Employee> employees) {
        mEmployees = new ArrayList<>(employees);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name) TextView mName;
        @BindView(R.id.tv_details) TextView mDetails;
        @BindView(R.id.iv_photo) ImageView mPhoto;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
