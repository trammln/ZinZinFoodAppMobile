package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PaymentMethodDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "paymentmethod.sqlite";
    //    Co the dat phan mo rong la .db hoac .sqlite
    public static final  int DB_VERSION =1;

    public static final String TBL_NAME = "Payment_Method";
    public static final String COL_ID ="PayMethod_ID";
    public static final String COL_NAME = "PayMethod_Name";

    public PaymentMethodDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " VARCHAR(255))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(sqLiteDatabase);
    }
    //    DINH NGHIA PHUONG THUC DE SU DUNG CAU LENH SELECT
    public Cursor getData(String sql){
        SQLiteDatabase  db= getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    //    INSERT, UPDATE, DELETE
    public void execSql(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public int numbOfRows(){
        Cursor cursor = getData("SELECT * FROM "+ TBL_NAME);
        int count = cursor.getCount();
        // Tra ve so dong
        cursor.close();
        return count;
    }

    public void createSampleData(){
        if (numbOfRows() == 0){
            execSql("INSERT INTO "+ TBL_NAME + " VALUES(null,'Thanh toán bằng tiền mặt')");
            execSql("INSERT INTO "+ TBL_NAME + " VALUES(null,'Tài khoản ngân hàng')");
            execSql("INSERT INTO "+ TBL_NAME + " VALUES(null,'Tài khoản Visa/Mastercard')");
            execSql("INSERT INTO "+ TBL_NAME + " VALUES(null,'Ví điện tử Momo')");
        }
    }
}

