package com.example.caloriecounter.controllers.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.caloriecounter.R;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SplashScreen extends AppCompatActivity {

    private String[] phrase;

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
                return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 123:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        phrase = getResources().getStringArray(R.array.activity_kind);//new String[]{"Спорт", "Соки", "Правильное питание", "Отдых", "Бег"};

        final TextView appName = (TextView) findViewById(R.id.splash_app_name);

        appName.animate().alpha(0).setDuration(1000).setInterpolator(new DecelerateInterpolator()).withEndAction(new Runnable() {
            @Override
            public void run() {
                appName.setText(phrase[new Random().nextInt(phrase.length)]);
                appName.animate().alpha(1).setDuration(1000).setInterpolator(new AccelerateInterpolator()).start();
            }
        }).start();

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.splash_progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            checkPermissions();
//            DB db = DB.getDB(getApplicationContext());
////            AppDbHelper dbHelper = new AppDbHelper(new com.example.caloriecounter.data.DatabaseOpenHelper(getApplicationContext(),"calorie",null));
//            AppDbHelper dbHelper = db.getDbHelper();
//            List<FoodListFragment> foodList = dbHelper.getFood();
            Intent intent = new Intent(SplashScreen.this, BottomNavigation.class);
            startActivity(intent);
        }, 6000);
    }
}

//https://stackoverflow.com/questions/15313598/once-for-all-how-to-correctly-save-instance-state-of-fragments-in-back-stack