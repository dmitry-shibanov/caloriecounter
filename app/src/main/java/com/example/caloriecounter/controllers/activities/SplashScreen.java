package com.example.caloriecounter.controllers.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.caloriecounter.MainActivity;
import com.example.caloriecounter.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SplashScreen extends AppCompatActivity {

    private String[] phrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        phrase = new String[]{"Спорт","Соки","Правильное питание","Отдых","Бег"};

        final TextView appName = (TextView) findViewById(R.id.splash_app_name);

        appName.animate().alpha(0).setDuration(1000).setInterpolator(new DecelerateInterpolator()).withEndAction(new Runnable() {
            @Override
            public void run() {
                appName.setText(phrase[new Random().nextInt(phrase.length)]);
                appName.animate().alpha(1).setDuration(1000).setInterpolator(new AccelerateInterpolator()).start();
            }
        }).start();

//        appName.setAnimation();
//        Animation fadeOut = new AlphaAnimation(1, 0);
//        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
//        fadeOut.setStartOffset(1000);
//        fadeOut.setDuration(1000);
//
//        AnimationSet animation = new AnimationSet(false); //change to false
//        animation.addAnimation(fadeIn);
//        animation.addAnimation(fadeOut);
//        this.setAnimation(animation);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.splash_progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(SplashScreen.this, BottomNavigation.class);
                startActivity(intent);
            }
        }, 6000);
    }
}

//https://stackoverflow.com/questions/15313598/once-for-all-how-to-correctly-save-instance-state-of-fragments-in-back-stack