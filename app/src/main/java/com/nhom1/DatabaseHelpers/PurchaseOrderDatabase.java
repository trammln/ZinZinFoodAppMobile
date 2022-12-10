package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PurchaseOrderDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "purchaseorder.sqlite";
    //    Co the dat phan mo rong la .db hoac .sqlite
    public static final  int DB_VERSION =1;

    public static final String TBL_NAME = "Purchase_Order";
    public static final String COL_ID ="OrderId";
    public static final String COL_DATE = "OrderDate";
    public static final String COL_TOTAL = "Total";
    public static final String COL_USER = "UserId";
    public static final String COL_VOUCHER = "VoucherId";
    public static final String COL_PAYMED = "PaymentMed";
    public static final String COL_INVOICE = "InvoiceId";
    public static final String COL_NOTES = "OrderNotes";

    public PurchaseOrderDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_DATE + " DATE, " + COL_TOTAL + " REAL, " + COL_USER + " VARCHAR(50), " + COL_VOUCHER + " VARCHAR(50), "
                + COL_PAYMED + " VARCHAR(50), " + COL_INVOICE + " VARCHAR(50), " + COL_NOTES + " VARCHAR(255))";
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
            execSql("INSERT INTO "+ TBL_NAME + " VALUES(null,'20/11/2022', 140000,'1','1','1','1','')");
            execSql("INSERT INTO "+ TBL_NAME + " VALUES(null,'11/11/2022', 120000, '2','2','3','2','')");
            execSql("INSERT INTO "+ TBL_NAME + " VALUES(null,'15/11/2022', 65000, '3','2','2','3','')");
        }
    }
}

