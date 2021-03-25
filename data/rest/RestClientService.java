package com.example.sample_log_app.data.rest;


import com.example.sample_log_app.ui.forgetpassword.domain.ForgotPasswordResponse;
import com.example.sample_log_app.ui.login.domain.LoginResponse;
import com.example.sample_log_app.ui.register.domain.RegisterResponse;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

public interface RestClientService {
    void setToken(String mToken);

    Call<RegisterResponse> registerUser(String name , String email, String pw, String gender, String type, String age, int zip);
    // Call<RegisterResponse> registerUser(Map<String, String> param);
    Call<ForgotPasswordResponse> forgotPassword(String email);
    Call<LoginResponse> loginUser(Map<String, String> param);
}
