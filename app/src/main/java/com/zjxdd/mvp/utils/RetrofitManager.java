package com.zjxdd.mvp.utils;

import com.zjxdd.mvp.constants.StaticQuality;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:32
 */
public class RetrofitManager {
    private static RetrofitManager instance = null;
    private final Retrofit mRetrofit;

    private RetrofitManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(StaticQuality.BASE_URL)
                .build();
    }

    public static RetrofitManager getInstance() {
        synchronized (RetrofitManager.class) {
            if (instance == null) {
                instance = new RetrofitManager();
            }
        }
        return instance;
    }


    public <T> T createRs(Class<T> t) {
        return mRetrofit.create(t);
    }


}
