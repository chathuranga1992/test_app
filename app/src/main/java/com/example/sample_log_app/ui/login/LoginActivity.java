package com.example.sample_log_app.ui.login;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sample_log_app.GenericActivity;
import com.example.sample_log_app.R;
import com.example.sample_log_app.ui.forgetpassword.ForgotPasswordActivity;
import com.example.sample_log_app.ui.login.domain.LoginStatus;
import com.example.sample_log_app.ui.main.MainActivity;
import com.example.sample_log_app.ui.register.RegisterActivity;
import com.example.sample_log_app.util.Constants;
import com.example.sample_log_app.util.L;
import com.example.sample_log_app.util.StringUtils;
import com.example.sample_log_app.util.UIUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends GenericActivity {
    @BindView(R.id.rootLinearLayoutLogin)
    RelativeLayout mLoginBaseView;

    @BindView(R.id.text_input_layout_email_login)
    TextInputLayout mEditTextLEmail;

    @BindView(R.id.text_input_layout_password_login)
    TextInputLayout mEditTextLPw;

    @BindView(R.id.text_input_edit_text_email_login)
    TextInputEditText mInputEditTextEmail;

    @BindView(R.id.text_input_edit_text_password_login)
    TextInputEditText mInputEditTextPw;

    @BindView(R.id.tv_login_bottom_link)
    AppCompatTextView mTextViewSignUp;

    @BindView(R.id.tv_forgot_pw)
    TextView mTextViewForgotPW;

    @BindView(R.id.layoutContent)
    LinearLayout mLayoutContent;

    private LoginViewModel mLoginViewModel;
    private int clickCount = 0;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mHandler = new Handler();
        makeFullScreen();
        setupBottomSetup();

        mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mLoginViewModel.init(this);

        mInputEditTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mInputEditTextEmail.setCursorVisible(true);
                } else {

                    mInputEditTextEmail.setCursorVisible(false);
                }
            }
        });

        mInputEditTextPw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mInputEditTextPw.setCursorVisible(true);
                } else {

                    mInputEditTextPw.setCursorVisible(false);
                }
            }
        });
    }



    private void setupBottomSetup() {
        SpannableString string = new SpannableString(getString(R.string.don_t_have_an_account_sign_up));
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        string.setSpan(boldSpan, 22, getString(R.string.don_t_have_an_account_sign_up).length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextViewSignUp.setText(string);
        Space space = new Space(this);
        space.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                UIUtils.getScreenHeight(this)));
        mLayoutContent.addView(space);
    }

    @OnClick(R.id.button_activity_login)
    public void onSubmitLogin(View v) {

        String email = mEditTextLEmail.getEditText().getText().toString().trim();
        String pw = mEditTextLPw.getEditText().getText().toString().trim();

        L.d("---------->>>", Constants.isNetworkConnected + "");
        if (Constants.isNetworkConnected) {

            String mType = "Local";
            String mSubToken = "";
            Boolean valid = true;

            mInputEditTextEmail.setCursorVisible(false);
            mInputEditTextPw.setCursorVisible(false);

            mEditTextLEmail.setBackground(getDrawable(R.drawable.bg_edit_text));
            mEditTextLPw.setBackground(getDrawable(R.drawable.bg_edit_text));
            mEditTextLEmail.setErrorEnabled(false);
            mEditTextLPw.setErrorEnabled(false);

            if (StringUtils.isEmpty(email)) {
;
                mEditTextLEmail.requestFocus();
                valid = false;
            } else if (!StringUtils.isValidEmail(email)) {
mEditTextLEmail.setError("");
                mEditTextLEmail.requestFocus();
                valid = false;
            } else
                if (StringUtils.isEmpty(pw)) {
                    mEditTextLPw.setErrorEnabled(true);

                    mEditTextLPw.requestFocus();
                    valid = false;
                }

            if (valid) {


                hideBaseView();
                showLoadingIndicator();
                mLoginViewModel.doLogin(email, pw, mType, mSubToken).observe(this, new Observer<LoginStatus>() {
                    @Override
                    public void onChanged(LoginStatus loginStatus) {
                        L.d("---------->>", loginStatus.isDone() + "");
                        if (loginStatus.isDone()) {
                            startNewActivity(MainActivity.class);
                            dismissLoadingIndicator();
                            //showBaseView();
                            finishAfterTransition();
                        } else {
                            dismissLoadingIndicator();
                            showBaseView();
                            showInfoMsg(loginStatus.getMsg(), false);
                        }
                    }
                });
            }
        }else {
            Toast.makeText(this,R.string.no_internet, Toast.LENGTH_LONG).show();
        }


    }

    private void showBaseView() {
        mLoginBaseView.setVisibility(View.VISIBLE);
    }

    private void hideBaseView() {
        mLoginBaseView.setVisibility(View.GONE);
    }

    @OnClick(R.id.tv_forgot_pw)
    public void onForgotPw(View v) {

        startNewActivity(ForgotPasswordActivity.class);
    }

    @OnClick(R.id.tv_login_bottom_link)
    public void onRegister(View v) {
        startNewActivity(RegisterActivity.class);
    }



}
