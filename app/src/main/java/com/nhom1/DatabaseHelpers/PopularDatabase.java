package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PopularDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "popular.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "PopularProduct";
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

    public PopularDatabase(@Nullable Context context) {
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

    public void createSampleData()
    {
        if(numbOfRows()==0){

            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Qu???y b?? ZinZin qu???y c??ng deadline m???i ng??y', 7000, 5.0, 850, '14/11/2023', 1000, 'B???t m??, ???t tr??i, sa t???', 'Qu???y tr??? v??? tu???i th??, cay the l?????i, ngon ngon, nh??m nhi c??ng b???n b??.', 'quaybozinzin')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng t???i phi cay ?????m ch???t nh?? l??m', 7000, 5.0, 150, '10/11/2023', 1200, 'B??nh tr??ng, mu???i ???t, t???i', 'B??nh tr??ng d??o dai v???i h????ng v??? t???i th??m cay n???ng ?????m v???.', 'banhtrangtoiphicay')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng sa t??? b?? th??m ngon h???t s???y', 7000, 4.7, 150, '05/11/2023', 1500, 'B??nh tr??ng, kh?? b??, sa t???', '1kg Ba??nh tra??ng tr????n s????i mo??ng T??y Ninh. Th??m th??m ng???t ng???t c??ng kh?? b??.', 'banhtrangsatebo')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng t??m h??nh b??o ng???y ?????m ????', 10000, 4.9, 300, '08/11/2023', 1500, 'B??nh tr??ng, t??m, ph?? mai', 'B??nh tr??ng v??? cay nh??? ,ng???t nh??? ,b??o b??o v??? ph?? mai v?? r???t th??m m??i ph?? mai m??i m?? thoang tho???ng m??i tr???ng mu???i.', 'banhtrangphomaitomhanh')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng sa t??? t???c th??m cay ngon ngon', 10000, 4.9, 250, '04/11/2023', 1300, 'B??nh tr??ng, t???c, h??nh phi, sa t???',  'B??nh tr??ng v??? chua cay, c?? th??? tr???n d??ng chung ho???c t??ch ra ly ch???m.', 'banhtrangsatetac')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Kh?? g?? l?? chanh nh?? l??m ?????m b???o', 12000, 4.8, 350, '01/11/2023', 1000, 'G??, l?? chanh, ???t kh??', 'Kh?? g?? l?? chanh nh?? l??m cay cay th??m v??? chanh n???ng n??n.', 'khogalachanh')");

        }
    }
}
