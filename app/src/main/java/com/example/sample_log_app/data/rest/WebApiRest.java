package com.example.sample_log_app.data.rest;


import com.example.sample_log_app.ui.forgetpassword.domain.ForgotPasswordResponse;
import com.example.sample_log_app.ui.login.domain.LoginResponse;
import com.example.sample_log_app.ui.register.domain.RegisterResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WebApiRest {



    @POST("users/forgot-password")
    Call<ForgotPasswordResponse> sentPasswordRequest(@Body String email);

    @FormUrlEncoded
    @POST("users")
    Call<RegisterResponse> registerUser(String s, @Field("name") String name, @Field("email") String email);

    @FormUrlEncoded
    @POST("users/login")
    Call<LoginResponse> loginUser(@FieldMap Map<String, String> param);



}
