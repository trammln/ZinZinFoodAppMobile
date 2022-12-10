package com.nhom1.zinzinfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.nhom1.DatabaseHelpers.CartDatabase;
import com.nhom1.DatabaseHelpers.PaymentMethodDatabase;
import com.nhom1.DatabaseHelpers.PurchaseOrderDatabase;
import com.nhom1.DatabaseHelpers.VoucherDatabase;
import com.nhom1.adapters.ItemToPayAdapter;
import com.nhom1.adapters.ProductCartAdapter;
import com.nhom1.models.PayProduct;
import com.nhom1.models.Product;
import com.nhom1.models.ProductCart;
import com.nhom1.zinzinfood.databinding.ActivityPaymentActitvityBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PaymentActitvity extends AppCompatActivity {

    ActivityPaymentActitvityBinding binding;
    ItemToPayAdapter adapter;
    ArrayList<PayProduct> itemList;
    ListView lvPayItems;
    CartDatabase db;
    PaymentMethodDatabase paymentMethoddb;
    PurchaseOrderDatabase purchaseOrderdb;
    VoucherDatabase dbvoucher;

    double draftsum = 0;
    double giamgia = 0;
    double vanchuyen = 0;
    double thanhtien =0;
    String paymentmethod = "Thanh toán bằng tiền mặt";
    String voucher = "Không";
    String ordernote ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_payment_actitvity);

        binding = ActivityPaymentActitvityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        lvPayItems = binding.lvItemsToPay;

        getData();
        loadData();
        setVoucher();
        setListViewHeightBasedOnItems(lvPayItems);
        addEvents();
        tinhgiavanchuyen();
        getTotal();
        footerLinkpage();
    }

    private void footerLinkpage() {

        binding.imvToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToi = new Intent(PaymentActitvity.this, Toi.class);
                startActivity(intentToi);
            }
        });
        binding.imvDonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToi = new Intent(PaymentActitvity.this, DonMuaPage.class);
                startActivity(intentToi);
            }
        });

        binding.imvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActitvity.this, ProductPage.class);
                startActivity(intent);
            }
        });

        binding.imvUuDai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActitvity.this, VoucherPage.class);
                startActivity(intent);
            }
        });
        binding.imvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActitvity.this, Homepage.class);
                startActivity(intent);
            }
        });
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActitvity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        binding.txtChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActitvity.this, DeliveryAddress.class);
                startActivity(intent);
            }
        });

        //Thay doi voucher
        binding.edtInputVoucher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                double discount = 0;
                double draftsum = Double.parseDouble(binding.txtDraftSum.getText().toString());
                voucher = binding.edtInputVoucher.getSelectedItem().toString();

                switch ( voucher ) {
                    case  "Voucher giảm giá 10%":
                        discount = 0.1;
                        break;
                    case  "Voucher giảm giá 20%":
                        discount = 0.2;
                        break;
                    case  "Voucher giảm giá 30%":
                        discount = 0.3;
                        break;
                    case  "Voucher giảm giá 40%":
                        discount = 0.4;
                        break;
                    case  "Voucher giảm giá 50%":
                        discount = 0.5;
                        break;
                    case  "Voucher giảm giá 20.000đ":
                        discount = 20000;
                        break;
                    case  "Voucher giảm giá 30.000đ":
                        discount = 30000;
                        break;
                    case  "Voucher giảm giá 10.000đ":
                        discount = 10000;
                        break;
                    default:
                        discount = 0;
                        break;
                }
                if (discount < 1){
                    giamgia = discount*draftsum;
                } else {
                    giamgia = discount;
                }
                binding.txtDiscountQuant.setText(String.format("%.0f", giamgia));
                getfinalTotal();
            }

            //Chưa có case
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentmethod = "Thanh toán bằng tiền mặt";
                binding.edtInputPaymentMethod.setText(paymentmethod);
            }
        });
        binding.btnBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentmethod = "Tài khoản ngân hàng";
                binding.edtInputPaymentMethod.setText(paymentmethod);
            }
        });
        binding.btnMomo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentmethod = "Ví điện tử Momo";
                binding.edtInputPaymentMethod.setText(paymentmethod);
            }
        });
        binding.btnVisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentmethod = "Tài khoản Visa/Mastercard";
                binding.edtInputPaymentMethod.setText(paymentmethod);
            }
        });

        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Prepare data
            String voucherid = (dbvoucher.getData("SELECT " +dbvoucher.COL_ID + " FROM " +dbvoucher.TBL_NAME
                             + " WHERE " + dbvoucher.COL_NAME + " = "+ "'" + voucher + "'")).toString();
            String paymentid = (paymentMethoddb.getData("SELECT " + paymentMethoddb.COL_ID + " FROM " + paymentMethoddb.TBL_NAME
                    + " WHERE " + paymentMethoddb.COL_NAME + " = "+ "'" + paymentmethod + "'")).toString();
            ordernote = binding.edtInputNotes.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date());

            //Lấy số dòng hiện có trong database PO
                int count = 0;
                Cursor c = purchaseOrderdb.getData("SELECT * FROM " + PurchaseOrderDatabase.TBL_NAME);
                try {
                    while (c.moveToNext()){
                        count++;
                    }
                } finally {
                    c.close();
                }

            //Insert data vào bảng PO
            purchaseOrderdb.execSql("INSERT INTO " + PurchaseOrderDatabase.TBL_NAME + " VALUES(null, '" + date + "', "+
                    thanhtien + ", 'U01', '" + voucherid + "', '"+ paymentid + "', '" + (count+1) + "', '" + ordernote + "')" );

            Intent intent = new Intent(PaymentActitvity.this, AfterPaymentActivity.class);
            startActivity(intent);
            }
        });
    }

    public void tinhgiavanchuyen(){
        vanchuyen = 15000;
        binding.txtDelivFee.setText(String.format("%.0f", vanchuyen));
        getfinalTotal();
    }

    private void getData() {
        db = new CartDatabase(PaymentActitvity.this);
        //        db.createData();
        //Tram
        db.getData("SELECT * FROM "+ CartDatabase.TBL_NAME);
        //        Phuong Anh

        paymentMethoddb = new PaymentMethodDatabase(PaymentActitvity.this);
        paymentMethoddb.createSampleData();

        purchaseOrderdb = new PurchaseOrderDatabase(PaymentActitvity.this);
        purchaseOrderdb.createSampleData();

        dbvoucher = new VoucherDatabase(PaymentActitvity.this);
        dbvoucher.createSampleData();

//        //Linh
//
//        Intent intent = getIntent();
//        Product p = (Product) intent.getSerializableExtra("productInf");
//        String name = p.getProductName();
//        int price = p.getProductPrice();
//        String image = p.getProductThumb();
//
//        db = new CartDatabase(PaymentActitvity.this);
//
//        if (db.checkproductcart(name)) {
//            db.execSql("UPDATE " + CartDatabase.TBL_NAME + " SET " + CartDatabase.COL_AMOUNT + " = " + CartDatabase.COL_AMOUNT + " + 1" + " WHERE " + CartDatabase.COL_NAME + " = '" + name + "'");
//        } else {
//            db.execSql("INSERT INTO " + CartDatabase.TBL_NAME + " VALUES(null, '" + image + "', '" + name + "', " + Double.parseDouble(String.valueOf(price)) + ", 1)");
//        }
//        loadData();
    }

    private void setVoucher() {
        Spinner dropdown = binding.edtInputVoucher;
        // String[] items = new String[]{"","VC1", "VC2", "VC3","VC4","VC5"};

    //        Phuong Anh
        List<String> list;
        list = new ArrayList<>();
        list.add("Không");

        Cursor c = dbvoucher.getData("SELECT * FROM " + VoucherDatabase.TBL_NAME);
        int i = 0;
        try {
            while (c.moveToNext()){
                String str = c.getString(1);
                list.add(str);
                i++;
            }
        } finally {
            c.close();
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        dropdown.setAdapter(adapter1);

        //Phuong Anh
    }

    private void loadData() {
    //        itemList = new ArrayList<>();
    //        itemList.add(new PayProduct(R.drawable.khoheogiabo,"Khô heo giả bò",10000,3));
    //        itemList.add(new PayProduct(R.drawable.banhtrangrongbien1x1,"Bánh tráng rong biển",12000,2));
    //        itemList.add(new PayProduct(R.drawable.tamque1x1,"Tăm que",10000,1));
    //
    //        //Init adapter
    //        adapter = new ItemToPayAdapter(PaymentActitvity.this, R.layout.items_to_pay_list, itemList);
    //
    //        //Set adapter
    //        binding.lvItemsToPay.setAdapter(adapter);
    //        //Phuong Anh

        //init adapter
        itemList = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + CartDatabase.TBL_NAME);
        while (c.moveToNext()){
            itemList.add(new PayProduct(c.getInt(0),c.getString(1), c.getString(2), c.getDouble(3), c.getInt(4)));
        }
        c.close();
        adapter = new ItemToPayAdapter(PaymentActitvity.this, R.layout.items_to_pay_list, itemList);
        binding.lvItemsToPay.setAdapter(adapter);
        setListViewHeightBasedOnItems(lvPayItems);
        //        Phuong Anh
    }


//    Delete product
    public void openDialogDelete(PayProduct b) {
        //Tram
        AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActitvity.this);
        builder.setTitle("Xác nhận xóa sản phẩm!");
        builder.setMessage("Bạn có chắc muốn xóa sản phẩm " + b.getProductName() + "?");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Delete product
                db.execSql("DELETE FROM " + CartDatabase.TBL_NAME + " WHERE " + CartDatabase.COL_ID + " = " + b.getProductId());
                loadData();
                getTotal();
                if (db.numbOfRows() <= 0) {
                    binding.lvItemsToPay.setVisibility(View.GONE);
                //                    binding.linearEmpty.setVisibility(View.VISIBLE);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
        for (int i = 0; i < itemList.size(); i++) {
                draftsum += itemList.get(i).getProductQuantity() * itemList.get(i).getProductPrice();
                db.execSql("UPDATE " + CartDatabase.TBL_NAME + " SET "+ CartDatabase.COL_AMOUNT + " = " + itemList.get(i).getProductQuantity() + " WHERE " + CartDatabase.COL_ID + " = '" + itemList.get(i).getProductId() + "'");
                loadData();
        }
        binding.txtDraftSum.setText(String.format("%.0f", draftsum));
        //Tram
        getfinalTotal();
        //PhuongAnh
    }

    public void getfinalTotal(){
        thanhtien = draftsum - giamgia + vanchuyen;
        binding.edtTotalsum.setText(String.format("%.0f", thanhtien, "đ"));

    }




    //    SET LISTVIEW HEIGHT AUTOMICALLY - Phuong Anh
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            //setDynamicHeight(listView);
            return true;

        } else {
            return false;
        }
    }
}