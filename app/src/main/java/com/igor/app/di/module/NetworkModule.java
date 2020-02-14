package com.igor.app.di.module;

import android.content.Context;

import com.igor.app.BuildConfig;
import com.igor.app.api.EmployeeApi;
import com.igor.app.network.EmployeeRepository;
import com.igor.app.network.EmployeeRepositoryImpl;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class NetworkModule {

    @Provides @Singleton public Interceptor providesLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level level = BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
        loggingInterceptor.level(level);
        return loggingInterceptor;
    }

    @Provides @Singleton @Named("unsafe-http-client") public OkHttpClient unsafeOkHttpClient(Interceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(31, TimeUnit.SECONDS)
                .readTimeout(31, TimeUnit.SECONDS)
                .writeTimeout(31, TimeUnit.SECONDS)
                .build();
    }

    @Provides @Singleton @Named("safe-http-client") public OkHttpClient safeOkHttpClient(Interceptor loggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(31, TimeUnit.SECONDS)
                .readTimeout(31, TimeUnit.SECONDS)
                .writeTimeout(31, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

    @Provides @Singleton public Cache providesOkClientCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "http-responses");
        return new Cache(httpCacheDirectory, 10 * 1024 * 1024);
    }

    @Provides public EmployeeRepository providesEmployeeRepository(@Named("unsafe-http-client") OkHttpClient unsafe, @Named("safe-http-client") OkHttpClient safe) {
        return new EmployeeRepositoryImpl(EmployeeApi.class, unsafe, safe);
    }
}
