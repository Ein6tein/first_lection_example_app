package com.igor.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.app.R;
import com.igor.app.adapter.EmployeesAdapter;
import com.igor.app.viewmodel.MyViewModel;
import com.igor.app.viewmodel.MyViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ExampleFragment extends DaggerFragment {

    public static final String TAG = ExampleFragment.class.getSimpleName();

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    @Inject MyViewModelFactory mViewModelFactory;

    private CompositeDisposable mCompositeDisposable;
    private MyViewModel mViewModel;
    private EmployeesAdapter mAdapter;

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example, container, false);
        return view;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        mCompositeDisposable = new CompositeDisposable();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        mAdapter = new EmployeesAdapter(getActivity());

        mRecyclerView.setAdapter(mAdapter);

        mViewModel = new ViewModelProvider(this, mViewModelFactory).get(MyViewModel.class);

        mViewModel.employees().observe(getViewLifecycleOwner(), mAdapter::setEmployees);
    }

    @Override public void onStart() {
        super.onStart();
        mViewModel.getEmployees();
    }

    @Override public void onStop() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
        super.onStop();
    }

    private <T> void subscribe(Observable<T> observable, Consumer<T> onSuccess) {
        mCompositeDisposable.add(observable.subscribe(onSuccess));
    }

    private <T> void subscribe(LiveData<T> liveData, Observer<T> onSuccess) {
        liveData.observe(getViewLifecycleOwner(), onSuccess);
    }
}
