package com.nhom1.zinzinfood;

import static com.nhom1.DatabaseHelpers.ProductDatabase.COL_ID;
import static com.nhom1.DatabaseHelpers.ProductDatabase.COL_NAME;
import static com.nhom1.DatabaseHelpers.ProductDatabase.COL_PRICE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.nhom1.DatabaseHelpers.ProductDatabase;
import com.nhom1.adapters.ProductAdapter;
import com.nhom1.models.Product;
import com.nhom1.zinzinfood.databinding.ActivityProductpageBinding;

import java.util.ArrayList;

public class ProductPage extends AppCompatActivity {

    ActivityProductpageBinding binding;
    ProductDatabase db;
    ArrayList<Product>products;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_productpage);
        binding = ActivityProductpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.txtAutocompleteTextView);
        String[] products =getResources().getStringArray(R.array.products);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        autoCompleteTextView.setAdapter(arrayAdapter);

        createDb();
        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.gvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ProductPage.this, DetailProductPage.class);
                Product item = products.get(i);
                intent.putExtra("productInf", item);
                intent.putExtra("name", item.getProductName());
                intent.putExtra("price", item.getProductPrice());
                intent.putExtra("rate", item.getProductRate());
                intent.putExtra("sold", item.getProductSold());
                intent.putExtra("image", item.getProductThumb());
                intent.putExtra("due", item.getProductDue());
                intent.putExtra("quantity", item.getProductQuantity());
                intent.putExtra("component", item.getProductComponent());
                intent.putExtra("description", item.getProductDescription());
                startActivity(intent);
            }
        });

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductPage.this, Homepage.class);
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
                    Intent intent = new Intent(ProductPage.this, CartActivity.class);
                    startActivity(intent);
                }
            }
        });

        binding.txtArrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductPage.this, ArrangeProduct.class);
                startActivity(intent);
            }
        });

        binding.txtNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductPage.this, NewProduct.class);
                startActivity(intent);
            }
        });

        binding.txtPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductPage.this, PopularProduct.class);
                startActivity(intent);
            }
        });

        binding.txtAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductPage.this, ProductPage.class);
                startActivity(intent);
            }
        });

        binding.imvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProductPage.this, KetQuaTimKiemSanPham.class);
                startActivity(intent);
//                if (binding.txtAutocompleteTextView.toString() == "Bánh tráng rong biển")
//                {
//                     Intent intent = new Intent(ProductPage.this, DetailProductPage.class);
//                     startActivity(intent);
//                }
            }
        });
    }

    private void loadData() {
        products = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + ProductDatabase.TBL_NAME);
        while (c.moveToNext())
        {
            products.add(new Product(c.getInt(0), c.getString(1), c.getInt(2), c.getDouble(3), c.getInt(4), c.getString(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9)));
        }
        c.close();
        adapter = new ProductAdapter(ProductPage.this, R.layout.gridview_productpage, products);
        binding.gvProducts.setAdapter(adapter);
    }


    private void createDb() {
       db = new ProductDatabase(ProductPage.this);
       db.createSampleData();
    }

    private void batDangNhap() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductPage.this);
        builder.setTitle("Vui lòng đăng nhập");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage("Để tiếp tục xem thông tin bạn cần phải đăng nhập!");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentToi = new Intent(ProductPage.this, LogInActivity.class);
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