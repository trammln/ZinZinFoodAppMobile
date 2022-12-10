package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ArrangeDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "arrange.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "ArrangeProduct";
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

    public ArrangeDatabase(@Nullable Context context) {
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
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Snack bò thơm cay trở về tuổi thơ', 4000, 4.8, 150, '14/11/2023', 1000, 'Bột, ớt trái, sa tế', 'Snack bò trở về tuổi thơ, cay the lưỡi, nhâm nhi cùng bạn bè.', 'snackbothomcay')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Tăm que giòn vị cay cay thư giãn ngày hè', 7000, 4.9, 50, '25/11/2023', 100, 'Bột, gia vị, ớt bột', 'Đồ ăn vặt nội địa tăm que, giòn giòn nhâm nhi cùng người yêu, bạn bè và gia đình.', 'tamquecaycay')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Quẩy bò ZinZin quẩy cùng deadline mỗi ngày', 7000, 5.0, 850, '14/11/2023', 1000, 'Bột mì, ớt trái, sa tế', 'Quẩy trở về tuổi thơ, cay the lưỡi, ngon ngon, nhâm nhi cùng bạn bè.', 'quaybozinzin')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Mực bento cay cay xé tan cõi lòng', 7000, 4.8, 50, '10/11/2023', 1300, 'Mực, muối ớt, sa tế', 'Mực bento Thái Lan, mức độ cay tùy chọn. Bảo quan nơi khô ráo.', 'mucbentocaycay')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng tỏi phi cay đậm chất nhà làm', 7000, 5.0, 150, '10/11/2023', 1200, 'Bánh tráng, muối ớt, tỏi', 'Bánh tráng déo dai với hương vị tỏi thơm cay nồng đậm vị.', 'banhtrangtoiphicay')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng sa tế bò thơm ngon hết sảy', 7000, 4.7, 150, '05/11/2023', 1500, 'Bánh tráng, khô bò, sa tế', '1kg Bánh tráng trộn sợi mỏng Tây Ninh. Thơm thơm ngọt ngọt cùng khô bò.', 'banhtrangsatebo')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng bơ cuộn thơm ngon đặc biệt', 10000, 5.0, 100, '05/11/2023', 1000, 'Bánh tráng dẻo tôm, bơ, ruốc', 'Bánh tráng dẻo tôm thơm ngon đặc biệt. Vị hòa quyện giữa các loại gia vị, đặc biệt có bơ thơm ngon.', 'banhtrangbocuon')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng tôm hành béo ngậy đậm đà', 10000, 4.9, 300, '08/11/2023', 1500, 'Bánh tráng, tôm, phô mai', 'Bánh tráng vị cay nhẹ ,ngọt nhẹ ,béo béo vị phô mai và rất thơm mùi phô mai mùi mè thoang thoảng mùi trứng muối.', 'banhtrangphomaitomhanh')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng sa tế tắc thơm cay ngon ngon', 10000, 4.9, 250, '04/11/2023', 1300, 'Bánh tráng, tắc, hành phi, sa tế',  'Bánh tráng vị chua cay, có thể trộn dùng chung hoặc tách ra ly chấm.', 'banhtrangsatetac')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Khô heo giả bò vị ngon đón tết sum vầy', 10000, 5.0, 150, '02/11/2023', 2000, 'Thịt heo, sả, ớt khô', 'Thịt thơm dai và ngọt, cay cay thơm lừng của sả, hương vị hấp dẫn.', 'khoheogiabo')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Bánh tráng rong biển cháy tỏi thơm ngon', 12000, 5.0, 200, '03/11/2023', 1000, 'Bánh tráng, rong biển, muối ớt', '500gr Bánh Tráng Rong Biển. Vị cay cay ngọt ngọt mặn mặn và độ giòn giòn của rong biển', 'banhtrangrongbien')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Cơm cháy lắc khô gà truyền thống nhà làm', 12000, 5.0, 300, '09/11/2023', 800, 'Gạo rang, gà, lá chanh', 'Cơm cháy ép mỏng chiên giòn rụm, phủ đều mắm ớt, lắc chung với khô gà xé lá chanh cay cay, giòn giòn.', 'comchaykhoga')");
            execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Khô gà lá chanh nhà làm đảm bảo', 12000, 4.8, 350, '01/11/2023', 1000, 'Gà, lá chanh, ớt khô', 'Khô gà lá chanh nhà làm cay cay thơm vị chanh nồng nàn.', 'khogalachanh')");

        }
    }
}
