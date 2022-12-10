package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ThongBaoDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "thongbao.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "ThongBao";
    public static final String COL_ID = "ThongBaoId";
    public static final String COL_NAME = "ThongBaoName";
    public static final String COL_CONTENT = "ThongBaoContent";
    public static final String COL_THUMB = "ThongBaoThumb";

    public ThongBaoDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_THUMB + " TEXT, " + COL_NAME + " VARCHAR(50), " + COL_CONTENT + " TEXT) " ;
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

    public void createData() {
        if(numbOfRows() == 0){
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'tbsale', 'FLASH SALE 11/11 GIẢM 10% KHÔNG GIỚI HẠN!', 'Một tuần trôi qua với bạn cảm thấy như thế nào? Bạn có đang cô đơn vì deadline chất chồng...')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'tbminigame', 'MINIGAME “AI TINH MẮT HƠN TUI?”', 'Nghe vẻ nghe ve, nghe vè nhận quà. Nghe quà là mê rồi đúng hong Cả nhà cùng chơi minigame nhận combo bánh tráng và voucher nè....')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'tbgiaithuongminigame', 'THAM GIA MINIGAME KHÁCH NHẬN ĐƯỢC GÌ?', 'Tui thắng thì tui nhận được gì ạ? => 2 bạn cmt nhanh nhất + trùng số ZinZin quay trúng sẽ nhận được: ...')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'tbtriankhachhang', 'LỜI TRI ÂN ĐẾN TỪ ZINZIN FOOD', 'Vậy là sau gần 4 tuần phát động, chặng đường chiến dịch ...')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'tbketquaminigame', 'KẾT QUẢ MINIGAME “AI TINH MẮT HƠN TUI?” ĐÃ CÓ RỒI ĐÂY', 'Ting ting! Sau khoảng thời gian 8 ngày ngắn ngủi MINIGAME “AI TINH MẮT HƠN TUI?” đã chính thức khép lại !!...')");
        }
    }
}
