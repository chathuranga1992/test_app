package com.example.sample_log_app.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sample_log_app.R;

import static com.example.sample_log_app.SampleLogApp.sAppContext;

public final class SharedPreferenceService {
    private static SharedPreferenceService mPreference;
    private SharedPreferences mPreferences;

    private SharedPreferenceService() {
        mPreferences = sAppContext.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferenceService getInstance() {
        if (mPreference == null) {
            mPreference = new SharedPreferenceService();
        }
        return mPreference;
    }

    public String getToken() {
        if (mPreferences.contains(Constants.ACCESS_TOKEN)) {
            return EncryptionUtils.decrypt(sAppContext, mPreferences.getString(Constants.ACCESS_TOKEN, ""));
        } else {
            return UIUtils.getTitle(R.string.sample_token);
        }
    }



    public void setToken(String token) {
        mPreferences.edit().putString(Constants.ACCESS_TOKEN,
                EncryptionUtils.encrypt(sAppContext,token)).apply();
    }

    public void setActId(String token) {
        mPreferences.edit().putString(Constants.ACT_ID,
               EncryptionUtils.encrypt(sAppContext,token)).apply();
    }

    public String getActId(String token) {
        if (mPreferences.contains(Constants.ACT_ID)) {
            return EncryptionUtils.decrypt(sAppContext, mPreferences.getString(Constants.ACT_ID, ""));
        } else {
            return null;
        }
    }

    public boolean isAuthenticated() {
        String token = "";
        if (mPreferences.contains(Constants.ACCESS_TOKEN)) {
            token = mPreferences.getString(Constants.ACCESS_TOKEN, "");
        } else {
            token = "";
        }
        return token.length() > 0;
    }


}
