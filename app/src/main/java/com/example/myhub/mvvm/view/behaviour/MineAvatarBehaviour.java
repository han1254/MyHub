package com.example.myhub.mvvm.view.behaviour;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.myhub.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

/**
 * Time:2020/2/16 8:16
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class MineAvatarBehaviour extends CoordinatorLayout.Behavior<ImageView> {

    private final String TOOLBAR_TAG = "mine_toolbar";

    private float mAvatarStartX = 0;
    private float mAvatarStartY = 0;
    private float toolbarHeight = 0;
    private float mAppBarStartHeight = 0;
    private float mImageHeight = 0;
    private float percent = 1;

    private boolean isStart = false;

    public MineAvatarBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    FloatingActionButton.Behavior


    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {
        initValue(parent, child, dependency);

        float currentPercent = (child.getY() - toolbarHeight) / (mAvatarStartY - toolbarHeight);

        if (currentPercent < 0) {
            currentPercent = 0;
        }

        if (percent == currentPercent || currentPercent > 1) {


            return true;
        }

        percent = currentPercent;

        ViewCompat.setScaleX(child, percent);

        ViewCompat.setScaleY(child, percent);

        return false;
    }

    private void initValue(CoordinatorLayout parent, ImageView child, View dependency) {

        if (!isStart) {
            mAvatarStartX = child.getX();
            mAvatarStartY = child.getY();
            Toolbar toolbar = (Toolbar)parent.findViewWithTag(TOOLBAR_TAG);
            toolbarHeight = toolbar.getHeight();
            mAppBarStartHeight = dependency.getHeight();
            isStart = true;
        }


    }
}
