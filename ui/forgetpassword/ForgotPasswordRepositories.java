package com.example.sample_log_app.ui.forgetpassword;

import androidx.lifecycle.MutableLiveData;

import com.example.sample_log_app.GenericEntity;
import com.example.sample_log_app.GenericRepositories;
import com.example.sample_log_app.data.rest.RestClient;
import com.example.sample_log_app.data.rest.domain.Status;
import com.example.sample_log_app.ui.forgetpassword.domain.ForgotPasswordResponse;
import com.example.sample_log_app.ui.forgetpassword.domain.PasswordStatus;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordRepositories extends GenericRepositories {

    RestClient mRestClient;
    PasswordStatus passwordStatus = new PasswordStatus();
    MutableLiveData<PasswordStatus> mutableLiveData;
    MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();;

    public MutableLiveData<PasswordStatus> doForgotPassword(String email) {
        mutableLiveData = new MutableLiveData<>();
        sendPasswordRequest(email);
        return mutableLiveData;
    }

    public MutableLiveData<Boolean> getIsUpdating() {
        isUpdating = new MutableLiveData<>();
        return isUpdating;
    }

    private void sendPasswordRequest(String email) {
        isUpdating.postValue(true);
        mRestClient = RestClient.getInstance();
        mRestClient.forgotPassword(email).enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                onSuccessResponse(response);
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                onFailureResponse(t);
            }
        });

    }

    @Override
    public <T extends GenericEntity> void onSuccessResponse(Response<T> entity) {
        PasswordStatus status = new PasswordStatus();
        if (entity.code() == 200) {
            status.setDone(true);
            status.setMsg(status.getMsg());
        }else {
            Gson gson = new Gson();
            try {
                Status json = gson.fromJson(entity.errorBody().string(), Status.class);
                status.setMsg(json.getMessage());
                status.setDone(false);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        isUpdating.setValue(false);
        mutableLiveData.setValue(status);
    }

    @Override
    public void onFailureResponse(Throwable t) {
        PasswordStatus status = new PasswordStatus();
        status.setDone(false);
        status.setMsg(t.getMessage());
        isUpdating.setValue(false);
        mutableLiveData.setValue(status);
    }
}
