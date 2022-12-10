package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nhom1.DatabaseHelpers.UserDatabase;
import com.nhom1.models.User;
import com.nhom1.zinzinfood.databinding.ActivityDoiPhoneBinding;

import java.util.ArrayList;

public class DoiPhone extends AppCompatActivity {
    ActivityDoiPhoneBinding binding;
    UserDatabase db;
    ArrayList<User> users;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_doi_phone);
        binding=ActivityDoiPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();

        addEvents();

    }

    private void OpenDialogOTP() {
        Dialog dialog=new Dialog(DoiPhone.this);
        dialog.setContentView(R.layout.dialog_xacthucotp);

        Button btnOk,btnGuilaima;
        EditText edtMa;

        btnOk=dialog.findViewById(R.id.btn_OkMa);
        edtMa=dialog.findViewById(R.id.edt_Ma);
        btnGuilaima=dialog.findViewById(R.id.btn_GuiLaiMa);

        dialog.show();

        btnGuilaima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DoiPhone.this, "Đã gửi lại mã!", Toast.LENGTH_LONG).show();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtMa.getText().toString().length()==6){

                    Toast.makeText(DoiPhone.this, "Đổi số điện thoại thành công!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    Intent intent = new Intent(DoiPhone.this, LogInActivity.class);
                    intent.putExtra("PhoneNumb",binding.edtPhoneMoi.getText().toString());
                    intent.putExtra("Password",user.getPassword());
                    startActivity(intent);
                }else
                    Toast.makeText(DoiPhone.this, "Vui lòng nhập đúng mã OTP", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private void loadData() {
        db = new UserDatabase(DoiPhone.this);
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
                Intent intent = new Intent(DoiPhone.this, subpolicy04.class);
                startActivity(intent);
            }
        });

        binding.imvShowPhoneCu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.imvShowPhoneCu.getTag() == null || binding.imvShowPhoneCu.getTag().equals("visibility_off")) {
                    binding.imvShowPhoneCu.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.edtPhoneCu.setInputType(InputType.TYPE_CLASS_TEXT);
                    binding.imvShowPhoneCu.setTag("visibility");
                } else {
                    binding.imvShowPhoneCu.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.edtPhoneCu.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.imvShowPhoneCu.setTag("visibility_off");
                }
            }
        });

        binding.imvShowPhoneMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.imvShowPhoneMoi.getTag() == null || binding.imvShowPhoneMoi.getTag().equals("visibility_off")) {
                    binding.imvShowPhoneMoi.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.edtPhoneMoi.setInputType(InputType.TYPE_CLASS_TEXT);
                    binding.imvShowPhoneMoi.setTag("visibility");
                } else {
                    binding.imvShowPhoneMoi.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.edtPhoneMoi.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.imvShowPhoneMoi.setTag("visibility_off");
                }
            }
        });


        binding.btnResetPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edtPhoneMoi.getText().length() == 0 || binding.edtPhoneCu.getText().length() == 0) {
                    Toast.makeText(DoiPhone.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else if (IsPhone(binding.edtPhoneMoi.getText().toString())&&IsPhone(binding.edtPhoneCu.getText().toString())) {
                    if (binding.edtPhoneCu.getText().toString().equals(user.getPhoneNumb())) {

                        db = new UserDatabase(DoiPhone.this);
                        db.execSql("UPDATE "+ UserDatabase.TBL_USER_NAME+" SET "+
                                UserDatabase.COL_USER_PHONE+" = '" +
                                binding.edtPhoneMoi.getText().toString()+"' " +
                                "WHERE "+ UserDatabase.COL_USER_EMAIL+ "='" +
                                SplashActivity.EmailHoacPhone+"'"+ " OR "+ UserDatabase.COL_USER_PHONE+ "='" +
                                SplashActivity.EmailHoacPhone+"'" );
                        OpenDialogOTP();
                    } else {
                        Toast.makeText(DoiPhone.this, "Đổi số điện thoại thất bại! Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DoiPhone.this, "Số điện thoại không hợp lệ!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public boolean IsPhone(String number) {
        String regex = "^[+]?[0-9]{8,15}$";
        if(!number.matches(regex)) {
            return false;
        }else{
            return true;
        }
    }
}