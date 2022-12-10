package com.nhom1.zinzinfood;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nhom1.DatabaseHelpers.UserDatabase;
import com.nhom1.models.User;
import com.nhom1.zinzinfood.databinding.ActivityCaiDatBinding;

import java.util.ArrayList;

public class CaiDatActivity extends AppCompatActivity {
    ActivityCaiDatBinding binding;
    UserDatabase db;
    User user;
    ArrayList<User> users;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cai_dat);
        binding=ActivityCaiDatBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        loadData();

        addEvents();
    }

    private void loadData() {
        db = new UserDatabase(CaiDatActivity.this);
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
            public void onClick(View view) {
                finish();
            }
        });

        binding.linearDoiNgonNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(CaiDatActivity.this);
                dialog.setContentView(R.layout.dialog_ngon_ngu);
                dialog.show();

                Button btnTiengViet, btnTiengAnh;

                btnTiengAnh=dialog.findViewById(R.id.btn_TiengAnh);
                btnTiengViet=dialog.findViewById(R.id.btn_TiengViet);

                btnTiengViet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.txtTieng.setText("Tiếng Việt");
                        dialog.dismiss();
                    }
                });
                btnTiengAnh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.txtTieng.setText("Tiếng Anh");
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        binding.linearCheDoHienThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(CaiDatActivity.this);
                dialog.setContentView(R.layout.dialog_che_do_hien_thi);
                dialog.show();

                Button btnSang, btnToi;

                btnSang=dialog.findViewById(R.id.btn_Sang);
                btnToi=dialog.findViewById(R.id.btn_Toi);

                btnSang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.txtChedo.setText("Sáng");
                        dialog.dismiss();
                    }
                });
                btnToi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.txtChedo.setText("Tối");
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        binding.linearCaiDatThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(CaiDatActivity.this);
                dialog.setContentView(R.layout.dialog_caidatthongbao);
                dialog.show();

                Button btnChophep, btnKhongchophep;

                btnChophep=dialog.findViewById(R.id.btn_ChoPhep);
                btnKhongchophep=dialog.findViewById(R.id.btn_KhongChoPhep);

                btnChophep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.txtCaiDatThongBao.setText("Cho phép");
                        dialog.dismiss();
                    }
                });
                btnKhongchophep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.txtCaiDatThongBao.setText("Không cho phép");
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        binding.linearYeuCauXoaTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(CaiDatActivity.this);
                dialog.setContentView(R.layout.dialog_yeucauxoataikhoan);
                dialog.show();

                Button btnOk, btnCancel;

                btnOk=dialog.findViewById(R.id.btn_Ok);
                btnCancel=dialog.findViewById(R.id.btn_Cancel);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        db.execSql("DELETE FROM " + UserDatabase.TBL_USER_NAME
                                + " WHERE " + UserDatabase.COL_USER_EMAIL + " ='" + user.getEmail()+"'");

                        Toast.makeText(CaiDatActivity.this, "Đã xóa tài khoản thành công!", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        Intent intent = new Intent(CaiDatActivity.this, SplashActivity.class);
                        startActivity(intent);
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