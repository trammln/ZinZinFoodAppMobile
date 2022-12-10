package com.nhom1.zinzinfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nhom1.DatabaseHelpers.UserDatabase;
import com.nhom1.models.User;
import com.nhom1.zinzinfood.databinding.ActivitySignUpBinding;

import java.util.ArrayList;


public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    UserDatabase db;
    ArrayList<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.edtEmail.requestFocus();
        loadData();
        addEvents();
        //Tram
    }

    public void loadData() {
        db = new UserDatabase(SignUpActivity.this);
        users = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + UserDatabase.TBL_USER_NAME);
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
                Intent intent = new Intent(SignUpActivity.this, subpolicy04.class);
                startActivity(intent);
            }
        });

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edtEmail.getText().length()== 0 && binding.edtPhone.getText().length() == 0 && binding.edtMatKhau.getText().length() == 0 && binding.edtRetypeMatKhau.getText().length() == 0) {
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setTitle("Xác nhận thoát");
                    builder.setIcon(android.R.drawable.ic_dialog_info);
                    builder.setMessage("Bạn chắc chắn muốn thoát trang này không?");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
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

        binding.txtTabDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
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

        binding.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String email = binding.edtEmail.getText().toString();
                String phone = binding.edtPhone.getText().toString();
                String password = binding.edtMatKhau.getText().toString();
                String repassword = binding.edtRetypeMatKhau.getText().toString();

                if(email.length() == 0 || phone.length() == 0 || password.length() == 0 || repassword.length() == 0) {
                    Toast.makeText(SignUpActivity.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                } else if (password.length() >= 8 && repassword.length() >= 8) {
                    if (IsEmail(email) && IsPhone(phone) && password.equals(repassword)) {
                        if(!db.checkuseremail(email) && !db.checkuserphone(phone)){
                            db.execSql("INSERT INTO " + db.TBL_USER_NAME + " VALUES(null, 'User', 'Thành Viên', null, '" + phone + "', '" + email + "', '" + password + "', null)");
                            loadData();
                            Intent intent = new Intent(SignUpActivity.this, AuthenticationActivity.class);
                            intent.putExtra("Email", email);
                            intent.putExtra("PhoneNumb", phone);
                            intent.putExtra("Password", password);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Thông tin đã tồn tại! Vui lòng nhập thông tin khác để đăng ký.", Toast.LENGTH_SHORT).show(); }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Đăng ký thất bại! Vui lòng kiểm tra lại thông tin.", Toast.LENGTH_SHORT).show(); }
                } else {
                    Toast.makeText(SignUpActivity.this, "Mật khẩu tài khoản phải đủ mạnh và có ít nhất 8 ký tự.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Tram
    }

    public boolean IsEmail(String emailaddress) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(!emailaddress.matches(regex)) {
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

    @Override
    protected void onStart() {
        binding.edtEmail.setText("");
        binding.edtPhone.setText("");
        binding.edtMatKhau.setText("");
        binding.edtRetypeMatKhau.setText("");
        binding.edtEmail.requestFocus();
        super.onStart();
        //Tram
    }
}