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

            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Quẩy bò ZinZin quẩy cùng deadline mỗi ngày', 7000, 5.0, 850, '14/11/2023', 1000, 'Bột mì, ớt trái, sa tế', 'Quẩy trở về tuổi thơ, cay the lưỡi, ngon ngon, nhâm nhi cùng bạn bè.', 'quaybozinzin')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng tỏi phi cay đậm chất nhà làm', 7000, 5.0, 150, '10/11/2023', 1200, 'Bánh tráng, muối ớt, tỏi', 'Bánh tráng déo dai với hương vị tỏi thơm cay nồng đậm vị.', 'banhtrangtoiphicay')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng sa tế bò thơm ngon hết sảy', 7000, 4.7, 150, '05/11/2023', 1500, 'Bánh tráng, khô bò, sa tế', '1kg Bánh tráng trộn sợi mỏng Tây Ninh. Thơm thơm ngọt ngọt cùng khô bò.', 'banhtrangsatebo')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng tôm hành béo ngậy đậm đà', 10000, 4.9, 300, '08/11/2023', 1500, 'Bánh tráng, tôm, phô mai', 'Bánh tráng vị cay nhẹ ,ngọt nhẹ ,béo béo vị phô mai và rất thơm mùi phô mai mùi mè thoang thoảng mùi trứng muối.', 'banhtrangphomaitomhanh')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng sa tế tắc thơm cay ngon ngon', 10000, 4.9, 250, '04/11/2023', 1300, 'Bánh tráng, tắc, hành phi, sa tế',  'Bánh tráng vị chua cay, có thể trộn dùng chung hoặc tách ra ly chấm.', 'banhtrangsatetac')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Khô gà lá chanh nhà làm đảm bảo', 12000, 4.8, 350, '01/11/2023', 1000, 'Gà, lá chanh, ớt khô', 'Khô gà lá chanh nhà làm cay cay thơm vị chanh nồng nàn.', 'khogalachanh')");

        }
    }
}
