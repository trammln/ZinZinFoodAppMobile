package com.nhom1.zinzinfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nhom1.DatabaseHelpers.ProductDatabase;
import com.nhom1.models.Product;
import com.nhom1.zinzinfood.databinding.ActivityDetailproductpageBinding;

import java.util.ArrayList;

public class DetailProductPage extends AppCompatActivity {

    ActivityDetailproductpageBinding binding;
    ProductDatabase db;
    ArrayList<Product>products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detailproductpage);
        binding = ActivityDetailproductpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();
        addEvents();
    }


    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProductPage.this, ProductPage.class);
                startActivity(intent);
            }
        });

        binding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0) {
                    batDangNhap();
                }
                else
                {
                    Intent intent = new Intent(DetailProductPage.this, CartActivity.class);
                    startActivity(intent);
                }
            }
        });

        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProductPage.this, Homepage.class);
                startActivity(intent);
            }
        });

        binding.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Trâm
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                } else {
                Intent intent = new Intent(DetailProductPage.this, CartActivity.class);
                Intent intent1 = getIntent();
                Product p = (Product) intent1.getSerializableExtra("productInf");
                intent.putExtra("productInf", p);
                Toast.makeText(DetailProductPage.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                startActivity(intent); }
                //Trâm
            }
        });

        binding.imvHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(DetailProductPage.this, SanphamyeuthichPage.class);
//                startActivity(intent);
                if(SplashActivity.EmailHoacPhone.length()==0) {
                    batDangNhap();
                }
                else {
                    Intent intent = new Intent(DetailProductPage.this, SanphamyeuthichPage.class);
                    Intent intent1 = getIntent();
                    Product p = (Product) intent1.getSerializableExtra("productInf");
                    intent.putExtra("productInf", p);
                    Toast.makeText(DetailProductPage.this,"Đã thêm vào danh sách sản phẩm yêu thích", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
        binding.txtXemDanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProductPage.this, FeedbackPage.class);
                startActivity(intent);
            }
        });
        binding.imvAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailProductPage.this,"Đã sao chép liên kết", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0) {
                    batDangNhap();
                }
                else {
                  Intent intent = new Intent(DetailProductPage.this, Messenger.class);
                  startActivity(intent);
                }
            }
        });

    }

    private void batDangNhap() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailProductPage.this);
        builder.setTitle("Vui lòng đăng nhập");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage("Để tiếp tục xem thông tin bạn cần phải đăng nhập!");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentToi = new Intent(DetailProductPage.this, LogInActivity.class);
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

    private void getData() {

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        int price = intent.getIntExtra("price", 0);
        double rate = intent.getDoubleExtra("rate", 0);
        int sold = intent.getIntExtra("sold", 0);

        String due = intent.getStringExtra("due");

        int quantity = intent.getIntExtra("quantity", 0);
        String component = intent.getStringExtra("component");
        String description = intent.getStringExtra("description");


        String image = intent.getStringExtra("image");
        int imageThumb = getResources().getIdentifier(image, "drawable", getPackageName());


        binding.txtName.setText(name);
        binding.txtPrice.setText(String.valueOf(price));
        binding.txtSold.setText(String.valueOf(sold));
        binding.imvPic.setImageResource(imageThumb);
        binding.txtRate.setText(String.valueOf(rate));
        binding.txtDue.setText(due);
        binding.txtStorage.setText(String.valueOf(quantity));
        binding.txtThanhPhan.setText(component);
        binding.txtDescription.setText(description);

    }
}