package com.example.sample_log_app.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sample_log_app.GenericActivity;
import com.example.sample_log_app.R;
import com.example.sample_log_app.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashActivity extends GenericActivity {
    @BindView(R.id.iv_loading)
    ImageView myImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        makeFullScreen();
        madeTransition();

        Glide.with(this)
                .load(R.drawable.ic_loading)
                .centerCrop()
                .into(myImageView);
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
