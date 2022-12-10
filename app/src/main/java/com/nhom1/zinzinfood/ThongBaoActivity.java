package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

import com.nhom1.DatabaseHelpers.CartDatabase;
import com.nhom1.DatabaseHelpers.ThongBaoDatabase;
import com.nhom1.adapters.ProductCartAdapter;
import com.nhom1.adapters.ThongBaoAdapter;
import com.nhom1.models.ThongBao;
import com.nhom1.zinzinfood.databinding.ActivityThongBaoBinding;

import java.util.ArrayList;
import java.util.List;

public class ThongBaoActivity extends AppCompatActivity {
    ActivityThongBaoBinding binding;

    ThongBaoAdapter adapter;
    List<ThongBao> ThongBaoList;
    ThongBaoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_thong_bao);
        binding = ActivityThongBaoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createData();
        loadData();
        addEvents();
    }

    private void createData() {
        db = new ThongBaoDatabase(ThongBaoActivity.this);
        db.createData();

    }
    private void loadData() {
        //init adapter
        ThongBaoList = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + ThongBaoDatabase.TBL_NAME);
        while (c.moveToNext()){
            ThongBaoList.add(new ThongBao(c.getInt(0), c.getString(1), c.getString(2), c.getString(3)));
        }
        c.close();
        adapter = new ThongBaoAdapter(ThongBaoActivity.this, R.layout.items_thongbao, ThongBaoList);
        binding.lvThongBao.setAdapter(adapter);
    }
    private void addEvents() {
    }
}