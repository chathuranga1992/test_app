package com.example.sample_log_app.ui.login;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.sample_log_app.data.db.SampleAppDatabase;
import com.example.sample_log_app.ui.login.domain.LoginStatus;
import com.example.sample_log_app.util.L;
import com.example.sample_log_app.util.SharedPreferenceService;
import com.example.sample_log_app.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginViewModel extends ViewModel {
    private LoginRepositories mRepositories;
    private SampleAppDatabase mDatabase;

    void init(Context c) {
        mRepositories = new LoginRepositories(c);
    }

    MutableLiveData<LoginStatus> doLogin(String email, String pw, String type, String subToken) {

        Map<String, String> parameters = new HashMap<>();
        if (StringUtils.isNotEmpty(email)) {
            parameters.put("email", email);
            L.e("email",email);
        }
        if (StringUtils.isNotEmpty(pw)) {
            parameters.put("password", pw);
            L.e("pw",pw);
        }
        if (StringUtils.isNotEmpty(type)) {
            parameters.put("loginType", type);
            L.e("loginType",type);
        }
        if (StringUtils.isNotEmpty(subToken)){

            parameters.put("subjectToken", subToken);
            L.e("subToken",subToken);
        }


        return mRepositories.doLogin(parameters);
    }



    MutableLiveData<Boolean> isUpdating() {
        return mRepositories.isUpdating;
    }
}
