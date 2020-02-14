package com.igor.app.di.module;

import android.app.Application;
import android.content.Context;

import com.igor.app.sharedprefs.SharedPrefsManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Singleton @Provides public SharedPrefsManager providesSharedPrefsManager() {
        return new SharedPrefsManager();
    }

    @Provides public Context provideApplicationContext(Application application) {
        return application;
    }
}
