package com.nhom1.DatabaseHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.nhom1.models.User;
import com.nhom1.zinzinfood.SplashActivity;

public class UserDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "user.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_USER_NAME = "USER";
    public static final String COL_USER_ID = "UserId";
    public static final String COL_USER_NAME = "UserName";
    public static final String COL_USER_TYPE = "UserType";
    public static final String COL_USER_PHONE = "PhoneNumb";
    public static final String COL_USER_EMAIL = "Email";
    public static final String COL_USER_DOB = "DOB";
    public static final String COL_USER_PASS = "Password";
    public static final String COL_USER_SEX = "SEX";

    public UserDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS "
                + TBL_USER_NAME + " (" + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_USER_NAME + " VARCHAR(50), " + COL_USER_TYPE + " VARCHAR(20), "
                + COL_USER_DOB + " VARCHAR(20), " + COL_USER_PHONE + " VARCHAR(20), "
                + COL_USER_EMAIL + " VARCHAR(50), " + COL_USER_PASS + " VARCHAR(50), " + COL_USER_SEX + " VARCHAR(10))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_USER_NAME);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_ACCOUNT_NAME);
        onCreate(sqLiteDatabase);
    }



    public Boolean checkuseremail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_USER_NAME + " WHERE " + COL_USER_EMAIL + " = ? ", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkuserphone(String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_USER_NAME + " WHERE " + COL_USER_PHONE + " = ? ", new String[]{phone});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_USER_NAME + " WHERE (" + COL_USER_EMAIL + " = ? " + "OR " + COL_USER_PHONE + " = ? )" + "AND " + COL_USER_PASS + " = ? ", new String[] {username, username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
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
        Cursor cursor = getData("SELECT * FROM " + TBL_USER_NAME);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void createSampleData(){
        if(numbOfRows() == 0){
            execSql("INSERT INTO " + TBL_USER_NAME + " VALUES(null, 'User1', 'Thành Viên', '20/10/2022', '0911111111', 'user1@gmail.com', 'user1234','Nam')");
            execSql("INSERT INTO " + TBL_USER_NAME + " VALUES(null, 'User2', 'Thành Viên', '21/10/2022', '0922222222', 'user2@gmail.com', 'user1234','Nam')");
            execSql("INSERT INTO " + TBL_USER_NAME + " VALUES(null, 'User3', 'Thành Viên Bạc', '22/10/2022', '0363333333', 'user3@gmail.com', 'user1234','Nam')");
            execSql("INSERT INTO " + TBL_USER_NAME + " VALUES(null, 'User4', 'Thành Viên Bạc', '23/10/2022', '0366666666', 'user4@gmail.com', 'user1234','Nữ')");
            execSql("INSERT INTO " + TBL_USER_NAME + " VALUES(null, 'User5', 'Thành Viên Vàng', '24/10/2022', '0955555555', 'user5@gmail.com', '1234user','Nữ')");
            execSql("INSERT INTO " + TBL_USER_NAME + " VALUES(null, 'User6', 'Thành Viên Vàng', '25/10/2022', '0366666666', 'user6@gmail.com', '1234user','Nữ')");
            execSql("INSERT INTO " + TBL_USER_NAME + " VALUES(null, 'User7', 'Thành Viên Kim Cương', '26/10/2022', '0977777777', 'user7@gmail.com', '1234user','Nữ')");
            execSql("INSERT INTO " + TBL_USER_NAME + " VALUES(null, 'Đinh Thị Thúy An', 'Thành Viên Kim Cương', '27/10/2022', '0971754062', 'dinhthithuyan@gamil.com', 'dinhthithuyan2072002','Nữ')");
        }
        //Tram
    }
}
