package com.igor.app.di.builder;

import com.igor.app.activity.MainActivity;
import com.igor.app.activity.RecyclerViewExampleActivity;
import com.igor.app.di.module.ExampleModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector abstract MainActivity bindMainActivity();
    @ContributesAndroidInjector(modules = ExampleModule.class) abstract RecyclerViewExampleActivity bindRecyclerViewExampleActivity();
}
