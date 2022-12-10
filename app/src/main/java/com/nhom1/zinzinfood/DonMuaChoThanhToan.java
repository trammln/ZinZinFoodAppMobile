package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.nhom1.DatabaseHelpers.ChoThanhToanDatabase;
import com.nhom1.DatabaseHelpers.DangGiaoDatabase;
import com.nhom1.DatabaseHelpers.DonMuaDatabase;
import com.nhom1.adapters.ChoThanhToanAdapter;
import com.nhom1.adapters.DonMuaAdapter;
import com.nhom1.models.DonMua;
import com.nhom1.zinzinfood.databinding.ActivityDonmuachothanhtoanBinding;

import java.util.ArrayList;

public class DonMuaChoThanhToan extends AppCompatActivity {

    ActivityDonmuachothanhtoanBinding binding;
    ChoThanhToanDatabase db;
    ChoThanhToanAdapter adapter;
    ArrayList<DonMua>donMuas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_donmuachothanhtoan);
         binding = ActivityDonmuachothanhtoanBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());

         createDb();
         loadData();
         addEvents();
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonMuaChoThanhToan.this, Homepage.class);
                startActivity(intent);
            }
        });

        binding.imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonMuaChoThanhToan.this, CartActivity.class);
                startActivity(intent);
            }
        });

        binding.txtDaGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonMuaChoThanhToan.this, DonMuaPage.class);
                startActivity(intent);
            }
        });

        binding.txtChoThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonMuaChoThanhToan.this, DonMuaChoThanhToan.class);
                startActivity(intent);
            }
        });

        binding.txtDangGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonMuaChoThanhToan.this, DangGiaoPage.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        donMuas = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + ChoThanhToanDatabase.TBL_NAME);
        while (c.moveToNext())
        {
            donMuas.add(new DonMua(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getString(6), c.getString(7)));
        }
        c.close();
        adapter = new ChoThanhToanAdapter(DonMuaChoThanhToan.this, R.layout.listview_chothanhtoan, donMuas);
        binding.lvChoThanhToan.setAdapter(adapter);

    }

    private void createDb() {
        db = new ChoThanhToanDatabase(DonMuaChoThanhToan.this);
        db.createSampleData();
    }

}