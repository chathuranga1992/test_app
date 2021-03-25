package com.example.sample_log_app.data.rest;



import com.example.sample_log_app.ui.forgetpassword.domain.ForgotPasswordResponse;
import com.example.sample_log_app.ui.login.domain.LoginResponse;
import com.example.sample_log_app.ui.register.domain.RegisterResponse;
import com.example.sample_log_app.util.Constants;
import com.example.sample_log_app.util.SharedPreferenceService;

import java.util.Map;

import retrofit2.Call;

public class RestClient implements RestClientService {

    private static final String GOOGLE_AUTH_URL = "https://www.googleapis.com/oauth2/v4/";
    private static final String GOOGLE_URL = "https://maps.googleapis.com/maps/api/";
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/";

    private static RestClient mRestClient;
    private WebApiRest mWebApiRest, mWebApiRestMul;



    public RestClient() {
        initRest();
    }

    public static RestClient getInstance() {
        if (mRestClient == null) {
            mRestClient = new RestClient();

        }
        return mRestClient;
    }

    private void initRest() {

       WebApiRequestInterceptor interceptor = new WebApiRequestInterceptor();

        String token = SharedPreferenceService.getInstance().getToken();
        interceptor.setAuth(token);
        mWebApiRest = WebApiRestFactory.newInstance(Constants.API_REST_HOST, interceptor.okHttpClient(false));
        mWebApiRestMul = WebApiRestFactory.newInstance(Constants.API_REST_HOST, interceptor.okHttpClient(true));
    }


    public void invalidate() {
        mRestClient = null;
    }


    @Override
    public void setToken(String mToken) {

    }

    @Override

    public Call<RegisterResponse> registerUser(String name , String email, String pw, String gender, String type, String age, int zip) {
        return mWebApiRest.registerUser(name, email, pw);
    }

  /*  public Call<RegisterResponse> registerUser(Map<String, String> param) {
        return mWebApiRest.registerUser(param);

    }*/

    @Override
    public Call<ForgotPasswordResponse> forgotPassword(String email) {
        return mWebApiRest.sentPasswordRequest(email);
    }



    @Override
    public Call<LoginResponse> loginUser(Map<String, String> param) {
        return mWebApiRest.loginUser(param);
    }

}
