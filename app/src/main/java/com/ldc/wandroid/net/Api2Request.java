package com.ldc.wandroid.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api2Request {

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;


    public static Api2Request getInstance() {
        return new Api2Request();
    }

    public static Api2Request getInstance(final String base_url) {
        return new Api2Request(base_url);
    }


    private Api2Request() {
        getRetrofit(API.base_url);
    }

    private Api2Request(final String base_url) {
        getRetrofit(base_url);
    }


    public <T> T createServer(Class<T> tClass) {
        return retrofit.create(tClass);
    }


    public Retrofit getRetrofit(final String base_url) {
        retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(base_url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public OkHttpClient getOkHttpClient() {

        if (null == okHttpClient) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30000, TimeUnit.SECONDS)
                    .readTimeout(35000, TimeUnit.SECONDS)
                    .writeTimeout(35000, TimeUnit.SECONDS)
                    .build();
        }

        return okHttpClient;
    }
}
