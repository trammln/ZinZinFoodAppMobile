package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NewDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "new.sqlite";
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

    public NewDatabase(@Nullable Context context) {
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

            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Kh?? heo gi??? b?? v??? ngon ????n t???t sum v???y', 10000, 5.0, 150, '02/11/2023', 2000, 'Th???t heo, s???, ???t kh??', 'Th???t th??m dai v?? ng???t, cay cay th??m l???ng c???a s???, h????ng v??? h???p d???n.', 'khoheogiabo')");

            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng sa t??? t???c th??m cay ngon ngon', 10000, 4.9, 250, '04/11/2023', 1300, 'B??nh tr??ng, t???c, h??nh phi, sa t???',  'B??nh tr??ng v??? chua cay, c?? th??? tr???n d??ng chung ho???c t??ch ra ly ch???m.', 'banhtrangsatetac')");

            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Kh?? g?? l?? chanh nh?? l??m ?????m b???o', 12000, 4.8, 350, '01/11/2023', 1000, 'G??, l?? chanh, ???t kh??', 'Kh?? g?? l?? chanh nh?? l??m cay cay th??m v??? chanh n???ng n??n.', 'khogalachanh')");

            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'B??nh tr??ng rong bi???n ch??y t???i th??m ngon', 12000, 5.0, 200, '03/11/2023', 1000, 'B??nh tr??ng, rong bi???n, mu???i ???t', '500gr B??nh Tr??ng Rong Bi???n. V??? cay cay ng???t ng???t m???n m???n v?? ????? gi??n gi??n c???a rong bi???n', 'banhtrangrongbien')");

        }
    }
}
