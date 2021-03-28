package com.example.sample_log_app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoadingDialog extends Dialog {

    public static String TAG = "FullScreenDialog";

    @BindView(R.id.imageView)
    ImageView mIv;

    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       setContentView(R.layout.loading_dialog);
        ButterKnife.bind(this);

        Glide.with(getContext())
                .load(getContext().getDrawable(R.drawable.ic_loading))
                .thumbnail(.25f)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mIv);
    }
}
