package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nhom1.zinzinfood.databinding.ActivityAfterPaymentBinding;

public class AfterPaymentActivity extends AppCompatActivity {

    ActivityAfterPaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_after_payment);
        binding = ActivityAfterPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AfterPaymentActivity.this, Homepage.class);
                startActivity(intent);
            }
        });

        //Footer link page ============================================================================

        binding.imvToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToi = new Intent(AfterPaymentActivity.this, Toi.class);
                startActivity(intentToi);
            }
        });
        binding.imvDonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToi = new Intent(AfterPaymentActivity.this, DonMuaPage.class);
                startActivity(intentToi);
            }
        });

        binding.imvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AfterPaymentActivity.this, ProductPage.class);
                startActivity(intent);
            }
        });

        binding.imvUuDai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AfterPaymentActivity.this, VoucherPage.class);
                startActivity(intent);
            }
        });
        binding.imvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AfterPaymentActivity.this, Homepage.class);
                startActivity(intent);
            }
        });
        //==========Footer link page end =======================================================
    }
}