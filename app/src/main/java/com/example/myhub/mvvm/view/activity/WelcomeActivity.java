package com.example.myhub.mvvm.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.base.utils.statusbar.StatusBarUtil;
import com.example.myhub.R;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView mWelcomeLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorVerdure));
        mWelcomeLogo = findViewById(R.id.welcome_logo);
        startWelcome();

    }

    private void startWelcome() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_welcome);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(WelcomeActivity.this, SplashActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mWelcomeLogo.setAnimation(animation);
    }
}
