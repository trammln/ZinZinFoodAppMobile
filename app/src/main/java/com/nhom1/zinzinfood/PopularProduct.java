package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.nhom1.DatabaseHelpers.NewDatabase;
import com.nhom1.DatabaseHelpers.PopularDatabase;
import com.nhom1.adapters.NewAdapter;
import com.nhom1.adapters.PopularAdapter;
import com.nhom1.models.Product;
import com.nhom1.zinzinfood.databinding.ActivityPopularproductBinding;

import java.util.ArrayList;

public class PopularProduct extends AppCompatActivity {

    ActivityPopularproductBinding binding;
    PopularDatabase db;
    PopularAdapter adapter;
    ArrayList<Product>products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_popularproduct);
        binding = ActivityPopularproductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createDb();
        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.gvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PopularProduct.this, DetailProductPage.class);
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
                Intent intent = new Intent(PopularProduct.this, Homepage.class);
                startActivity(intent);
            }
        });

        binding.imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularProduct.this, CartActivity.class);
                startActivity(intent);
            }
        });

        binding.txtArrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularProduct.this, ArrangeProduct.class);
                startActivity(intent);
            }
        });

        binding.txtNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularProduct.this, NewProduct.class);
                startActivity(intent);
            }
        });

        binding.txtPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularProduct.this, PopularProduct.class);
                startActivity(intent);
            }
        });

        binding.txtAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularProduct.this, ProductPage.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        products = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + PopularDatabase.TBL_NAME);
        while (c.moveToNext())
        {
            products.add(new Product(c.getInt(0), c.getString(1), c.getInt(2), c.getDouble(3), c.getInt(4), c.getString(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9)));
        }
        c.close();
        adapter = new PopularAdapter(PopularProduct.this, R.layout.gridview_productpage, products);
        binding.gvProducts.setAdapter(adapter);
    }

    private void createDb() {
        db = new PopularDatabase(PopularProduct.this);
        db.createSampleData();
    }
}