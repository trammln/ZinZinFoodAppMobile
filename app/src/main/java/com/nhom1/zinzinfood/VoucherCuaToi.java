package com.nhom1.zinzinfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nhom1.DatabaseHelpers.CartDatabase;
import com.nhom1.DatabaseHelpers.ToiVoucher;
import com.nhom1.DatabaseHelpers.VoucherDatabase;
import com.nhom1.adapters.ToiVoucherAdapter;
import com.nhom1.models.Voucher;
import com.nhom1.zinzinfood.databinding.ActivityVouchercuatoiBinding;

import java.util.ArrayList;

public class VoucherCuaToi extends AppCompatActivity {

    ActivityVouchercuatoiBinding binding;
    ToiVoucher db;
    ToiVoucherAdapter adapter;
    ArrayList<Voucher> voucherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_vouchercuatoi);
        binding = ActivityVouchercuatoiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createDb();
        loadData();
        addEvents();
        getData();
    }

    private void getData() {
//
//          Intent intent = getIntent();
//          Voucher v = (Voucher) intent.getSerializableExtra("voucherInf");

//        String name = intent.getStringExtra("name");
//        String condition = intent.getStringExtra("condition");
//        String time = intent.getStringExtra("time");
//        String note = intent.getStringExtra("note");

//        String name= v.getVoucherName();
//        String condition = v.getVoucherCondition();
//        String time = v.getVoucherTime();
//        String note = v.getVoucherNote();

//        db.execSql("INSERT INTO " + ToiVoucher.TBL_NAME + " VALUES(null, '" + name + "', '" + condition + "', '" + time + "', '" + note + "')");
//
//        loadData();
    }


    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoucherCuaToi.this, VoucherPage.class);
                startActivity(intent);
            }
        });
        binding.imvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoucherCuaToi.this, Homepage.class);
                startActivity(intent);
            }
        });
        binding.imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoucherCuaToi.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {

        voucherList = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + ToiVoucher.TBL_NAME);

        while (c.moveToNext())
        {
            voucherList.add(new Voucher(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
        }

        c.close();
        adapter = new ToiVoucherAdapter(VoucherCuaToi.this, R.layout.listview_vouchercuatoi, voucherList);
        binding.lvMyVouchers.setAdapter(adapter);
    }

    private void createDb() {
        db = new ToiVoucher(VoucherCuaToi.this);
        db.createSampleData();
    }

    public void xoaVoucher(Voucher v) {
        Dialog dialog=new Dialog(VoucherCuaToi.this);
        dialog.setContentView(R.layout.dialog_deletevoucher);

        Button btnDelete, btnCancel;
        btnDelete = dialog.findViewById(R.id.btn_Xoa);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSql("DELETE FROM "+ToiVoucher.TBL_NAME+" WHERE "+
                        ToiVoucher.COL_ID+"="+v.getVoucherId());

                loadData();
                dialog.dismiss();
                Toast.makeText(VoucherCuaToi.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
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

    public void dungVoucher(Voucher v) {
            Intent intent = new Intent(VoucherCuaToi.this, PaymentActitvity.class);
            startActivity(intent);
    }

}