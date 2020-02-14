package com.igor.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.igor.app.network.EmployeeRepository;

import javax.inject.Inject;

public class MyViewModelFactory implements ViewModelProvider.Factory {

    private final EmployeeRepository mEmployeeRepository;

    @Inject public MyViewModelFactory(EmployeeRepository employeeRepository) {
        mEmployeeRepository = employeeRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull @Override public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MyViewModel.class)) {
            return (T) new MyViewModel(mEmployeeRepository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
