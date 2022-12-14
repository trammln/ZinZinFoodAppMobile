package com.nhom1.zinzinfood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom1.DatabaseHelpers.UserDatabase;
import com.nhom1.models.User;
import com.nhom1.zinzinfood.databinding.ActivityForgotPassword1Binding;

import java.util.ArrayList;

public class ForgotPasswordActivity1 extends AppCompatActivity {

    ActivityForgotPassword1Binding binding;
    UserDatabase db;
    ArrayList<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forgot_password1);
        binding = ActivityForgotPassword1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();
        addEvents();
        //Tram
    }

    private void loadData() {
        db = new UserDatabase(ForgotPasswordActivity1.this);
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
                Intent intent = new Intent(ForgotPasswordActivity1.this, subpolicy04.class);
                startActivity(intent);
            }
        });

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.edtUsername.getText().length() == 0) {
                    Toast.makeText(ForgotPasswordActivity1.this, "Vui l??ng nh???p th??ng tin t??i kho???n ????? ?????n b?????c ti???p theo.", Toast.LENGTH_SHORT).show();
                } else if (IsEmail(binding.edtUsername.getText().toString()) || IsPhone(binding.edtUsername.getText().toString())) {
                    if (db.checkuseremail(binding.edtUsername.getText().toString()) || db.checkuserphone(binding.edtUsername.getText().toString())) {
                        Intent intent = new Intent(ForgotPasswordActivity1.this, AuthenticationActivity.class);
                        intent.putExtra("Username", binding.edtUsername.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(ForgotPasswordActivity1.this, "Kh??ng t???n t???i t??i kho???n t????ng ???ng v???i th??ng tin c???a b???n.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ForgotPasswordActivity1.this, "Vui l??ng ki???m tra l???i th??ng tin.", Toast.LENGTH_SHORT).show();
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