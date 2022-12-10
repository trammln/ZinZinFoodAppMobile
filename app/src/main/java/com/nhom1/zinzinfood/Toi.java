package com.nhom1.zinzinfood;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.nhom1.DatabaseHelpers.UserDatabase;
import com.nhom1.models.User;
import com.nhom1.zinzinfood.databinding.ActivityToiBinding;

import java.util.ArrayList;

public class Toi extends AppCompatActivity {
    ActivityToiBinding binding;
    ActivityResultLauncher<Intent> launcher;
    ArrayList<User> users;
    UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_toi);
        binding = ActivityToiBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();

        if(SplashActivity.EmailHoacPhone.length()==0){
            setContentView(view);
        }else {
            setContentView(view);
            binding.linearSuaThongtin.setVisibility(View.VISIBLE);
            binding.linearDangXuat.setVisibility(View.VISIBLE);

            loadData();
            binding.imvSuaAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    launcher.launch(intent);
                }
            });
        }

        addEvents();

        launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode()==RESULT_OK && result.getData() !=null){

                Bitmap bitmap=(Bitmap) result.getData().getExtras().get("data");
                binding.imvSuaAvatar.setImageBitmap(bitmap);
            }
        });

    }

    private void loadData() {
        db = new UserDatabase(Toi.this);
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

        binding.txtName.setText(user.getUserName());
        binding.txtLoaiThanhVien.setText(user.getUserType());
    }

    private void batDangNhap() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Toi.this);
        builder.setTitle("Vui lòng đăng nhập");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage("Để tiếp tục xem thông tin bạn cần phải đăng nhập!");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intentToi = new Intent(Toi.this, LogInActivity.class);
                startActivity(intentToi);
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


    private void addEvents() {
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Toi.this, LogInActivity.class);
                startActivity(intent);
            }
        });

        binding.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Toi.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        binding.linearHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToi = new Intent(Toi.this, Homepage.class);
                startActivity(intentToi);
            }
        });
        binding.linearDieuKhoanChinhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToi = new Intent(Toi.this, PolicyActivity.class);
                startActivity(intentToi);
            }
        });
//        binding.linearVeZinZinFood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentToi = new Intent(Toi.this, PolicyActivity.class);
//                startActivity(intentToi);
//            }
//        });
//        binding.linearTrungTamTroGiup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentToi = new Intent(Toi.this, PolicyActivity.class);
//                startActivity(intentToi);
//            }
//        });

        binding.linearProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToi = new Intent(Toi.this, ProductPage.class);
                startActivity(intentToi);
            }
        });
        binding.linearHoSoCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }else {
                    Intent intentToi = new Intent(Toi.this, HoSoCuaToi.class);
                    startActivity(intentToi);
                }
            }
        });
        binding.linearSanPhamYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }else {
                    Intent intentToi = new Intent(Toi.this, SanphamyeuthichPage.class);
                    startActivity(intentToi);
                }
            }
        });
        binding.linearUuDai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(SplashActivity.EmailHoacPhone.length()==0){
//                    batDangNhap();
//                }else {
                    Intent intentToi = new Intent(Toi.this, VoucherPage.class);
                    startActivity(intentToi);
            }
        });
        binding.linearDonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }
            }
        });
        binding.imvTinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }else {
                    Intent intentToi = new Intent(Toi.this, Messenger.class);
                    startActivity(intentToi);
                }
            }
        });
        binding.imvThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }else {
                    Intent intentToi = new Intent(Toi.this, ThongBaoActivity.class);
                    startActivity(intentToi);
                }
            }
        });
        binding.linearCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }else {
                    Intent intentToi = new Intent(Toi.this, CaiDatActivity.class);
                    startActivity(intentToi);
                }
            }
        });
        binding.linearDonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }else {
                    Intent intentToi = new Intent(Toi.this, DonMuaPage.class);
                    startActivity(intentToi);
                }
            }
        });
        binding.linearDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }else {
                    Intent intentToi = new Intent(Toi.this, DeliveryAddress.class);
                    startActivity(intentToi);
                }
            }
        });
        binding.linearPhuongThucThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){batDangNhap();}
            }
        });
        binding.linearDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SplashActivity.EmailHoacPhone.length()==0){
                    batDangNhap();
                }
            }
        });
        binding.linearDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(Toi.this);
                dialog.setContentView(R.layout.dialog_logout);
                dialog.show();

                Button btnOk, btnCancel;

                btnOk=dialog.findViewById(R.id.btn_Ok);
                btnCancel=dialog.findViewById(R.id.btn_Cancel);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SplashActivity.EmailHoacPhone="";
                        dialog.dismiss();
                        Intent intent = new Intent(Toi.this, SplashActivity.class);
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
        binding.linearSuaThongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToi = new Intent(Toi.this, HoSoCuaToi.class);
                startActivity(intentToi);
            }
        });
    }
}