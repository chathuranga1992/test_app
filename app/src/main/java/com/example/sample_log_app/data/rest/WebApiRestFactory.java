package com.example.sample_log_app.data.rest;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class WebApiRestFactory {
    public static WebApiRest newInstance(String endpoint, OkHttpClient okHttpClient) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(WebApiRest.class);
    }

}
