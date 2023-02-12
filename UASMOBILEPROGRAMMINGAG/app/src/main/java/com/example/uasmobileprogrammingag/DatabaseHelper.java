package com.example.uasmobileprogrammingag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbuas.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("PRAGMA foreign_keys=ON");
        DB.execSQL("create table penyewa (" +
                "nama text," +
                "alamat text," +
                "no_hp text," +
                "merek text," +
                "lama int," +
                "total double," +
                "primary key(nama)" +
                ");" +
                "");
        DB.execSQL("create Table Admin(username text primary key,password PASSWORD)");
        DB.execSQL("insert into Admin(username ,password) VALUES ('admin', '12345')");
        DB.execSQL("create table mobil(" +
                "merk text," +
                "harga int," +
                "primary key(merk)" +
                ");" +
                "");
        DB.execSQL("insert into mobil values (" +
                "'Avanza'," +
                "400000" +
                ");" +
                "");
        DB.execSQL("insert into mobil values (" +
                "'Xenia'," +
                "400000" +
                ");" +
                "");
        DB.execSQL("insert into mobil values (" +
                "'Ertiga'," +
                "400000" +
                ");" +
                "");
        DB.execSQL("insert into mobil values (" +
                "'APV'," +
                "400000" +
                ");" +
                "");
        DB.execSQL("insert into mobil values (" +
                "'Innova'," +
                "500000" +
                ");" +
                "");
        DB.execSQL("insert into mobil values (" +
                "'Xpander'," +
                "550000" +
                ");" +
                "");
        DB.execSQL("insert into mobil values (" +
                "'Pregio'," +
                "550000" +
                ");" +
                "");
        DB.execSQL("insert into mobil values (" +
                "'Elf'," +
                "700000" +
                ");" +
                "");
        DB.execSQL("insert into mobil values (" +
                "'Alphard'," +
                "1500000" +
                ");" +
                "");

    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserDetails");
    }
    /*public Boolean insetUserData(String name,String number,String email,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID",email);
        contentValues.put("name",name);
        contentValues.put("password",password);
        contentValues.put("number",number);
        long result= DB.insert("User",null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }*/
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Admin",null);
        return cursor;
    }
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        String selectQuery = "select * from mobil";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categories;
    }
}