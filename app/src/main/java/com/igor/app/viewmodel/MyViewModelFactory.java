package com.igor.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

public class MyViewModelFactory implements ViewModelProvider.Factory {

    @Inject public MyViewModelFactory() {}

    @SuppressWarnings("unchecked")
    @NonNull @Override public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MyViewModel.class)) {
            return (T) new MyViewModel();
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
