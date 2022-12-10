package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nhom1.zinzinfood.databinding.ActivityAddNewAddressBinding;

public class AddNewAddress extends AppCompatActivity {

    ActivityAddNewAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_new_address);
        binding = ActivityAddNewAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {finish();
            }
        });

        binding.btnAddNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNewAddress.this, DeliveryAddress.class);
                // Send data

                startActivity(intent);
            }
        });
    }
}