package com.nhom1.zinzinfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nhom1.zinzinfood.databinding.ActivityKetquatimkiemsanphamBinding;

public class KetQuaTimKiemSanPham extends AppCompatActivity {

    ActivityKetquatimkiemsanphamBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketquatimkiemsanpham);

        binding = ActivityKetquatimkiemsanphamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KetQuaTimKiemSanPham.this, ProductPage.class);
                startActivity(intent);
            }
        });

        binding.imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0) {
                    batDangNhap();
                }
                else{
                    Intent intent = new Intent(KetQuaTimKiemSanPham.this, CartActivity.class);
                    startActivity(intent);
                }
            }
        });

//        binding.linearsp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               setContentView(R.layout.activity_detailproductpage);
//            }
//        });

        binding.txtArrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KetQuaTimKiemSanPham.this, ArrangeProduct.class);
                startActivity(intent);
            }
        });

        binding.txtNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KetQuaTimKiemSanPham.this, NewProduct.class);
                startActivity(intent);
            }
        });

        binding.txtPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KetQuaTimKiemSanPham.this, PopularProduct.class);
                startActivity(intent);
            }
        });

        binding.txtAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KetQuaTimKiemSanPham.this, ProductPage.class);
                startActivity(intent);
            }
        });

    }

    private void batDangNhap() {
        AlertDialog.Builder builder = new AlertDialog.Builder(KetQuaTimKiemSanPham.this);
        builder.setTitle("Vui lòng đăng nhập");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage("Để tiếp tục xem thông tin bạn cần phải đăng nhập!");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentToi = new Intent(KetQuaTimKiemSanPham.this, LogInActivity.class);
                startActivity(intentToi);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}