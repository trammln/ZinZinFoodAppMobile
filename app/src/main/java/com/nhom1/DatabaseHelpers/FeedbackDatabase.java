package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FeedbackDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "feedback.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME ="Feedback";
    public static final String COL_ID ="FeedbackId";
    public static final String COL_NAME ="CustomerName";
    public static final String COL_RATE="Rate";
    public static final String COL_TIME="Datetime";
    public static final String COL_CONTENT ="Content";
    public static final String COL_THUMB ="Picture";

    public FeedbackDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME +" (" + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_NAME+" VARCHAR(50), "+COL_RATE+" REAL, "+ COL_TIME+" VARCHAR(500), "+ COL_CONTENT+" VARCHAR(1000), "+ COL_THUMB+" TEXT)";
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
        if(numbOfRows()==0)
        {
//            String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME +" (" + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
//                    COL_NAME+" VARCHAR(50), "+COL_RATE+" REAL, "+ COL_TIME+" VARCHAR(500), "+ COL_CONTENT+" VARCHAR(200), "+ COL_THUMB+" TEXT)";
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Thùy Linh', 5.0, '10:05 16/11/2022', 'Thời gian giao hàng rất nhanh, đóng gói cẩn thận, giao đầy đủ và đúng theo yêu cầu, nếu ăn ngon sẽ ủng hộ shop nhiều hơn.', 'quaybo')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Mai Ngọc', 4.9, '08:05 14/11/2022', 'Đồ ăn của shop chất lượng cực kì, giao hàng có 2-3 ngày là đến rồi. Meeeeee', 'satetac')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Thành Long', 5.0, '20:05 10/11/2022', 'Đóng gói cẩn thận, logo shop cute quá nhen :v', 'rongbien')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Mỹ Ngọc', 4.9, '20:05 05/11/2022', 'Tuyệt dời ông mặt trời, nhân viên cực kỳ dễ thương luôn á.', 'mucbento')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Vương Long', 5.0, '20:05 02/11/2022', '10 điểm, không có chỗ nào chê nha shop.', 'quaybo')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Thanh Bình', 5.0, '20:05 30/10/2022', 'Bánh tráng đậm đà mà ngon xỉu, dẻo nữa. Omg chân ái của tui', 'rongbien')");
        }
    }
}
