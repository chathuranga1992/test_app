package com.example.sample_log_app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.sample_log_app.util.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoLoginDialog extends Dialog implements View.OnClickListener {

    @BindView(R.id.textViewTitle)
    TextView mTextViewTitle;
    @BindView(R.id.textViewMsg)
    TextView mTextViewMsg;
    @BindView(R.id.buttonCancel)
    Button mButtonCancel;
    @BindView(R.id.buttonOk)
    Button mButtonOk;

    @BindView(R.id.ll_header)
    LinearLayout mHeaderCover;

    private String mTitle;
    private String mMessage;
    private String mCancelButtonText;
    private String mOkButtonText = "Ok";
    private int headerColor;
    private boolean mTitleEnable = true;
    private OnInfoClickListener mOkClickListener;
    private OnInfoClickListener mCancelClickListener;

    public InfoLoginDialog(@NonNull Context context) {
        super(context);
    }

    public InfoLoginDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected InfoLoginDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.info_title_view);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);
        mTextViewMsg.setText(mMessage);
        mTextViewTitle.setEnabled(mTitleEnable);
        mTextViewTitle.setText(mTitle);
        headerColor = getContext().getColor(R.color.colorWhite);
        mButtonOk.setText(mOkButtonText);
        if (StringUtils.isNotEmpty(mCancelButtonText)) {
            mButtonCancel.setText(mCancelButtonText);
            mButtonCancel.setVisibility(View.VISIBLE);
        }
        mButtonOk.setOnClickListener(this::onClick);
        mButtonCancel.setOnClickListener(this::onClick);
        setCanceledOnTouchOutside(false);
    }

    public InfoLoginDialog setTitle(String mTitle, boolean enable) {

        this.mTitle = mTitle;
        this.mTitleEnable = enable;
        if (mTextViewTitle != null) {
            mTextViewTitle.setEnabled(mTitleEnable);
            mTextViewTitle.setText(mTitle);

        }

        return this;
    }

    public InfoLoginDialog setMessage(String mMessage) {
        this.mMessage = mMessage;
        if (mTextViewMsg != null) {
            mTextViewMsg.setText(mMessage);
        }

        return this;
    }

    public InfoLoginDialog setCancelButtonText(String text) {
        this.mCancelButtonText = text;
        if (mButtonCancel != null) {
            mButtonCancel.setText(text);
            mButtonCancel.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public InfoLoginDialog setOkButtonText(String text) {
        this.mOkButtonText = text;
        if (mButtonOk != null) {
            mButtonOk.setText(text);

        }
        return this;
    }

    public InfoLoginDialog setHeaderBackground(int color) {
        this.headerColor = color;
        if (mHeaderCover != null) {
            mHeaderCover.setBackgroundColor(getContext().getColor(R.color.colorLGreen));

        }
        return this;
    }

    public InfoLoginDialog setOkButtonClickListener(InfoLoginDialog.OnInfoClickListener clickListener) {
        this.mOkClickListener = clickListener;
        return this;
    }

    public InfoLoginDialog setCancelClickLisListener(InfoLoginDialog.OnInfoClickListener clickListener) {
        this.mCancelClickListener = clickListener;
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOk:
                if (mOkClickListener != null) {
                    mOkClickListener.onClick(this);
                } else {
                    dismiss();
                }
                break;
            case R.id.buttonCancel:
                if (mCancelClickListener != null) {
                    mCancelClickListener.onClick(this);
                } else {
                    dismiss();
                }
                break;
            default:
                break;

        }
    }



    public interface OnInfoClickListener {
        void onClick(InfoLoginDialog var1);
    }
}