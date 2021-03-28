package com.example.sample_log_app.data.rest;


import android.os.Build;


import com.example.sample_log_app.BuildConfig;
import com.example.sample_log_app.util.L;
import com.example.sample_log_app.util.SharedPreferenceService;
import com.example.sample_log_app.util.StringUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


public final class WebApiRequestInterceptor {

    private String mToken;

    public OkHttpClient okHttpClient(boolean isMultipart) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(interceptor)

                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder();
                        if (isMultipart) {
                            builder.addHeader("Content-Type", "multipart/form-data");
                        }

                        builder.addHeader("Accept", "application/json");
                        mToken = SharedPreferenceService.getInstance().getToken();
                        if (StringUtils.isNotEmpty(mToken)) {
                            builder.addHeader("Authorization", "bearer " + mToken);
                        }
                        Request newRequest = builder.build();
                        L.d("=====REQUEST======", "====BODY==== " + newRequest.body());
                        L.d("=====REQUEST======", "====URL==== " + newRequest.url());
                        L.d("=====REQUEST======", "====HEADER==== " + newRequest.headers());
                        return chain.proceed(newRequest);
                    }
                }).build();

        return okHttpClient;
    }

    public void setAuth(String auth) {
        L.d("------Token----->>", auth);
        this.mToken = auth;
    }

  private String getUserAgent() {
        return "Version:"+ BuildConfig.VERSION_NAME
                +",Version_Code:"+ BuildConfig.VERSION_CODE
                +",App_Id:"+BuildConfig.APPLICATION_ID
                +",OS_Version:"+ Build.VERSION.RELEASE
                +",OS:Android"
                +",Model:"+ Build.MODEL
                +",Manufacturer:"+ Build.MANUFACTURER;
    }
}
