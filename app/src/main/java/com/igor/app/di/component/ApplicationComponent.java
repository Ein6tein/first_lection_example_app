package com.igor.app.di.component;

import android.app.Application;

import com.igor.app.application.MyApplication;
import com.igor.app.di.builder.ActivityBuilder;
import com.igor.app.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ActivityBuilder.class
})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(MyApplication application);

    @Component.Builder interface Builder {

        @BindsInstance Builder application(Application application);

        ApplicationComponent build();
    }
}
