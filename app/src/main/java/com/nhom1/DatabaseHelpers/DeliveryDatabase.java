package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DeliveryDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "delivery.sqlite";
    //    Co the dat phan mo rong la .db hoac .sqlite
    public static final  int DB_VERSION =1;

    public static final String TBL_NAME = "Delivery";
    public static final String COL_ID ="Delivery_ID";
    public static final String COL_NAME = "Delivery_Name";
    public static final String COL_PRICE = "Delivery_Price";

    public DeliveryDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " VARCHAR(255), " + COL_PRICE + " REAL )";
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
            execSql("INSERT INTO "+ TBL_NAME + " VALUES(null,'Lấy hàng trực tiếp',0)");
            execSql("INSERT INTO "+ TBL_NAME + " VALUES(null,'Giao trong nội thành',15000)");
            execSql("INSERT INTO "+ TBL_NAME + " VALUES(null,'Giao hàng tiết kiệm',20000)");
            execSql("INSERT INTO "+ TBL_NAME + " VALUES(null,'Giao hàng nhanh',20000)");
            execSql("INSERT INTO "+ TBL_NAME + " VALUES(null,'Viettel Post',20000)");
        }
    }
}

