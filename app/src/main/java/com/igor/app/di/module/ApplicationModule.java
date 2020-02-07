package com.igor.app.di.module;

import com.igor.app.sharedprefs.SharedPrefsManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Singleton @Provides public SharedPrefsManager providesSharedPrefsManager() {
        return new SharedPrefsManager();
    }
}
