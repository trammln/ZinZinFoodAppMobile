package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.nhom1.DatabaseHelpers.ChoThanhToanDatabase;
import com.nhom1.DatabaseHelpers.DangGiaoDatabase;
import com.nhom1.adapters.ChoThanhToanAdapter;
import com.nhom1.adapters.DangGiaoAdapter;
import com.nhom1.models.DonMua;
import com.nhom1.zinzinfood.databinding.ActivityDanggiaopageBinding;

import java.util.ArrayList;

public class DangGiaoPage extends AppCompatActivity {

    ActivityDanggiaopageBinding binding;
    DangGiaoDatabase db;
    DangGiaoAdapter adapter;
    ArrayList<DonMua>donMuas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_danggiaopage);
        binding= ActivityDanggiaopageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        createDb();
        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangGiaoPage.this, Homepage.class);
                startActivity(intent);
            }
        });

        binding.imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangGiaoPage.this, CartActivity.class);
                startActivity(intent);
            }
        });

        binding.txtDaGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangGiaoPage.this, DonMuaPage.class);
                startActivity(intent);
            }
        });

        binding.txtChoThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangGiaoPage.this, DonMuaChoThanhToan.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        donMuas = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + DangGiaoDatabase.TBL_NAME);
        while (c.moveToNext())
        {
            donMuas.add(new DonMua(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getString(6), c.getString(7)));
        }
        c.close();
        adapter = new DangGiaoAdapter(DangGiaoPage.this, R.layout.listview_danggiao, donMuas);
        binding.lvDangGiao.setAdapter(adapter);
    }

    private void createDb() {
        db = new DangGiaoDatabase(DangGiaoPage.this);
        db.createSampleData();
    }
}