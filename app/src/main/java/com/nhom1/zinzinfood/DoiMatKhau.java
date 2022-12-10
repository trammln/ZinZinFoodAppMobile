package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.nhom1.DatabaseHelpers.UserDatabase;
import com.nhom1.models.User;
import com.nhom1.zinzinfood.databinding.ActivityDoiMatKhauBinding;
import com.nhom1.zinzinfood.databinding.ActivityForgotPassword2Binding;

import java.util.ArrayList;

public class DoiMatKhau extends AppCompatActivity {
    ActivityDoiMatKhauBinding binding;
    UserDatabase db;
    ArrayList<User> users;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_doi_mat_khau);
        binding = ActivityDoiMatKhauBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
        addEvents();

    }

    private void loadData() {
        db = new UserDatabase(DoiMatKhau.this);
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

        user=users.get(0);
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.imvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoiMatKhau.this, subpolicy04.class);
                startActivity(intent);
            }
        });

        binding.imvShowPasswordCu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.imvShowPasswordCu.getTag() == null || binding.imvShowPasswordCu.getTag().equals("visibility_off")) {
                    binding.imvShowPasswordCu.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.edtMatKhauCu.setInputType(InputType.TYPE_CLASS_TEXT);
                    binding.imvShowPasswordCu.setTag("visibility");
                } else {
                    binding.imvShowPasswordCu.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.edtMatKhauCu.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.imvShowPasswordCu.setTag("visibility_off");
                }
            }
        });

        binding.imvShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.imvShowPassword.getTag() == null || binding.imvShowPassword.getTag().equals("visibility_off")) {
                    binding.imvShowPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.edtMatKhauMoi.setInputType(InputType.TYPE_CLASS_TEXT);
                    binding.imvShowPassword.setTag("visibility");
                } else {
                    binding.imvShowPassword.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.edtMatKhauMoi.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.imvShowPassword.setTag("visibility_off");
                }
            }
        });

        binding.imvShowPassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.imvShowPassword2.getTag() == null || binding.imvShowPassword2.getTag().equals("visibility_off")) {
                    binding.imvShowPassword2.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.edtRetypeMatKhau.setInputType(InputType.TYPE_CLASS_TEXT);
                    binding.imvShowPassword2.setTag("visibility");
                } else {
                    binding.imvShowPassword2.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.edtRetypeMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.imvShowPassword2.setTag("visibility_off");
                }
            }
        });

        binding.btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edtMatKhauMoi.getText().length() == 0 || binding.edtRetypeMatKhau.getText().length() == 0) {
                    Toast.makeText(DoiMatKhau.this, "Vui lòng nhập đủ thông tin mật khẩu mới", Toast.LENGTH_SHORT).show();
                } else if (binding.edtMatKhauMoi.getText().length() >= 8 && binding.edtRetypeMatKhau.getText().length() >= 8) {
                    if (binding.edtMatKhauMoi.getText().toString().equals(binding.edtRetypeMatKhau.getText().toString())
                            &&binding.edtMatKhauCu.getText().toString().equals(user.getPassword())) {

                        db = new UserDatabase(DoiMatKhau.this);
                        db.execSql("UPDATE "+ UserDatabase.TBL_USER_NAME+" SET "+
                                UserDatabase.COL_USER_PASS+" = '" +
                                binding.edtMatKhauMoi.getText().toString()+"' " +
                                "WHERE "+ UserDatabase.COL_USER_EMAIL+ "='" +
                                SplashActivity.EmailHoacPhone+"'"+ " OR "+ UserDatabase.COL_USER_PHONE+ "='" +
                                SplashActivity.EmailHoacPhone+"'" );

                        Toast.makeText(DoiMatKhau.this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(DoiMatKhau.this, LogInActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(DoiMatKhau.this, "Đổi mật khẩu thất bại! Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DoiMatKhau.this, "Mật khẩu tài khoản phải đủ mạnh và có ít nhất 8 ký tự", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}