package com.example.sample_log_app.ui.register;

import androidx.lifecycle.MutableLiveData;

import com.example.sample_log_app.GenericEntity;
import com.example.sample_log_app.GenericRepositories;
import com.example.sample_log_app.data.rest.RestClient;
import com.example.sample_log_app.data.rest.domain.Status;
import com.example.sample_log_app.ui.register.domain.RegisterResponse;
import com.example.sample_log_app.ui.register.domain.RegisterStatus;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class RegistrationRepositories extends GenericRepositories {

    RestClient mRestClient;
    RegisterStatus registerStatus = new RegisterStatus();
    MutableLiveData<RegisterStatus> mutableLiveData ;
    MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public MutableLiveData<RegisterStatus> doRegisterUser(String name , String email, String pw, String gender, String type, String age, int zip) {
        mutableLiveData  = new MutableLiveData<>();
        doRegisterUserRequest(name,email,gender,pw,type,age,zip);
        return mutableLiveData;
    }

    public MutableLiveData<Boolean> getIsUpdating() {
        isUpdating = new MutableLiveData<>();
        return isUpdating;
    }


    private void doRegisterUserRequest(String name , String email, String pw, String gender, String type, String age, int zip) {
        isUpdating.setValue(true);
        mRestClient = RestClient.getInstance();
        mRestClient.registerUser(name,email,gender,pw,type,age,zip).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                onSuccessResponse(response);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                onFailureResponse(t);
            }
        });
    }

    @Override
    public <T extends GenericEntity> void onSuccessResponse(Response<T> entity) {

        if (entity.code() == 200) {
            registerStatus.setDone(true);
            registerStatus.setMsg("");
        } else {
            Gson gson = new Gson();
            try {
                Status json = gson.fromJson(entity.errorBody().string(), Status.class);
                registerStatus.setMsg(json.getMessage());
                registerStatus.setDone(false);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        isUpdating.setValue(false);
        mutableLiveData.setValue(registerStatus);
    }

    @Override
    public void onFailureResponse(Throwable t) {
        RegisterStatus status = new RegisterStatus();
        status.setDone(false);
        status.setMsg(t.getMessage());
        isUpdating.setValue(false);
        mutableLiveData.setValue(status);
    }
}
