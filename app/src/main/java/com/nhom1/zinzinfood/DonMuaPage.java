package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.nhom1.DatabaseHelpers.DonMuaDatabase;
import com.nhom1.DatabaseHelpers.ProductDatabase;
import com.nhom1.adapters.DonMuaAdapter;
import com.nhom1.adapters.ProductAdapter;
import com.nhom1.models.DonMua;
import com.nhom1.models.Product;
import com.nhom1.zinzinfood.databinding.ActivityDonmuapageBinding;

import java.util.ArrayList;

public class DonMuaPage extends AppCompatActivity {

    ActivityDonmuapageBinding binding;
    DonMuaDatabase db;
    DonMuaAdapter adapter;
    ArrayList<DonMua>donMuas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_donmuapage);
        binding = ActivityDonmuapageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createDb();
        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.txtChoThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonMuaPage.this, DonMuaChoThanhToan.class);
                startActivity(intent);
            }
        });

        binding.txtDangGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonMuaPage.this, DangGiaoPage.class);
                startActivity(intent);
            }
        });
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonMuaPage.this, Homepage.class);
                startActivity(intent);
            }
        });

        binding.imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonMuaPage.this, CartActivity.class);
                startActivity(intent);
            }
        });

    }

    //    String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME +" (" + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
//            COL_NAME+" VARCHAR(200), "+ COL_QUANTITY+" INTEGER, "+COL_PRICE+" INTEGER, "+COL_QUANTITYTOTAL+" INTEGER, "+ COL_TOTAL+" INTEGER,"+COL_DUE+" VARCHAR(200), "+COL_THUMB+" TEXT)";
//        sqLiteDatabase.execSQL(sql);
    private void loadData() {
        donMuas = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + DonMuaDatabase.TBL_NAME);
        while (c.moveToNext())
        {
            donMuas.add(new DonMua(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getString(6), c.getString(7)));
        }
        c.close();
        adapter = new DonMuaAdapter(DonMuaPage.this, R.layout.listview_donmua, donMuas);
        binding.lvDonMua.setAdapter(adapter);
    }

    private void createDb() {
        db = new DonMuaDatabase(DonMuaPage.this);
        db.createSampleData();
    }
}