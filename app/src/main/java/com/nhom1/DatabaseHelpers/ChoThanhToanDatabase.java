package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ChoThanhToanDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "chothanhtoan.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "ChoThanhToan";
    public static final String COL_ID = "DonId";
    public static final String COL_NAME = "ProcductName";
    public static final String COL_QUANTITY = "Quantity";
    public static final String COL_PRICE = "Price";
    public static final String COL_QUANTITYTOTAL = "QuantityTotal";
    public static final String COL_TOTAL = "Total";
    public static final String COL_DUE= "Due";
    public static final String COL_THUMB = "ProductThumb";

    public ChoThanhToanDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME +" (" + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_NAME+" VARCHAR(200), "+ COL_QUANTITY+" INTEGER, "+COL_PRICE+" INTEGER, "+COL_QUANTITYTOTAL+" INTEGER, "+ COL_TOTAL+" INTEGER,"+COL_DUE+" VARCHAR(200), "+COL_THUMB+" TEXT)";
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
;
    public void createSampleData(){
        if(numbOfRows()==0)
        {
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng bơ cuộn thơm ngon đặc biệt', 5, 10000, 8, 100000, '10/01/2022', 'banhtrangbocuon')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng rong biển cháy tỏi thơm ngon', 2,12000, 5, 80000, '04/01/2023','banhtrangrongbien')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng sa tế tắc thơm cay ngon ngon',3, 10000, 10, 120000, '04/01/2023', 'banhtrangsatetac')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng bơ cuộn thơm ngon đặc biệt', 1, 10000, 5, 70000, '02/01/2023', 'banhtrangbocuon')");
        }
    }
}
