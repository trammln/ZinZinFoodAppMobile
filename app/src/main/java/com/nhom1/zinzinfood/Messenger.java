package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nhom1.zinzinfood.databinding.ActivityMessengerBinding;
import com.nhom1.zinzinfood.databinding.ActivitySanphamyeuthichBinding;

public class Messenger extends AppCompatActivity {
    ActivityMessengerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_messenger);
        binding = ActivityMessengerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvents();
    }

    private void addEvents() {
        binding.imvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Messenger.this, Toi.class);
                startActivity(intent);
            }
        });
    }
}