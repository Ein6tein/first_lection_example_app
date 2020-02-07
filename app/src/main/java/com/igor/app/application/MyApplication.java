package com.igor.app.application;

import android.app.Application;

import com.igor.app.di.component.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class MyApplication extends Application implements HasAndroidInjector {

    @Inject DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override public void onCreate() {
        super.onCreate();

        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
