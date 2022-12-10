package com.nhom1.zinzinfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom1.zinzinfood.databinding.ActivitySplash2Binding;

public class Splash2Activity extends AppCompatActivity {
    ActivitySplash2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivitySplash2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvents();
        //Tram
    }

    private void addEvents() {
        binding.txtDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Splash2Activity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        binding.txtDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Splash2Activity.this, LogInActivity.class);
                startActivity(intent);
            }
        });

        binding.txtBatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Splash2Activity.this, Homepage.class);
                startActivity(intent);
            }
        });
        //Tram
    }

}