package com.belemirfuat.ders2v2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {

    public static String DBNAME = "oyun.db";
    public static int version = 1;
    public dbHelper(Context cnt)
    {
        super(cnt, DBNAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create table ayarlar (ID INTEGER PRIMARY KEY AUTOINCREMENT, name STRING, value STRING)");
        db.execSQL("Create table skorlar (ID INTEGER PRIMARY KEY AUTOINCREMENT, username STRING, value INT)");
        db.execSQL("insert into ayarlar (name, value) values('isim', 'oyuncu') ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public String isimOku()
    {
        SQLiteDatabase db = getReadableDatabase();
        String sorgu = "Select id, name, value from ayarlar where name = 'isim";
        Cursor crs = db.rawQuery(sorgu, null);
        crs.moveToFirst();
        return crs.getString(2);
    }


}
