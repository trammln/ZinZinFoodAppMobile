package com.nhom1.zinzinfood;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom1.zinzinfood.databinding.ActivityForgotPassword2Binding;
import com.nhom1.zinzinfood.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    public static String EmailHoacPhone="";
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.side_slide);
        binding.SplashScreenImage.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, Splash2Activity.class));
                finish();
            }
        }, 3000);
        //Tram
    }
}