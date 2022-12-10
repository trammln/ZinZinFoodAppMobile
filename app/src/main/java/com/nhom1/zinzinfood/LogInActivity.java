package com.nhom1.zinzinfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nhom1.DatabaseHelpers.UserDatabase;
import com.nhom1.models.User;
import com.nhom1.zinzinfood.databinding.ActivityLogInBinding;

import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {
    ActivityLogInBinding binding;
    UserDatabase db;
    ArrayList<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_log_in);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.edtUsername.requestFocus();
        loadData();
        addEvents();
        getData();
        //Tram
    }

    private void loadData() {
        db = new UserDatabase(LogInActivity.this);
        db.createSampleData();
        users = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + UserDatabase.TBL_USER_NAME );
        while (c.moveToNext()){
            users.add(new User(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6),c.getString(7)));
        }
        c.close();
        //Tram
    }


    private void getData() {
        Intent intent = getIntent();
        String email = intent.getStringExtra("Email");
        String phone = intent.getStringExtra("PhoneNumb");
        String mk = intent.getStringExtra("Password");
        String username = intent.getStringExtra("username");
        String newmk = intent.getStringExtra("newpassword");
        if (email != null || phone != null || mk != null) {
            if (email == null) {
                binding.edtMatKhau.setText(mk);
                binding.edtUsername.setText(phone);
            } else {
                binding.edtMatKhau.setText(mk);
                binding.edtUsername.setText(email);
            }
        } else {
            binding.edtMatKhau.setText(newmk);
            binding.edtUsername.setText(username);
        }
        //Tram
    }

    private void addEvents() {
        binding.imvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, subpolicy04.class);
                startActivity(intent);
            }
        });

        //Tram
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edtUsername.getText().length() == 0 && binding.edtMatKhau.getText().length() == 0) {
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                    builder.setTitle("Xác nhận thoát");
                    builder.setIcon(android.R.drawable.ic_dialog_info);
                    builder.setMessage("Bạn chắc chắn muốn thoát trang này không?");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(LogInActivity.this, SplashActivity.class);
                            startActivity(intent);
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
        });

        binding.txtTabDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        binding.txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, ForgotPasswordActivity1.class);
                startActivity(intent);
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

        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.edtUsername.getText().length() == 0 || binding.edtMatKhau.getText().length() == 0) { Toast.makeText(LogInActivity.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();}
                else if (binding.edtMatKhau.getText().length() >= 8) {
                    if (IsEmail(binding.edtUsername.getText().toString()) || IsPhone(binding.edtUsername.getText().toString())) {
                        if (db.checkusernamepassword(binding.edtUsername.getText().toString(),
                                binding.edtMatKhau.getText().toString()))
                        {
                            SplashActivity.EmailHoacPhone=binding.edtUsername.getText().toString();
                            Toast.makeText(LogInActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogInActivity.this, Homepage.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(LogInActivity.this, "Tài khoản không tồn tại! Vui lòng kiểm tra thông tin đăng nhập.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LogInActivity.this, "Đăng nhập thất bại! Vui lòng kiểm tra lại thông tin.", Toast.LENGTH_SHORT).show(); }
                } else {
                    Toast.makeText(LogInActivity.this, "Mật khẩu tài khoản phải đủ mạnh và có ít nhất 8 ký tự.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Tram
    }

    public boolean IsEmail(String email) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(!email.matches(regex)) {
            return false;
        }else{
            return true;
        }
        //Tram
    }

    public boolean IsPhone(String number) {
        String regex = "^[+]?[0-9]{8,15}$";
        if(!number.matches(regex)) {
            return false;
        }else{
            return true;
        }
        //Tram
    }
}