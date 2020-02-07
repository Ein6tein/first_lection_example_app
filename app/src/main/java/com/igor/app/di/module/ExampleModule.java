package com.igor.app.di.module;

import com.igor.app.fragment.ExampleFragment;
import com.igor.app.viewmodel.MyViewModelFactory;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ExampleModule {

    @ContributesAndroidInjector abstract ExampleFragment bindExampleFragment();

    @Provides static MyViewModelFactory providesMyViewModelFactory() {
        return new MyViewModelFactory();
    }
}
