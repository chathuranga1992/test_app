package com.example.sample_log_app.ui.register;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sample_log_app.GenericActivity;
import com.example.sample_log_app.InfoLoginDialog;
import com.example.sample_log_app.R;
import com.example.sample_log_app.ui.login.LoginActivity;
import com.example.sample_log_app.ui.register.domain.RegisterStatus;
import com.example.sample_log_app.ui.register.domain.UserInputData;
import com.example.sample_log_app.util.Constants;
import com.example.sample_log_app.util.L;
import com.example.sample_log_app.util.StringUtils;
import com.example.sample_log_app.util.UIUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends GenericActivity {

    @BindView(R.id.rootLinearLayoutRegister)
    RelativeLayout mRegisterBaseView;

    @BindView(R.id.text_input_layout_name_register)
    TextInputLayout mTextIplName;

    @BindView(R.id.text_input_layout_email_register)
    TextInputLayout mTextIplEmail;


    @BindView(R.id.text_input_layout_password_register)
    TextInputLayout mTextIplPw;

    @BindView(R.id.text_input_layout_cnfm_password_register)
    TextInputLayout mTextIplCnfmPw;

    @BindView(R.id.text_input_edit_text_name_register)
    TextInputEditText mInputEditTextName;

    @BindView(R.id.text_input_edit_text_email_register)
    TextInputEditText mInputEditTextEmail;



    @BindView(R.id.text_input_edit_text_password_register)
    TextInputEditText mInputEditTextPw;

    @BindView(R.id.text_input_edit_text_cnfm_password_register)
    TextInputEditText mInputEditTextCnfmPw;

    @BindView(R.id.layoutContent)
    LinearLayout mLayoutContent;

    Drawable drawable = null;
    boolean valid = true;
    private RegistrationViewModel mViewModel;
    private boolean state = false;
    private String mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        makeFullScreen();
        setupBottomSetup();

        mViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        mViewModel.init();


    }

    private void setupBottomSetup() {
        SpannableString string = new SpannableString(getString(R.string.have_an_account_login));
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        string.setSpan(boldSpan, 17, getString(R.string.have_an_account_login).length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Space space = new Space(this);
        space.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                UIUtils.getScreenHeight(this)));
        mLayoutContent.addView(space);
    }

    @OnClick(R.id.iv_back_register)
    public void onBackRegister(View v) {
        finish();
    }

    @OnClick(R.id.tv_register_bottom_link)
    public void onRegisterLoginLink(View v) {
        startNewActivity(LoginActivity.class);
    }


    @OnClick(R.id.button_activity_register)
    public void onSubmitRegister(View v) {
        L.d("---------->>>", Constants.isNetworkConnected + "");
        if (Constants.isNetworkConnected) {

            valid = true;
            String splChrs = "-/@#$%^&_+=()";
            Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

            String mName = mTextIplName.getEditText().getText().toString().trim();
            String mEmail = mTextIplEmail.getEditText().getText().toString().trim();
            String mPassword = mTextIplPw.getEditText().getText().toString().trim();
            String mConfirmPassword = mTextIplCnfmPw.getEditText().getText().toString().trim();

            mTextIplName.setBackground(getDrawable(R.drawable.bg_edit_text));
            mTextIplEmail.setBackground(getDrawable(R.drawable.bg_edit_text));
            mTextIplCnfmPw.setBackground(getDrawable(R.drawable.bg_edit_text));
            mTextIplPw.setBackground(getDrawable(R.drawable.bg_edit_text));

            mInputEditTextName.setCursorVisible(false);
            mInputEditTextEmail.setCursorVisible(false);
            mInputEditTextPw.setCursorVisible(false);
            mInputEditTextCnfmPw.setCursorVisible(false);

            if (StringUtils.isEmpty(mName)) {
                mTextIplName.requestFocus();
                valid = false;
            } else if (mName.matches("[0-9]+") && mName.length() > 2
                    || mName.contains("[" + splChrs + "]+") || regex.matcher(mName).find()) {
                mTextIplName.requestFocus();
                valid = false;
            }  else if (StringUtils.isEmpty(mEmail)) {

                                mTextIplEmail.requestFocus();
                                valid = false;
                            } else if (!StringUtils.isValidEmail(mEmail)) {

                                mTextIplEmail.requestFocus();
                                valid = false;
                            } else


                                if (StringUtils.isEmpty(mConfirmPassword)) {

                                    mTextIplCnfmPw.requestFocus();
                                    valid = false;
                                } else if (!StringUtils.equals(mPassword, mConfirmPassword)) {

                                    mTextIplCnfmPw.requestFocus();
                                    valid = false;
                                } else

                                    if (StringUtils.isEmpty(mPassword)) {

                                        mTextIplPw.requestFocus();
                                        valid = false;
                                    } else if (mPassword.length() < 8) {

                                        mTextIplPw.requestFocus();
                                        valid = false;
                                    }
            mInputEditTextName.setCursorVisible(true);
            mInputEditTextEmail.setCursorVisible(true);
            mInputEditTextPw.setCursorVisible(true);
            mInputEditTextCnfmPw.setCursorVisible(true);

            if (valid) {

                UserInputData inputData = new UserInputData();
                inputData.setUserName(mName);
                inputData.setUserEmail(mEmail);
                inputData.setUserGender(mGender);
                inputData.setUserPassword(mPassword);
                inputData.setUserConPassword(mConfirmPassword);
                showLoadingIndicator();
                mViewModel.doRegisterUser(inputData).observe(this,
                        new Observer<RegisterStatus>() {
                            @Override
                            public void onChanged(RegisterStatus registerStatus) {
                                if (registerStatus.isDone()) {

                                    dismissLoadingIndicator();


                                    new InfoLoginDialog(RegisterActivity.this, R.style.Theme_Dialog)
                                            .setMessage(getString(R.string.check_mail))
                                            .setTitle("Thank You !", true)
                                            .setOkButtonText("Login")
                                            .setOkButtonClickListener(new InfoLoginDialog.OnInfoClickListener() {
                                                @Override
                                                public void onClick(InfoLoginDialog var1) {
                                                    startNewActivity(LoginActivity.class);
                                                    finish();

                                                }
                                            }).show();
                                    //showInfoMsg(getString(R.string.check_mail), RegistrationActivity.this);

                                } else {
                                    dismissLoadingIndicator();
                                    showInfoMsg(registerStatus.getMsg(), false);
                                }
                            }
                        });
            }
        } else {
            Toast.makeText(this,R.string.no_internet, Toast.LENGTH_LONG).show();
        }
    }





}
