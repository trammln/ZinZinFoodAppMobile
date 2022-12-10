package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VoucherDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "voucher.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME ="Voucher";
    public static final String COL_ID ="VoucherId";
    public static final String COL_NAME ="VoucherName";
    public static final String COL_CONDITION="VoucherCondition";
    public static final String COL_TIME="VoucherTime";
    public static final String COL_NOTE ="VoucherNote";

    public VoucherDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME +" (" + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_NAME+" VARCHAR(50), "+ COL_CONDITION+" VARCHAR(500), "+ COL_TIME+" VARCHAR(200), "+ COL_NOTE+" VARCHAR(500)) ";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(sqLiteDatabase);
    }
    //SELECT //con trỏ
    public Cursor getData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    //INSERT, UPDATE, DELETE
    public void execSql(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public int numbOfRows(){
        Cursor cursor = getData("SELECT * FROM " + TBL_NAME);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public void createSampleData(){
        if(numbOfRows()==0){
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Voucher giảm giá 10%', 'Áp dụng cho đơn hàng trên 100.000đ', 'Thời gian: 14/11 - 16/11/2022', 'P/s: Áp dụng cho đơn hàng có quẩy bò ZinZin')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Voucher giảm giá 20%', 'Áp dụng cho đơn hàng trên 50.000đ', 'Thời gian: 28/11 - 05/12/2022', 'P/s: Áp dụng cho toàn bộ sản phẩm')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Voucher giảm giá 30%', 'Áp dụng cho đơn hàng trên 80.000đ', 'Thời gian: 24/11 - 06/12/2022', 'P/s: Áp dụng cho toàn bộ sản phẩm')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Voucher giảm giá 40%', 'Áp dụng cho đơn hàng trên 60.000đ', 'Thời gian: 23/11 - 06/12/2022', 'P/s: Áp dụng cho toàn bộ sản phẩm')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Voucher giảm giá 50%', 'Áp dụng cho đơn hàng trên 70.000đ', 'Thời gian: 25/11 - 06/12/2022', 'P/s: Áp dụng cho đơn hàng có mực bento')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Voucher giảm giá 10%', 'Áp dụng cho đơn hàng trên 10.000đ', 'Thời gian: 22/11 - 06/12/2022', 'P/s: Áp dụng cho đơn hàng có snack bò')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Voucher giảm giá 20.000đ', 'Áp dụng cho đơn hàng trên 100.000đ', 'Thời gian: 22/11 - 06/12/2022', 'P/s: Áp dụng cho toàn sản phẩm')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Voucher giảm giá 30.000đ', 'Áp dụng cho đơn hàng trên 150.000đ', 'Thời gian: 20/11 - 01/12/2022', 'P/s: Áp dụng cho đơn hàng có snack mèo')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Voucher giảm giá 10.000đ', 'Áp dụng cho đơn hàng trên 80.000đ', 'Thời gian: 22/11 - 01/12/2022', 'P/s: Áp dụng cho toàn sản phẩm')");
        }
    }
}
