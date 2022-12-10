package com.nhom1.zinzinfood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom1.DatabaseHelpers.UserDatabase;
import com.nhom1.models.User;
import com.nhom1.zinzinfood.databinding.ActivityForgotPassword2Binding;

import java.util.ArrayList;

public class ForgotPasswordActivity2 extends AppCompatActivity {

    ActivityForgotPassword2Binding binding;
    UserDatabase db;
    ArrayList<User> users;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forgot_password2);
    binding = ActivityForgotPassword2Binding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    loadData();
    addEvents();
        //Tram
    }

    private void loadData() {
        db = new UserDatabase(ForgotPasswordActivity2.this);
        db.createSampleData();
        users = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + UserDatabase.TBL_USER_NAME );
        while (c.moveToNext()){
            users.add(new User(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6),c.getString(7)));
        }
        c.close();
        //Tram
    }

    private void addEvents() {
        binding.imvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity2.this, subpolicy04.class);
                startActivity(intent);
            }
        });

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.imvShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.imvShowPassword.getTag() == null || binding.imvShowPassword.getTag().equals("visibility_off")){
                    binding.imvShowPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT);
                    binding.imvShowPassword.setTag("visibility");
                }else{
                    binding.imvShowPassword.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.imvShowPassword.setTag("visibility_off");
                }}
        });

        binding.imvShowPassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.imvShowPassword2.getTag() == null || binding.imvShowPassword2.getTag().equals("visibility_off")){
                    binding.imvShowPassword2.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.edtRetypeMatKhau.setInputType(InputType.TYPE_CLASS_TEXT);
                    binding.imvShowPassword2.setTag("visibility");
                }else{
                    binding.imvShowPassword2.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.edtRetypeMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.imvShowPassword2.setTag("visibility_off");
                }}
        });

        binding.btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.edtMatKhau.getText().length() == 0 || binding.edtRetypeMatKhau.getText().length() == 0) {
                    Toast.makeText(ForgotPasswordActivity2.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                } else if (binding.edtMatKhau.getText().length() >= 8 && binding.edtRetypeMatKhau.getText().length() >= 8) {
                    if (binding.edtMatKhau.getText().toString().equals(binding.edtRetypeMatKhau.getText().toString())) {
                        Intent intent1 = getIntent();
                        String username= intent1.getStringExtra("user");
                        if (!db.checkusernamepassword(username, binding.edtMatKhau.getText().toString())) {
                            db.execSql("UPDATE " + UserDatabase.TBL_USER_NAME + " SET " +
                                    UserDatabase.COL_USER_PASS + " = '" +
                                    binding.edtMatKhau.getText().toString() + "' " +
                                    "WHERE " + UserDatabase.COL_USER_EMAIL + " = '" +
                                    username + "'" + " OR " + UserDatabase.COL_USER_PHONE + " = '" + username + "'");
                            Toast.makeText(ForgotPasswordActivity2.this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPasswordActivity2.this, LogInActivity.class);
                            intent.putExtra("username", username);
                            intent.putExtra("newpassword", binding.edtMatKhau.getText().toString());
                            startActivity(intent);
                        } else {
                            Toast.makeText(ForgotPasswordActivity2.this, "Mật khẩu mới phải khác với mật khẩu trước đây.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ForgotPasswordActivity2.this, "Đổi mật khẩu thất bại! Vui lòng kiểm tra lại thông tin.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ForgotPasswordActivity2.this, "Mật khẩu tài khoản phải đủ mạnh và có ít nhất 8 ký tự.", Toast.LENGTH_SHORT).show();}
            }
        });
        //Tram
    }
}