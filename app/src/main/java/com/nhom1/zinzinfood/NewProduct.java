package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.nhom1.DatabaseHelpers.NewDatabase;
import com.nhom1.DatabaseHelpers.ProductDatabase;
import com.nhom1.adapters.NewAdapter;
import com.nhom1.adapters.ProductAdapter;
import com.nhom1.models.Product;
import com.nhom1.zinzinfood.databinding.ActivityNewproductBinding;

import java.util.ArrayList;

public class NewProduct extends AppCompatActivity {

    ActivityNewproductBinding binding;
    NewDatabase db;
    NewAdapter adapter;
    ArrayList<Product>products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_newproduct);
        binding = ActivityNewproductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createDb();
        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.gvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NewProduct.this, DetailProductPage.class);
                Product item = products.get(i);

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
                Intent intent = new Intent(NewProduct.this, Homepage.class);
                startActivity(intent);
            }
        });

        binding.imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewProduct.this, CartActivity.class);
                startActivity(intent);
            }
        });

        binding.txtArrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewProduct.this, ArrangeProduct.class);
                startActivity(intent);
            }
        });

        binding.txtNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewProduct.this, NewProduct.class);
                startActivity(intent);
            }
        });

        binding.txtPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewProduct.this, PopularProduct.class);
                startActivity(intent);
            }
        });

        binding.txtAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewProduct.this, ProductPage.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        products = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + NewDatabase.TBL_NAME);
        while (c.moveToNext())
        {
            products.add(new Product(c.getInt(0), c.getString(1), c.getInt(2), c.getDouble(3), c.getInt(4), c.getString(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9)));
        }
        c.close();
        adapter = new NewAdapter(NewProduct.this, R.layout.gridview_productpage, products);
        binding.gvProducts.setAdapter(adapter);
    }

    private void createDb() {
        db = new NewDatabase(NewProduct.this);
        db.createSampleData();
    }
}