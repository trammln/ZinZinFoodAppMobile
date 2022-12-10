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

import com.nhom1.DatabaseHelpers.VoucherDatabase;
import com.nhom1.adapters.VoucherAdapter;
import com.nhom1.models.Product;
import com.nhom1.models.Voucher;
import com.nhom1.zinzinfood.databinding.ActivityVoucherpageBinding;

import java.util.ArrayList;

public class VoucherPage extends AppCompatActivity {

    ActivityVoucherpageBinding binding;
    VoucherDatabase db;
    VoucherAdapter adapter;
    ArrayList<Voucher>vouchers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_voucherpage);
        binding = ActivityVoucherpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createDb();
        loadData();
        addEvents();
    }


    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoucherPage.this, Homepage.class);
                startActivity(intent);
            }
        });
        binding.imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0) {
                    batDangNhap();
                }
                else
                {
                    Intent intent = new Intent(VoucherPage.this, CartActivity.class);
                    startActivity(intent);
                }
            }
        });
        binding.imvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoucherPage.this, Homepage.class);
                startActivity(intent);
            }
        });
        binding.txtMyVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0) {
                    batDangNhap();
                }
                else
                {
                    Intent intent = new Intent(VoucherPage.this, VoucherCuaToi.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void loadData() {
        vouchers = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + VoucherDatabase.TBL_NAME);
        while (c.moveToNext())
        {
            vouchers.add(new Voucher(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
        }
        c.close();
        adapter = new VoucherAdapter(VoucherPage.this, R.layout.listview_voucherpage, vouchers);
        binding.lvVouchers.setAdapter(adapter);
    }

    private void createDb() {
        db = new VoucherDatabase(VoucherPage.this);
        db.createSampleData();
    }

    public void luuVoucher(Voucher v) {
        if(SplashActivity.EmailHoacPhone.length()==0) {
            batDangNhap();
        }
        else
        {
            Dialog dialog = new Dialog(VoucherPage.this);
            dialog.setContentView(R.layout.dialog_luuvoucher);
            Button btnLuu, btnCancel;
            btnLuu = dialog.findViewById(R.id.btn_Luu);
            btnLuu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(VoucherPage.this, "Đã lưu voucher", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                binding.lvVouchers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(VoucherPage.this, VoucherCuaToi.class);

                        Voucher item = vouchers.get(i);
                        intent.putExtra("voucherInf", item);
                        intent.putExtra("name", item.getVoucherName());
                        intent.putExtra("condition", item.getVoucherCondition());
                        intent.putExtra("time", item.getVoucherTime());
                        intent.putExtra("note", item.getVoucherNote());

                        startActivity(intent);
                    }
                });
                }
            });
            btnCancel = dialog.findViewById(R.id.btn_Cancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    public void dungVoucher(Voucher v) {
        if(SplashActivity.EmailHoacPhone.length()==0) {
            batDangNhap();
        }
        else
        {
            Intent intent = new Intent(VoucherPage.this, PaymentActitvity.class);
            startActivity(intent);
        }
    }

    private void batDangNhap() {
        AlertDialog.Builder builder = new AlertDialog.Builder(VoucherPage.this);
        builder.setTitle("Vui lòng đăng nhập");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage("Để tiếp tục xem thông tin bạn cần phải đăng nhập!");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentToi = new Intent(VoucherPage.this, LogInActivity.class);
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