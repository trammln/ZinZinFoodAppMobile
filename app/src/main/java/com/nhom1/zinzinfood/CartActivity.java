package com.nhom1.zinzinfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nhom1.DatabaseHelpers.CartDatabase;
import com.nhom1.DatabaseHelpers.ProductDatabase;
import com.nhom1.DatabaseHelpers.UserDatabase;
import com.nhom1.adapters.ProductCartAdapter;
import com.nhom1.models.Product;
import com.nhom1.models.ProductCart;
import com.nhom1.models.User;
import com.nhom1.zinzinfood.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    ProductCartAdapter adapter;
    List<ProductCart> ProductCartsList;
    CartDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cart);
        //Tram
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("productInf");
        db = new CartDatabase(CartActivity.this);
        if(p != null) {
            getData();
        }
            loadData();
            getTotal();
            getAmount();
            addEvents();

        if (db.numbOfRows() <= 0) {
            binding.btnClearAll.setVisibility(View.GONE);
            binding.lvProductCart.setVisibility(View.GONE);
            binding.linearEmpty.setVisibility(View.VISIBLE);
        }
        //Tram
        gotoPayment();
        //Phuong Anh
    }

    private void gotoPayment() {
        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Trâm
                if (db.numbOfRows() > 0) {
                    //Trâm
                    Intent intent = new Intent(CartActivity.this, PaymentActitvity.class);
                    //Get data from Adapter

                    // Deliver data
                    startActivity(intent);
                } //Trâm
                else {
                    Toast.makeText(CartActivity.this, "Giỏ hàng trống! Vui lòng thêm sản phẩm cần mua trước", Toast.LENGTH_SHORT).show();
                }
                //Trâm
            }
        });
        //Phuong Anh
    }

    private void getData() {
        //Tram
        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("productInf");
        String name = p.getProductName();
        int price = p.getProductPrice();
        String image = p.getProductThumb();
        if (db.checkproductcart(name)) {
            db.execSql("UPDATE " + CartDatabase.TBL_NAME + " SET " + CartDatabase.COL_AMOUNT + " = " + CartDatabase.COL_AMOUNT + " + 1" + " WHERE " + CartDatabase.COL_NAME + " = '" + name + "'");
        } else {
            db.execSql("INSERT INTO " + CartDatabase.TBL_NAME + " VALUES(null, '" + image + "', '" + name + "', " + Double.parseDouble(String.valueOf(price)) + ", 1)");
        }
        loadData();
        getTotal();
        getAmount();
        //Tram
    }

    private void addEvents() {
        //Tram
        binding.lvProductCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(CartActivity.this, DetailProductPage.class);

                //Get data from Adapter
                ProductCart item = ProductCartsList.get(position);
                intent.putExtra("name", item.getProductName());
                intent.putExtra("price", item.getProductPrice());
                intent.putExtra("image", item.getProductThumb());
                startActivity(intent);

                }
        });

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, ProductPage.class);
                startActivity(intent);
            }
        });

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, ProductPage.class);
                startActivity(intent);
            }
        });
        binding.btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ClearAll();
            }
        });
        //Tram
    }

    private void loadData() {
        //Tram
        //init adapter
        ProductCartsList = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + CartDatabase.TBL_NAME);
        if (c == null) c.moveToFirst();
        while (c.moveToNext()){
            ProductCartsList.add(new ProductCart(c.getInt(0), c.getString(1), c.getString(2), c.getDouble(3), c.getInt(4)));
        }
        c.close();
        adapter = new ProductCartAdapter(CartActivity.this, R.layout.activity_product_cart, ProductCartsList);
        binding.lvProductCart.setAdapter(adapter);
        //Tram
    }

    public void openDialogDelete(ProductCart b) {
        //Tram
        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
        builder.setTitle("Xác nhận xóa sản phẩm!");
        builder.setMessage("Bạn chắc chắn muốn xóa sản phẩm " + b.getProductName() + "?");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Delete product
                db.execSql("DELETE FROM " + CartDatabase.TBL_NAME + " WHERE " + CartDatabase.COL_ID + " = " + b.getProductId());
                loadData();
                getTotal();
                getAmount();
                if (db.numbOfRows() <= 0) {
                    binding.btnClearAll.setVisibility(View.GONE);
                    binding.lvProductCart.setVisibility(View.GONE);
                    binding.linearEmpty.setVisibility(View.VISIBLE);
                }
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
        //Tram
    }


    public void getTotal(){
        //Tram
        double Total = 0;
        for (int i = 0; i < ProductCartsList.size(); i++) {
            Total += ProductCartsList.get(i).getProductAmount() * ProductCartsList.get(i).getProductPrice();
            db.execSql("UPDATE " + CartDatabase.TBL_NAME + " SET "+ CartDatabase.COL_AMOUNT + " = " + ProductCartsList.get(i).getProductAmount() + " WHERE " + CartDatabase.COL_ID + " = '" + ProductCartsList.get(i).getProductId() + "'");
            loadData();
            //Phuong Anh
        }
        binding.txtTotal.setText(String.format("%.0f", Total));
        //Tram
    }

    public void getAmount(){
        //Tram
        int Amount = 0;
        for (int i = 0; i < ProductCartsList.size(); i++) {
            Amount += ProductCartsList.get(i).getProductAmount();
        }
        binding.btnOrder.setText("Mua ngay (" + Amount + ")");
        //Tram
    }
    public void ClearAll() {
        //Delete All products
        //Tram
        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
        builder.setTitle("Xác nhận xóa hết sản phẩm!");
        builder.setMessage("Bạn chắc chắn muốn xóa hết sản phẩm trong giỏ hàng?");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Delete product
                db.execSql("DELETE FROM " + CartDatabase.TBL_NAME);
                loadData();
                getTotal();
                getAmount();
                if (db.numbOfRows() <= 0) {
                    binding.btnClearAll.setVisibility(View.GONE);
                    binding.lvProductCart.setVisibility(View.GONE);
                    binding.linearEmpty.setVisibility(View.VISIBLE);
                }
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
        //Tram
    }

    public void MinusAmount(ProductCart b) {
        //Tram
        int numberOrder = b.getProductAmount();
        numberOrder--;
        if(numberOrder > 0 ) {
            b.setProductAmount(numberOrder);
            db.execSql("UPDATE " + CartDatabase.TBL_NAME + " SET "+ CartDatabase.COL_AMOUNT + " = " + b.getProductAmount() + " WHERE " + CartDatabase.COL_ID + " = '" + b.getProductId() + "'");
            loadData();
        }
        //Tram
    }

    public void AddAmount(ProductCart b) {
        //Tram
        int numberOrder = b.getProductAmount();
        numberOrder++;
        b.setProductAmount(numberOrder);
        db.execSql("UPDATE " + CartDatabase.TBL_NAME + " SET "+ CartDatabase.COL_AMOUNT + " = " + b.getProductAmount() + " WHERE " + CartDatabase.COL_ID + " = '" + b.getProductId() + "'");
        loadData();
        //Tram
    }
}