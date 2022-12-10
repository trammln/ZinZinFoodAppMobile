package com.nhom1.zinzinfood;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;

import com.nhom1.DatabaseHelpers.UserDatabase;
import com.nhom1.adapters.BestsellerAdapter;
import com.nhom1.models.Product;
import com.nhom1.models.User;
import com.nhom1.zinzinfood.databinding.ActivityHomepageBinding;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {
    ActivityResultLauncher<Intent> launcher;
    ArrayList<User> users;
    UserDatabase db;
    ArrayList<Product> products;
    // khong bi loi
    GridView gvBestseller;
    GridView gvFlashsale;

    String[] FlashsaleName = {"Bánh tráng bơ cuộn thơm ngon đặc biệt", "Bánh tráng rong biển cháy tỏi thơm ngon","Cơm cháy lắc khô gà truyền thống nhà làm",
            "Quẩy bò ZinZin quẩy cùng deadline mỗi ngày", "Mực bento cay cay xé tan cõi lòng", "Khô gà lá chanh nhà làm đảm bảo "};
    // ,"Cơm cháy khô gà","Quẩy bò ZinZin", "Mực bento ", "Khô gà lá chanh "
    String[] FlashsalePrice = { "10000đ","12000đ","12000đ","7000đ","7000đ","12000đ"};
    // ,"12000 VNĐ","7000 VNĐ","7000 VNĐ","12000 VNĐ"
    int[] FlashsaleThumb = {
            R.drawable.banhtrangbocuon, R.drawable.banhtrangrongbien , R.drawable.comchaykhoga,
            R.drawable.quaybozinzin, R.drawable.mucbentocaycay, R.drawable.khogalachanh};
    //, R.drawable.comchaykhoga, R.drawable.quaybozinzin, R.drawable.mucbentocaycay, R.drawable.khogalachanh

    String[] BestsellerName = {"Bánh tráng bơ cuộn thơm ngon đặc biệt", "Bánh tráng rong biển cháy tỏi thơm ngon","Cơm cháy lắc khô gà truyền thống nhà làm",
            "Quẩy bò ZinZin quẩy cùng deadline mỗi ngày", "Mực bento cay cay xé tan cõi lòng", "Khô gà lá chanh nhà làm đảm bảo "};
    String[] BestsellerPrice = { "10000đ","12000đ","12000đ","7000đ","7000đ","12000đ"};
    // ,"12000 VNĐ","7000 VNĐ","7000 VNĐ","12000 VNĐ"
    int[] BestsellerThumb = {
            R.drawable.banhtrangbocuon, R.drawable.banhtrangrongbien , R.drawable.comchaykhoga,
            R.drawable.quaybozinzin, R.drawable.mucbentocaycay, R.drawable.khogalachanh};
    //, R.drawable.comchaykhoga, R.drawable.quaybozinzin, R.drawable.mucbentocaycay, R.drawable.khogalachanh

    ImageView imvSearch,imvSpyt,
            imvToi, imvGioHang, imvProduct, imvUuDai, imvDonMua;//An
    SearchView svSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        gvBestseller =findViewById(R.id.gv_Bestseller);
        BestsellerAdapter bestsellerAdapter = new BestsellerAdapter(Homepage.this,BestsellerName,BestsellerThumb,BestsellerPrice);
        gvBestseller.setAdapter(bestsellerAdapter);

        gvFlashsale =findViewById(R.id.gv_Flashsale);
        BestsellerAdapter sellerAdapter = new BestsellerAdapter(Homepage.this,FlashsaleName,FlashsaleThumb,FlashsalePrice);
        gvFlashsale.setAdapter(sellerAdapter);



        svSearch = findViewById(R.id.sv_Search);
        imvSearch = findViewById(R.id.imv_Search);
        imvSpyt = findViewById(R.id.imv_Spyt);


        imvToi=findViewById(R.id.imv_Toi);//An
        imvGioHang=findViewById(R.id.imv_GioHang);//An
        imvProduct=findViewById(R.id.imv_Product);//An
        imvUuDai=findViewById(R.id.imv_UuDai); //Linh
        imvDonMua = findViewById(R.id.imv_DonMua);//Linh
        //==An thêm code từ khúc này========
        imvToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToi = new Intent(Homepage.this, Toi.class);
                startActivity(intentToi);
            }
        });
        imvGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }else {
                    Intent intentToi = new Intent(Homepage.this, CartActivity.class);
                    startActivity(intentToi);
                }
            }

            private void batDangNhap() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Homepage.this);
                builder.setTitle("Vui lòng đăng nhập");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setMessage("Để tiếp tục xem thông tin bạn cần phải đăng nhập!");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intentToi = new Intent(Homepage.this, LogInActivity.class);
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

        imvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, ProductPage.class);
                startActivity(intent);
            }
        });
        //==========An tới khúc này========

        //CharSequence query = svSearch.getQuery().toString();

        imvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String search = svSearch.getQuery().toString();
                Intent intent = new Intent(Homepage.this, Search.class);
                startActivity(intent);
            }
        });

        //Linh
        imvDonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }else {
                    Intent intentToi = new Intent(Homepage.this, DonMuaChoThanhToan.class);
                    startActivity(intentToi);
                }
            }

            private void batDangNhap() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Homepage.this);
                builder.setTitle("Vui lòng đăng nhập");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setMessage("Để tiếp tục xem thông tin bạn cần phải đăng nhập!");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intentToi = new Intent(Homepage.this, LogInActivity.class);
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

        imvSpyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }else {
                    Intent intentToi = new Intent(Homepage.this, SanphamyeuthichPage.class);
                    startActivity(intentToi);
                }
            }

            private void batDangNhap() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Homepage.this);
                builder.setTitle("Vui lòng đăng nhập");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setMessage("Để tiếp tục xem thông tin bạn cần phải đăng nhập!");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intentToi = new Intent(Homepage.this, LogInActivity.class);
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

        imvUuDai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, VoucherPage.class);
                startActivity(intent);
            }
        });

        gvBestseller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(Homepage.this, DetailProductPage.class);
//
//                Product item = products.get(i);
////                intent.putExtra("productInf", item);
//
//                intent.putExtra("name", item.getProductName());
//                intent.putExtra("price", item.getProductPrice());
//                intent.putExtra("rate", item.getProductRate());
//                intent.putExtra("sold", item.getProductSold());
//                intent.putExtra("image", item.getProductThumb());
//                intent.putExtra("due", item.getProductDue());
//                intent.putExtra("quantity", item.getProductQuantity());
//                intent.putExtra("component", item.getProductComponent());
//                intent.putExtra("description", item.getProductDescription());
//
//                startActivity(intent);
            }
        });


    }

}
