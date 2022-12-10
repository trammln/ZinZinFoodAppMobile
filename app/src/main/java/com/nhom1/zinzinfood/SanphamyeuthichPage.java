package com.nhom1.zinzinfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.nhom1.DatabaseHelpers.CartDatabase;
import com.nhom1.DatabaseHelpers.ProductDatabase;
import com.nhom1.DatabaseHelpers.ToiVoucher;
import com.nhom1.DatabaseHelpers.YeuThichDatabase;
//import com.nhom1.adapters.SanphamyeuthichAdapter;
import com.nhom1.adapters.ProductAdapter;
import com.nhom1.adapters.SanphamyeuthichAdapter;
import com.nhom1.models.Product;
import com.nhom1.models.ProductCart;
import com.nhom1.models.Sanphamyeuthich;
import com.nhom1.zinzinfood.databinding.ActivitySanphamyeuthichBinding;

import java.util.ArrayList;

public class SanphamyeuthichPage extends AppCompatActivity {

    ActivitySanphamyeuthichBinding binding;
    YeuThichDatabase db;
    ArrayList<Product>products;
    SanphamyeuthichAdapter adapter;
//    SanphamyeuthichAdapter adapter;
//    ArrayList<Sanphamyeuthich> productList;
//    ProductDatabase db;


    // khong bi loi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_productpage);
        binding = ActivitySanphamyeuthichBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        loadData();
        //Trâm
        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("productInf");
        createDb();
        if(p != null) {
            getData();
        }
        //Trâm
        addEvents();
        loadData();

    }

    private void getData() {

        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("productInf");
        String name = p.getProductName();
        int price = p.getProductPrice();
        double rate = p.getProductRate();
        int sold = p.getProductSold();
        String due = p.getProductDue();
        int quantity = p.getProductQuantity();
        String component = p.getProductComponent();
        String description = p.getProductDescription();
        String image = p.getProductThumb();

        db.execSql("INSERT INTO " + YeuThichDatabase.TBL_NAME + " VALUES(null, '" + name + "', " + price + ", "+ rate + ", "+sold+", '"+due+"', "+quantity+", '"+component+"', '"+description+"', '"+image+"')");

        db.getData("SELECT * FROM " + YeuThichDatabase.TBL_NAME);
        //Trâm
        loadData();
    }


    private void loadData() {
        products = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + YeuThichDatabase.TBL_NAME);
        while (c.moveToNext())
        {
            products.add(new Product(c.getInt(0), c.getString(1), c.getInt(2), c.getDouble(3), c.getInt(4), c.getString(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9)));
        }
        c.close();
        adapter = new SanphamyeuthichAdapter(SanphamyeuthichPage.this, R.layout.gridview_sanphamyeuthich, products);
        binding.gvSPYT.setAdapter(adapter);
    }

    private void createDb() {
        db = new YeuThichDatabase(SanphamyeuthichPage.this);
//        db.createSampleData();
        //Trâm
    }

    public void openDelete(Product b) {
        Dialog dialog=new Dialog(SanphamyeuthichPage.this);
        dialog.setContentView(R.layout.dialog_deletevoucher);

        Button btnDelete, btnCancel;
        btnDelete = dialog.findViewById(R.id.btn_Xoa);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSql("DELETE FROM "+ YeuThichDatabase.TBL_NAME+" WHERE "+
                        YeuThichDatabase.COL_ID+"="+b.getProductId());

                loadData();
                dialog.dismiss();
                Toast.makeText(SanphamyeuthichPage.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel = dialog.findViewById(R.id.btn_Cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /*public void openMuangay(Product b) {

        Intent intent = new Intent(SanphamyeuthichPage.this, PaymentActitvity.class);
        *//*Intent intent1 = getIntent();
        Product p = (Product) intent1.getSerializableExtra("productInf");
        intent.putExtra("productInf", p);
        Toast.makeText(SanphamyeuthichPage.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();*//*
        startActivity(intent);
       *//* Intent intent = new Intent(SanphamyeuthichPage.this, PaymentActitvity.class);
        startActivity(intent);*//*

    }*/

    private void addEvents() {

        binding.imvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SanphamyeuthichPage.this, Homepage.class);
                startActivity(intent);
            }
        });
    }
}

//    private void loadData() {
//        productList = new ArrayList<>();
//        productList.add(new com.nhom1.models.Sanphamyeuthich("Bánh tráng rong biển cháy tỏi thơm ngon", R.drawable.banhtrangrongbien,12000,"Đã bán 200"));
//        productList.add(new com.nhom1.models.Sanphamyeuthich("Bánh tráng bơ cuộn thơm ngon đặc biệt", R.drawable.banhtrangbocuon,10000,"Đã bán 250"));
//        productList.add(new com.nhom1.models.Sanphamyeuthich("Bánh tráng tôm hành béo ngậy đậm đà", R.drawable.banhtrangphomaitomhanh,10000,"Đã bán 150"));
//        productList.add(new com.nhom1.models.Sanphamyeuthich("Cơm cháy lắc khô gà truyền thống nhà làm", R.drawable.comchaykhoga,12000,"Đã bán 100"));
//        productList.add(new com.nhom1.models.Sanphamyeuthich("Mực bento cay cay xé tan cõi lòng", R.drawable.mucbentocaycay,7000,"Đã bán 50"));
//        productList.add(new com.nhom1.models.Sanphamyeuthich("Bánh tráng tỏi phi cay đậm chất nhà làm", R.drawable.banhtrangtoiphicay,7000,"Đã bán 150"));
//        productList.add(new com.nhom1.models.Sanphamyeuthich("Bánh tráng sa tế bò thơm ngon hết sảy", R.drawable.banhtrangsatebo,7000,"Đã bán 10"));
//        productList.add(new com.nhom1.models.Sanphamyeuthich("Bánh tráng sa tế tắc thơm cay ngon ngon", R.drawable.banhtrangsatetac,10000,"Đã bán 120"));
//        productList.add(new com.nhom1.models.Sanphamyeuthich("Khô gà lá chanh nhà làm đảm bảo", R.drawable.khogalachanh,12000,"Đã bán 140"));
//
//        adapter = new SanphamyeuthichAdapter(SanphamyeuthichPage.this, R.layout.gridview_sanphamyeuthich, productList);
//        binding.gvSPYT.setAdapter(adapter);
//    }

//    public void openMuangay(Sanphamyeuthich b) {
//        Intent intent = new Intent(SanphamyeuthichPage.this, CartActivity.class);
//        startActivity(intent);
//    }
//    public void openDelete(Sanphamyeuthich b) {
//       Toast.makeText(SanphamyeuthichPage.this, "Bạn đã xóa thành công", Toast.LENGTH_SHORT).show();
//
//    }
//}