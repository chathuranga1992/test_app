package com.example.sample_log_app.ui.login;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.sample_log_app.GenericEntity;
import com.example.sample_log_app.GenericRepositories;
import com.example.sample_log_app.SampleLogApp;
import com.example.sample_log_app.data.db.SampleAppDatabase;
import com.example.sample_log_app.data.rest.RestClient;
import com.example.sample_log_app.data.rest.domain.Status;
import com.example.sample_log_app.ui.login.domain.LoginResponse;
import com.example.sample_log_app.ui.login.domain.LoginStatus;
import com.example.sample_log_app.util.ExecutorServiceFactory;
import com.example.sample_log_app.util.L;
import com.example.sample_log_app.util.SharedPreferenceService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepositories extends GenericRepositories {
    RestClient mRestClient;
    LoginStatus mStatus = new LoginStatus();
    MutableLiveData<LoginStatus> mutableLiveData;
    private SampleAppDatabase mDatabase;
    private final ExecutorService mExecutorService;
    private SampleLogApp hometineApp;
    MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();


    public LoginRepositories(Context c) {
       mDatabase = SampleAppDatabase.getInstance(c);
        mExecutorService = ExecutorServiceFactory.getInstance();

    }

    public MutableLiveData<LoginStatus> doLogin(Map<String, String> map) {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                doLoginRequest(map);
            }
        });

        mutableLiveData = new MutableLiveData<>();
        return mutableLiveData;
    }

    private void doLoginRequest(Map<String, String> map) {
        mRestClient = RestClient.getInstance();
        isUpdating.postValue(true);
        mRestClient.loginUser(map).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                isUpdating.postValue(false);
                onSuccessResponse(response);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                isUpdating.postValue(false);
                onFailureResponse(t);
            }
        });
    }

    @Override
    public <T extends GenericEntity> void onSuccessResponse(Response<T> response) {
        if (response.code() == 200) {
            mStatus.setDone(true);
            mStatus.setMsg("");
            LoginResponse loginResponse = (LoginResponse) response.body();
            SharedPreferenceService.getInstance().setToken(loginResponse.getAccessToken());

            mExecutorService.execute(new Runnable() {
                @Override
                public void run() {
                    if (mDatabase.getUserDAO().getUserData().size() > 0) {
                        mDatabase.getUserDAO().deleteAll();
                    }
                    //User user = new User();
                    mDatabase.getUserDAO().addData(loginResponse.getUser());


                }
            });

            L.e("-----Response----->>", "**LOGIN**----------------------------\\n "
                    + loginResponse);
            L.d("-----ACCESS TOKEN----->>", "**LOGIN** " + loginResponse.getAccessToken());
        } else  {
            L.e("-----ERROR----->>", "**LOGIN** " + response.errorBody());
            mStatus.setDone(false);
            Gson gson = new Gson();
            try {
                Status status = gson.fromJson(response.errorBody().string(), Status.class);
                mStatus.setMsg(status.getMessage());
                L.i("-----ERROR----->>", "**LOGIN** " + status.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        mutableLiveData.postValue(mStatus);
    }

    @Override
    public void onFailureResponse(Throwable t) {
        t.printStackTrace();
    }




}
