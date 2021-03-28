package com.example.sample_log_app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.transition.Slide;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.sample_log_app.util.L;


public abstract class GenericActivity extends FragmentActivity {


    public ProgressDialog dialog;
    private static final int REQUEST_PERMISSION_SETTING = 2;
    private LoadingDialog mLoadingDialog;

    //private SharedPreferenceService mPreferenceService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        //mPreferenceService = SharedPreferenceService.getInstance();
        setupWindowAnimations();
        setupWindowExitAnimations();

        mLoadingDialog = new LoadingDialog(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBg));
        }
    }

    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
    }

    private void setupWindowExitAnimations() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
    }
    public void showLoadingIndicator() {

        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            mLoadingDialog.show();
            mLoadingDialog.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            mLoadingDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        }

    }
    public void showInfoMsg(String msg, boolean enable) {
        InfoDialog dialog = new InfoDialog(this, R.style.Theme_Dialog)
                .setMessage(msg)
                .setTitle("Info", enable)
                .setOkButtonText("Ok")
                .setOkButtonClickListener(new InfoDialog.OnInfoClickListener() {
                    @Override
                    public void onClick(InfoDialog var1) {
                        var1.dismiss();
                    }
                });
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        dialog.show();
        dialog.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


    }

    public void dismissLoadingIndicator() {

        if (mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    public void startNewActivity(Intent intent) {
        startActivity(intent);
    }

    public void startNewActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void hideKeyboardFrom(View view) {
        try {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            View decorView = getWindow().getDecorView();

            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);

            InputMethodManager imm = (InputMethodManager) getApplicationContext()
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public void showSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
    }

    class NetworkConnectionReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            L.d("-------->>>", "onReceive");
            if (intent.hasExtra("data")) {
                boolean isConnected = intent.getBooleanExtra("data", false);
                String message = isConnected ? "Good! Connected to Internet" : "Sorry! Not connected to internet";
                if (!isConnected) {

                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
