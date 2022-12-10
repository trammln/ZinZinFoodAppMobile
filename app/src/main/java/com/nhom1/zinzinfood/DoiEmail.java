package com.nhom1.zinzinfood;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom1.DatabaseHelpers.UserDatabase;
import com.nhom1.models.User;
import com.nhom1.zinzinfood.databinding.ActivityDoiEmailBinding;
import com.nhom1.zinzinfood.databinding.ActivityDoiPhoneBinding;

import java.util.ArrayList;

public class DoiEmail extends AppCompatActivity {
    ActivityDoiEmailBinding binding;
    UserDatabase db;
    ArrayList<User> users;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_doi_phone);
        binding=ActivityDoiEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();

        addEvents();

    }

    private void OpenDialogOTP() {
        Dialog dialog=new Dialog(DoiEmail.this);
        dialog.setContentView(R.layout.dialog_xacthucemail);

        Button btnOk,btnGuilaima;
        EditText edtMa;

        btnOk=dialog.findViewById(R.id.btn_OkMaEmail);
        edtMa=dialog.findViewById(R.id.edt_MaEmail);
        btnGuilaima=dialog.findViewById(R.id.btn_GuiLaiMaEmail);

        dialog.show();

        btnGuilaima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DoiEmail.this, "Đã gửi lại mã!", Toast.LENGTH_LONG).show();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtMa.getText().toString().length()==6){

                    Toast.makeText(DoiEmail.this, "Đổi Email thành công!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    Intent intent = new Intent(DoiEmail.this, LogInActivity.class);
                    intent.putExtra("Email",binding.edtEmailMoi.getText().toString());
                    intent.putExtra("Password",user.getPassword());
                    startActivity(intent);
                }else
                    Toast.makeText(DoiEmail.this, "Vui lòng nhập đúng mã xác thực", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private void loadData() {
        db = new UserDatabase(DoiEmail.this);
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

        binding.imvShowEmailCu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.imvShowEmailCu.getTag() == null || binding.imvShowEmailCu.getTag().equals("visibility_off")) {
                    binding.imvShowEmailCu.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.edtEmailCu.setInputType(InputType.TYPE_CLASS_TEXT);
                    binding.imvShowEmailCu.setTag("visibility");
                } else {
                    binding.imvShowEmailCu.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.edtEmailCu.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.imvShowEmailCu.setTag("visibility_off");
                }
            }
        });

        binding.imvShowEmailMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.imvShowEmailMoi.getTag() == null || binding.imvShowEmailMoi.getTag().equals("visibility_off")) {
                    binding.imvShowEmailMoi.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.edtEmailMoi.setInputType(InputType.TYPE_CLASS_TEXT);
                    binding.imvShowEmailMoi.setTag("visibility");
                } else {
                    binding.imvShowEmailMoi.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.edtEmailMoi.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.imvShowEmailMoi.setTag("visibility_off");
                }
            }
        });

        binding.imvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoiEmail.this, subpolicy04.class);
                startActivity(intent);
            }
        });

        binding.btnResetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edtEmailMoi.getText().length() == 0 || binding.edtEmailCu.getText().length() == 0) {
                    Toast.makeText(DoiEmail.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else if (IsEmail(binding.edtEmailCu.getText().toString())&&IsEmail(binding.edtEmailMoi.getText().toString())) {
                    if (binding.edtEmailCu.getText().toString().equals(user.getEmail())) {

                        db = new UserDatabase(DoiEmail.this);
                        db.execSql("UPDATE "+ UserDatabase.TBL_USER_NAME+" SET "+
                                UserDatabase.COL_USER_EMAIL+" = '" +
                                binding.edtEmailMoi.getText().toString()+"' " +
                                "WHERE "+ UserDatabase.COL_USER_EMAIL+ "='" +
                                SplashActivity.EmailHoacPhone+"'"+ " OR "+ UserDatabase.COL_USER_PHONE+ "='" +
                                SplashActivity.EmailHoacPhone+"'" );
                        OpenDialogOTP();
                    } else {
                        Toast.makeText(DoiEmail.this, "Đổi Email thất bại! Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DoiEmail.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean IsEmail(String email) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(!email.matches(regex)) {
            return false;
        }else{
            return true;
        }
    }
}