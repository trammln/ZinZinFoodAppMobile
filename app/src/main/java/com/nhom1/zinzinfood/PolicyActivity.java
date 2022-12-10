package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nhom1.zinzinfood.databinding.ActivityPolicyBinding;

public class PolicyActivity extends AppCompatActivity {

    ActivityPolicyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_policy);

        binding = ActivityPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnPolicy01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PolicyActivity.this, subpolicy01.class);
                startActivity(intent1);
            }
            //Phuong Anh
        });

        binding.btnPolicy02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PolicyActivity.this,subpolicy02.class);
                startActivity(intent1);
            }
            //Phuong Anh
        });

        binding.btnPolicy03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PolicyActivity.this,subpolicy03.class);
                startActivity(intent1);
            }
            //Phuong Anh
        });

        binding.btnPolicy04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PolicyActivity.this,subpolicy04.class);
                startActivity(intent1);
            }
            //Phuong Anh
        });

        binding.btnPolicy05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PolicyActivity.this,subpolicy05.class);
                startActivity(intent1);
            }
            //Phuong Anh
        });

        binding.btnPolicy05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PolicyActivity.this,subpolicy05.class);
                startActivity(intent1);
            }
            //Phuong Anh
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PolicyActivity.this,Toi.class);
                startActivity(intent1);
            }
        });
    }
}