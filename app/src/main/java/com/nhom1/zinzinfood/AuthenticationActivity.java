package com.nhom1.zinzinfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nhom1.zinzinfood.databinding.ActivityAuthenticationBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class AuthenticationActivity extends AppCompatActivity {
    ActivityAuthenticationBinding binding;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_authentication);
    binding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    binding.txtResend.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        Intent intent = getIntent();
        user = intent.getStringExtra("Username");
        if (user == null) {
            user = intent.getStringExtra("Email");
        }
        binding.txtContent.setText("Chúng tôi đã gửi mã xác thực OTP đến " + user + ". Vui lòng nhập mã để xác thực.");
    addEvents();
        //Tram
    }

    private void addEvents() {
        binding.imvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthenticationActivity.this, subpolicy04.class);
                startActivity(intent);
            }
        });

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.edtOTP1.getText().length()==0 || binding.edtOTP2.getText().length()==0 || binding.edtOTP3.getText().length()==0 || binding.edtOTP4.getText().length()==0 || binding.edtOTP5.getText().length()==0 || binding.edtOTP6.getText().length()==0) {
                    Toast.makeText(AuthenticationActivity.this, "Mã xác thực không hợp lệ!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = getIntent();
                    user = intent.getStringExtra("Username");
                    if (user == null) {
                        String signupemail = intent.getStringExtra("Email");
                        String signupphone = intent.getStringExtra("PhoneNumb");
                        String signuppassword = intent.getStringExtra("Password");
                        Toast.makeText(AuthenticationActivity.this, "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(AuthenticationActivity.this, LogInActivity.class);
                        intent2.putExtra("Email", signupemail);
                        intent2.putExtra("PhoneNumb", signupphone);
                        intent2.putExtra("Password", signuppassword);
                        startActivity(intent2);
                    } else {
                        Intent intent1 = new Intent(AuthenticationActivity.this, ForgotPasswordActivity2.class);
                        intent1.putExtra("user", user);
                        startActivity(intent1);
                    }
                }
            }
        });
        //Tram
    }

    @Override
    protected void onStart() {
        new CountDownTimer(30000, 1000){
            public void onTick(long millisUntilFinished){
                NumberFormat f = new DecimalFormat("00");
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                binding.txtNewOTP.setText("Bạn có thể yêu cầu mã mới sau ");
                binding.txtResend.setText(f.format(min) + ":" + f.format(sec));
            }
            public void onFinish(){
                binding.txtNewOTP.setText("Bạn có thể yêu cầu mã mới. ");
                binding.txtResend.setText("Gửi lại OTP");
                binding.txtResend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AuthenticationActivity.this);
                        builder.setTitle("Thông báo");
                        builder.setIcon(android.R.drawable.ic_dialog_info);
                        builder.setMessage("Mã OTP mới sẽ được gửi đến bạn ngay! Vui lòng kiểm tra và nhập lại OTP mới");
                        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                binding.txtResend.setText("");
                                onStart();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
            }
        }.start();
        super.onStart();
        //Tram
    }
}