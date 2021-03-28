package com.example.sample_log_app.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;

import com.google.android.material.tabs.TabLayout;

import static com.example.sample_log_app.SampleLogApp.sAppContext;


public final class UIUtils {

    private UIUtils() {
        throw new AssertionError();
    }

    public static String getTitle(String id) {
        final int titleId = getResId(id, "string");
        return sAppContext.getResources().getString(titleId);
    }

    public static String getTitle(int id) {

        return sAppContext.getResources().getString(id);
    }

    public static Drawable getDrawable(int id) {
        return ContextCompat.getDrawable(sAppContext, id);
    }

    public static Drawable getDrawable(String id) {
        return getDrawable(getResId(id, "drawable"));
    }

    public static StateListDrawable getStateListDrawable(int state, String resOnId, String resOffId) {
        return getStateListDrawable(state, getResId(resOnId, "drawable"), getResId(resOffId, "drawable"));
    }

    public static StateListDrawable getStateListDrawable(int state, int resOnId, int resOffId) {
        final StateListDrawable drawableList = new StateListDrawable();
        drawableList.addState(new int[]{state}, getDrawable(resOnId));
        drawableList.addState(StateSet.WILD_CARD, getDrawable(resOffId));
        return drawableList;
    }

    public static int getResId(String id, String defType) {
        return sAppContext.getResources().getIdentifier(id, defType, sAppContext.getPackageName());
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return (displaymetrics.heightPixels / 3);
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    public static int getColor(int color) {
        return ContextCompat.getColor(sAppContext, color);
    }

    public static void wrapTabIndicatorToTitle(TabLayout tabLayout, int externalMargin, int internalMargin) {
        View tabStrip = tabLayout.getChildAt(0);
        if (tabStrip instanceof ViewGroup) {
            ViewGroup tabStripGroup = (ViewGroup) tabStrip;
            int childCount = ((ViewGroup) tabStrip).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View tabView = tabStripGroup.getChildAt(i);
                tabView.setMinimumWidth(0);
                tabView.setPadding(0, tabView.getPaddingTop(), 0, tabView.getPaddingBottom());
                if (tabView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
                    if (i == 0) {

                        settingMargin(layoutParams, externalMargin, internalMargin);
                    } else if (i == childCount - 1) {

                        settingMargin(layoutParams, internalMargin, externalMargin);
                    } else {

                        settingMargin(layoutParams, internalMargin, internalMargin);
                    }
                }
            }

            tabLayout.requestLayout();
        }
    }

    private static void settingMargin(ViewGroup.MarginLayoutParams layoutParams, int start, int end) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.setMarginStart(start);
            layoutParams.setMarginEnd(end);
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        } else {
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        }
    }

    public static float convertPxToDp(Context context, float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    public static int getCurrentNightMode() {
        return sAppContext.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
    }



}
