package com.ecommerce.ecommerpizzas.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpTime {

    public static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10000L, TimeUnit.MILLISECONDS)
            .readTimeout(1000L, TimeUnit.MILLISECONDS)
            .build();

}
