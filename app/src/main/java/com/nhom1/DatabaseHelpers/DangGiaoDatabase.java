package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DangGiaoDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "danggiao.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "DonMuaDangGiao";
    public static final String COL_ID = "DonId";
    public static final String COL_NAME = "ProcductName";
    public static final String COL_QUANTITY = "Quantity";
    public static final String COL_PRICE = "Price";
    public static final String COL_QUANTITYTOTAL = "QuantityTotal";
    public static final String COL_TOTAL = "Total";
    public static final String COL_STATUS= "StatusVanChuyen";
    public static final String COL_THUMB = "ProductThumb";
    public DangGiaoDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME +" (" + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_NAME+" VARCHAR(200), "+ COL_QUANTITY+" INTEGER, "+COL_PRICE+" INTEGER, "+COL_QUANTITYTOTAL+" INTEGER, "+ COL_TOTAL+" INTEGER,"+COL_STATUS+" VARCHAR(200), "+COL_THUMB+" TEXT)";
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

    public int numbOfRows() {
        Cursor cursor = getData("SELECT * FROM " + TBL_NAME);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void createSampleData(){
        if(numbOfRows()==0)
        {
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng b?? cu???n th??m ngon ?????c bi???t', 5, 10000, 8, 100000, '??ang giao h??ng', 'banhtrangbocuon')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng rong bi???n ch??y t???i th??m ngon', 2,12000, 5, 80000, '??ang v???n chuy???n','banhtrangrongbien')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng sa t??? t???c th??m cay ngon ngon',3, 10000, 10, 120000, '??ang v???n chuy???n', 'banhtrangsatetac')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng b?? cu???n th??m ngon ?????c bi???t', 1, 10000, 5, 70000, '??ang l???y h??ng', 'banhtrangbocuon')");
        }
    }
}
