package com.example.sample_log_app.ui.forgetpassword;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample_log_app.ui.forgetpassword.domain.PasswordStatus;
import com.example.sample_log_app.util.StringUtils;
import com.google.gson.JsonObject;

public class ForgotPasswordViewModel extends ViewModel {

    private ForgotPasswordRepositories mRepositories;

    void init() {
        mRepositories = new ForgotPasswordRepositories();
    }

    boolean validateUserInput(String email) {
        return StringUtils.isValidEmail(email);
    }


    MutableLiveData<PasswordStatus> sendPasswordRequest(String email) {
        JsonObject object = new JsonObject();
        object.addProperty("email",email);
        return mRepositories.doForgotPassword(object.toString());
    }

    MutableLiveData<Boolean> isUpdating() {
        return mRepositories.getIsUpdating();
    }
}
