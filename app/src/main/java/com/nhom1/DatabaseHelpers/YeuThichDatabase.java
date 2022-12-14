package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class YeuThichDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "loveproducts.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Product";
    public static final String COL_ID = "ProductId";
    public static final String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";
    public static final String COL_RATE = "ProductRate";
    public static final String COL_SOLD = "ProductSold";
    public static final String COL_DUE = "ProductDue";
    public static final String COL_QUANTITY = "ProductQuantity";
    public static final String COL_COMPONENT = "ProductComponent";
    public static final String COL_DESCRIPTION = "ProductDescription";
    public static final String COL_THUMB = "ProductThumb";

    public YeuThichDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME +" (" + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_NAME+" VARCHAR(50), "+ COL_PRICE+" INTEGER, "+COL_RATE+" REAL, "+ COL_SOLD+" INTEGER,"+COL_DUE+" VARCHAR(200), "+COL_QUANTITY+" INTEGER,"+COL_COMPONENT+" VARCHAR(500), "+COL_DESCRIPTION+" VARCHAR(2000), "+COL_THUMB+" TEXT)";
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
    //    String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME +" (" + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
//            COL_NAME+" VARCHAR(50), "+ COL_PRICE+" INTEGER, "+COL_RATE+" REAL, "+ COL_SOLD+" INTEGER,"+COL_DUE+" VARCHAR(200), "+COL_QUANTITY+" INTEGER,"+COL_COMPONENT+" VARCHAR(500), "+COL_DESCRIPTION+" VARCHAR(2000), "+COL_THUMB+" TEXT)";
//        sqLiteDatabase.execSQL(sql);
//    public void createSampleData()
//    {
//        if(numbOfRows()==0){
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng rong bi???n ch??y t???i th??m ngon', 12000, 5.0, 200, '03/11/2023', 1000, 'B??nh tr??ng, rong bi???n, mu???i ???t', '500gr B??nh Tr??ng Rong Bi???n. V??? cay cay ng???t ng???t m???n m???n v?? ????? gi??n gi??n c???a rong bi???n', 'banhtrangrongbien')");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng b?? cu???n th??m ngon ?????c bi???t', 10000, 5.0, 100, '05/11/2023', 1000, 'B??nh tr??ng d???o t??m, b??, ru???c', 'B??nh tr??ng d???o t??m th??m ngon ?????c bi???t. V??? h??a quy???n gi???a c??c lo???i gia v???, ?????c bi???t c?? b?? th??m ngon.', 'banhtrangbocuon')");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng t??m h??nh b??o ng???y ?????m ????', 10000, 4.9, 300, '08/11/2023', 1500, 'B??nh tr??ng, t??m, ph?? mai', 'B??nh tr??ng v??? cay nh??? ,ng???t nh??? ,b??o b??o v??? ph?? mai v?? r???t th??m m??i ph?? mai m??i m?? thoang tho???ng m??i tr???ng mu???i.', 'banhtrangphomaitomhanh')");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'C??m ch??y l???c kh?? g?? truy???n th???ng nh?? l??m', 12000, 5.0, 300, '09/11/2023', 800, 'G???o rang, g??, l?? chanh', 'C??m ch??y ??p m???ng chi??n gi??n r???m, ph??? ?????u m???m ???t, l???c chung v???i kh?? g?? x?? l?? chanh cay cay, gi??n gi??n.', 'comchaykhoga')");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'M???c bento cay cay x?? tan c??i l??ng', 7000, 4.8, 50, '10/11/2023', 1300, 'M???c, mu???i ???t, sa t???', 'M???c bento Th??i Lan, m???c ????? cay t??y ch???n. B???o quan n??i kh?? r??o.', 'mucbentocaycay')");
//            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng t???i phi cay ?????m ch???t nh?? l??m', 7000, 5.0, 150, '10/11/2023', 1200, 'B??nh tr??ng, mu???i ???t, t???i', 'B??nh tr??ng d??o dai v???i h????ng v??? t???i th??m cay n???ng ?????m v???.', 'banhtrangtoiphicay')");
//        }
//    }
}
