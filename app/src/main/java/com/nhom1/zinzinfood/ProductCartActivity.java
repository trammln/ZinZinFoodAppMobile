package com.nhom1.zinzinfood;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.nhom1.zinzinfood.databinding.ActivityProductCartBinding;

public class ProductCartActivity extends AppCompatActivity {
    ActivityProductCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product_cart);
        binding = ActivityProductCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}