package com.nhom1.zinzinfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom1.adapters.SearchAdapter;

public class Search extends AppCompatActivity {
    GridView gvBanhtrang;
    String[] BanhtrangName = {"Bánh tráng bơ cuộn thơm ngon đặc biệt", "Bánh tráng rong biển cháy tỏi thơm ngon","Bánh tráng sate tắc thơm cay ngon ngon","Bánh tráng sate bò thơm ngon hết sảy",
            "Bánh tráng tôm hành béo ngậy đậm đà", "Bánh tráng tỏi phi cay đậm chất nhà làm "};

    String[] BanhtrangPrice = { "10000đ","12000đ","10000đ","7000đ","10000đ","7000đ"};
    int[] BanhtrangThumb = {
            R.drawable.banhtrangbocuon, R.drawable.banhtrangrongbien , R.drawable.banhtrangsatetac,
            R.drawable.banhtrangsatebo, R.drawable.banhtrangphomaitomhanh, R.drawable.banhtrangtoiphicay};

    ImageView imvReturn,imvSpyt, imvGiohang;
    TextView xemtatcasanpham;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        gvBanhtrang =findViewById(R.id.gv_BanhTrang);
        SearchAdapter searchAdapter = new SearchAdapter(Search.this,BanhtrangName,BanhtrangThumb,BanhtrangPrice);
        gvBanhtrang.setAdapter(searchAdapter);

        imvReturn = findViewById(R.id.imv_Return);
        imvSpyt = findViewById(R.id.imv_Spyt);
        imvGiohang = findViewById(R.id.imv_GioHang);
        xemtatcasanpham = findViewById(R.id.txt_Xemtatcasanpham);
        addEvent();
    }

    private void addEvent() {
        xemtatcasanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this, ProductPage.class);
                startActivity(intent);
            }
        });
        imvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this, Homepage.class);
                startActivity(intent);
            }
        });
        imvSpyt.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }else {
                    Intent intentToi = new Intent(Search.this, SanphamyeuthichPage.class);
                    startActivity(intentToi);
                }
            }

            private void batDangNhap() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Search.this);
                builder.setTitle("Vui lòng đăng nhập");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setMessage("Để tiếp tục xem thông tin bạn cần phải đăng nhập!");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intentToi = new Intent(Search.this, LogInActivity.class);
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
        }));

       imvGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }else {
                    Intent intentToi = new Intent(Search.this, CartActivity.class);
                    startActivity(intentToi);
                }
            }

            private void batDangNhap() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Search.this);
                builder.setTitle("Vui lòng đăng nhập");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setMessage("Để tiếp tục xem thông tin bạn cần phải đăng nhập!");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intentToi = new Intent(Search.this, LogInActivity.class);
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
        });

    }
}