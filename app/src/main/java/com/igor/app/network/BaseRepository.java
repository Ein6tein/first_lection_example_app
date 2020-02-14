package com.igor.app.network;

import com.igor.app.BuildConfig;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseRepository<T> {

    private final ObservableTransformer schedulersTransformer = observable ->
            observable
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private Retrofit mRetrofit;
    private Class<T> mClass;

    public BaseRepository(Class<T> clazz, OkHttpClient unsafeOkHttpClient, OkHttpClient safeOkHttpClient) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://dummy.restapiexample.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(BuildConfig.DEBUG ? unsafeOkHttpClient : safeOkHttpClient)
                .build();

        mClass = clazz;
    }

    public T create() {
        return mRetrofit.create(mClass);
    }

    @SuppressWarnings("unchecked")
    public <R> ObservableTransformer<R, R> applySchedulers() {
        return schedulersTransformer;
    }
}