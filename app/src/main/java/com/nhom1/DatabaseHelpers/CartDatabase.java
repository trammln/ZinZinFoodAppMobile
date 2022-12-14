package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CartDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "cart.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "ProductCart";
    public static final String COL_ID = "ProductId";
    public static final String COL_NAME = "ProductName";
    public static final String COL_AMOUNT = "ProductAmount";
    public static final String COL_PRICE = "ProductPrice";
    public static final String COL_THUMB = "ProductThumb";

    public CartDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_THUMB + " TEXT, " + COL_NAME + " VARCHAR(50), " + COL_PRICE + " REAL, " + COL_AMOUNT + " INTEGER)" ;
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(sqLiteDatabase);
    }

    //SELECT
    public Cursor getData(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    //INSERT, UPDATE, DELETE
    public void execSql(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    //
    public int numbOfRows() {
        Cursor cursor = getData("SELECT * FROM " + TBL_NAME);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public Boolean checkproductcart(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + COL_NAME + " = ? ", new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

//    public void createData() {
//        if(numbOfRows() == 0){
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'banhtrangbocuon', 'B??nh tr??ng b?? cu???n', 10000, 2)");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'mucbentocaycay', 'M???c Bento cay cay', 7000, 2)");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'quaybozinzin', 'Qu???y b?? ZinZin', 7000, 1)");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'tamquecaycay', 'T??m que gi??n v???', 7000, 1)");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'khogalachanh', 'Kh?? g?? l?? chanh', 12000, 1)");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'khoheogiabo', 'Kh?? heo gi??? b??', 10000, 1)");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'snackbothomcay', 'Snack b?? th??m cay', 4000, 3)");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'banhtrangphomaitomhanh', 'B??nh tr??ng t??m ph?? mai', 10000, 1)");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'banhtrangrongbien', 'B??nh tr??ng rong bi???n', 12000, 1)");
//        }
//        //Tram
//    }
}
