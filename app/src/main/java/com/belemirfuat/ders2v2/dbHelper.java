package com.belemirfuat.ders2v2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {

    public static String DBNAME = "oyun.db";
    public static int version = 3;
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
        db.execSQL("insert into ayarlar (name, value) values('zorlukSeviyesi', '2')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d("dbHelper", "onupgrade çağrıldı");
        if(oldVersion == 2 && newVersion == 3)
        {
            db.execSQL("INSERT into ayarlar (name, value) values('zorlukSeviyesi', '2')");
        }
    }

    public String isimOku()
    {
        SQLiteDatabase db = getReadableDatabase();
        String sorgu = "Select id, name, value from ayarlar where name = 'isim'";
        Cursor crs = db.rawQuery(sorgu, null);
        crs.moveToFirst();
        return crs.getString(2);
    }

    public void isimKaydet(String oyuncuIsim)
    {
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("UPDATE ayarlar set value = '"+ oyuncuIsim +"' where name = 'isim'");
        db.rawQuery("update ayarlar set value = @deger where name = 'isim'", new String[]{oyuncuIsim});
    }

    public int zorlukSeviyesiOku()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor crs = db.rawQuery("select value from ayarlar where name = 'zorlukSeviyesi'", null);
        crs.moveToFirst();
        return crs.getInt(0);
    }
    public void zorlukSeviyesiKaydet(int seviye)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sevy = String.valueOf(seviye);
        db.execSQL("UPDATE ayarlar SET value = '"+sevy+"' where name ='zorlukSeviyesi'");
    }

    public Cursor ayarlariOku()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor crs = db.rawQuery("Select * from ayarlar", null);

        return crs;
    }


}
