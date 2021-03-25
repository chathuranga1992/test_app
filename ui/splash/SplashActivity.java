package com.example.sample_log_app.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.sample_log_app.GenericActivity;
import com.example.sample_log_app.R;
import com.example.sample_log_app.ui.login.LoginActivity;


public class SplashActivity extends GenericActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        makeFullScreen();
        madeTransition();
    }

    private void madeTransition() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
/*                if (SharedPreferenceService.getInstance().isAuthenticated()) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                }*/

                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        }, 1500);
    }
}
