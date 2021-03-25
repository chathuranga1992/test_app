package com.example.sample_log_app.ui.forgetpassword;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.sample_log_app.GenericActivity;
import com.example.sample_log_app.InfoDialog;
import com.example.sample_log_app.R;
import com.example.sample_log_app.ui.forgetpassword.domain.PasswordStatus;
import com.example.sample_log_app.ui.login.LoginActivity;
import com.example.sample_log_app.util.Constants;
import com.example.sample_log_app.util.L;
import com.example.sample_log_app.util.StringUtils;
import com.example.sample_log_app.util.UIUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends GenericActivity {

    @BindView(R.id.text_input_layout_email_Forgot_pw)
    TextInputLayout mEditTextLEmail;

    @BindView(R.id.text_input_edit_text_email_Forgot_pw)
    TextInputEditText mInputEditTextEmail;

    @BindView(R.id.rootLinearLayoutFF)
    LinearLayout mFpBaseView;

    ForgotPasswordViewModel mPasswordViewModel;
    private boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        makeFullScreen();
        mPasswordViewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
        mPasswordViewModel.init();
    }

    @OnClick(R.id.iv_back_forgot_pw)
    public void onBackForgotPw(View v) {
        finish();
    }

    private void showBaseView() {
        mFpBaseView.setVisibility(View.VISIBLE);
    }

    private void hideBaseView() {
        mFpBaseView.setVisibility(View.GONE);
    }

    @OnClick(R.id.button_activity_Forgot_pw)
    public void onSubmitFogotPw(View v) {
        L.d("---------->>>", Constants.isNetworkConnected + "");
        if (Constants.isNetworkConnected) {
            mInputEditTextEmail.setCursorVisible(false);
            mEditTextLEmail.setErrorEnabled(false);
            mEditTextLEmail.setBackground(getDrawable(R.drawable.bg_edit_text));
            valid = true;

            if (StringUtils.isEmpty(mEditTextLEmail.getEditText().getText().toString())) {
                valid = false;
                //mEditTextLEmail.setError(getString(R.string.COMMON_EMPTY));
                mEditTextLEmail.requestFocus();
            } else if (!StringUtils.isValidEmail(mEditTextLEmail.getEditText().getText().toString())) {
                valid = false;
                //mEditTextLEmail.setError(getString(R.string.INVALID_EMAIL_MESSAGE));
                mEditTextLEmail.requestFocus();
            }
            mInputEditTextEmail.setCursorVisible(true);
            if (valid) {
                hideBaseView();
                showLoadingIndicator();
                mPasswordViewModel.sendPasswordRequest(mEditTextLEmail.getEditText()
                        .getText().toString()).observe(this, new Observer<PasswordStatus>() {
                    @Override
                    public void onChanged(PasswordStatus passwordStatus) {
                        if (passwordStatus.isDone()) {
                            showBaseView();
                            dismissLoadingIndicator();
                            //showInfoMsg(" " + getString(R.string.forgot_pw_success_msg));

                            new InfoDialog(ForgotPasswordActivity.this, R.style.Theme_Dialog)
                                    .setTitle(UIUtils.getTitle(R.string.info), false)
                                    .setMessage(getString(R.string.forgot_pw_success_msg))
                                    .setOkButtonClickListener(new InfoDialog.OnInfoClickListener() {
                                        @Override
                                        public void onClick(InfoDialog var1) {
                                            startNewActivity(LoginActivity.class);
                                            finish();
                                        }
                                    })
                                    .setOkButtonText(UIUtils.getTitle(R.string.login))
                                    .show();
                        } else {
                            showBaseView();
                            dismissLoadingIndicator();
                            showInfoMsg(passwordStatus.getMsg(), false);
                        }
                    }
                });
            }
        } else {
            Toast.makeText(this,R.string.no_internet, Toast.LENGTH_LONG).show();
        }
    }
}
