package com.nhom1.zinzinfood;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nhom1.DatabaseHelpers.UserDatabase;
import com.nhom1.models.User;
import com.nhom1.zinzinfood.databinding.ActivityHoSoCuaToiBinding;
import com.nhom1.zinzinfood.databinding.ActivityLogInBinding;

import java.util.ArrayList;

public class HoSoCuaToi extends AppCompatActivity {
    ActivityHoSoCuaToiBinding binding;
    ArrayList<User> users;
    UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ho_so_cua_toi);
        binding = ActivityHoSoCuaToiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
        addEvents();

    }

    private void loadData() {
        db = new UserDatabase(HoSoCuaToi.this);
        db.createSampleData();

        String username=SplashActivity.EmailHoacPhone;
        users=new ArrayList<>();
        Cursor c=db.getData("SELECT * FROM " + UserDatabase.TBL_USER_NAME
                + " WHERE " + UserDatabase.COL_USER_EMAIL + "='"+username + "' OR " + UserDatabase.COL_USER_PHONE
                + "='"+ username +"'" );
        while (c.moveToNext()){
            users.add(new User(c.getInt(0),c.getString(1),
                    c.getString(2),c.getString(3),c.getString(4),
                    c.getString(5),c.getString(6),c.getString(7)));
        }
        c.close();

        User user=users.get(0);


        binding.txtPhone.setText(user.getPhoneNumb());
        binding.txtPass.setText(user.getPassword());
        binding.txtNgaySinh.setText(user.getDOB());
        binding.txtGioiTinh.setText(user.getSEX());
        binding.txtEmail.setText(user.getEmail());
        binding.txtHovaten.setText(user.getUserName());

    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HoSoCuaToi.this, Toi.class);
                startActivity(intent);
                //finish();
            }
        });

        binding.imvLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HoSoCuaToi.this, Toi.class);
                startActivity(intent);
            }
        });

        binding.linearMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HoSoCuaToi.this, DoiMatKhau.class);
                startActivity(intent);
            }
        });
        binding.linearSuaPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HoSoCuaToi.this, DoiPhone.class);
                startActivity(intent);
            }
        });
        binding.linearSuaEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HoSoCuaToi.this, DoiEmail.class);
                startActivity(intent);
            }
        });

        binding.linearSuaTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(HoSoCuaToi.this);
                dialog.setContentView(R.layout.dialog_suaten);

                Button btnLuu,btnCancel;
                EditText edtTenMoi;

                btnLuu=dialog.findViewById(R.id.btn_Luu);
                edtTenMoi=dialog.findViewById(R.id.edt_TenMoi);
                btnCancel=dialog.findViewById(R.id.btn_Cancel);

                edtTenMoi.setText(binding.txtHovaten.getText().toString());

                dialog.show();

                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        db.execSql("UPDATE "+UserDatabase.TBL_USER_NAME+" SET "+
                                UserDatabase.COL_USER_NAME+" = '" +
                                edtTenMoi.getText().toString()+"' " +
                                "WHERE "+ UserDatabase.COL_USER_EMAIL+ "='" +
                                binding.txtEmail.getText().toString()+"'");
                        loadData();
                        dialog.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        binding.linearSuaGioiTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(HoSoCuaToi.this);
                dialog.setContentView(R.layout.dialog_doigioitinh);
                dialog.show();

                Button btnNam,btnNu;

                btnNam=dialog.findViewById(R.id.btn_Nam);
                btnNu=dialog.findViewById(R.id.btn_Nu);

                btnNam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        db.execSql("UPDATE "+UserDatabase.TBL_USER_NAME+" SET "+
                                UserDatabase.COL_USER_SEX+" = '" +
                                btnNam.getText().toString()+"' " +
                                "WHERE "+ UserDatabase.COL_USER_EMAIL+ "='" +
                                binding.txtEmail.getText().toString()+"'");
                        loadData();
                        dialog.dismiss();
                    }
                });

                btnNu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.execSql("UPDATE "+UserDatabase.TBL_USER_NAME+" SET "+
                                UserDatabase.COL_USER_SEX+" = '" +
                                btnNu.getText().toString()+"' " +
                                "WHERE "+ UserDatabase.COL_USER_EMAIL+ "='" +
                                binding.txtEmail.getText().toString()+"'");
                        loadData();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        binding.linearSuaNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(HoSoCuaToi.this);
                dialog.setContentView(R.layout.dialog_suangaysinh);

                Button btnLuu,btnCancel;
                EditText edtDobMoi;

                btnLuu=dialog.findViewById(R.id.btn_Luu);
                edtDobMoi=dialog.findViewById(R.id.edt_DobMoi);
                btnCancel=dialog.findViewById(R.id.btn_Cancel);

                edtDobMoi.setText(binding.txtNgaySinh.getText().toString());

                dialog.show();

                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        db.execSql("UPDATE "+UserDatabase.TBL_USER_NAME+" SET "+
                                UserDatabase.COL_USER_DOB+" = '" +
                                edtDobMoi.getText().toString()+"' " +
                                "WHERE "+ UserDatabase.COL_USER_EMAIL+ "='" +
                                binding.txtEmail.getText().toString()+"'");
                        loadData();
                        dialog.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });


    }
}