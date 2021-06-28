package com.example.tictactoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.Attributes;

public class MyData extends SQLiteOpenHelper {
    private static String PACKAGE_NAME="com.example.tictactoe.pvp_result";
    private static String PACKAGE_NAME1="com.example.tictactoe.pvc_result";
    private static final String DATABASE_NAME = "histore";
    private static final String TABLE_NAME = "histore";
    private static final int DATABASE_VERSION = 1;
    public MyData(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLTable= "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "mode CHAR(20)," +
                "winorlose CHAR(20)" +
                ");";
        db.execSQL(SQLTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
/*public void chickTable(){ //檢查資料表狀態，若無指定資料表則新增
        Cursor cursor = getWritableDatabase().rawQuery(
                "select DISTINCT tbl_name from sqlite_master where tbl_name = '" + TableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() == 0)
                getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS " + TableName + "( " +
                        "Gametype TEXT, " +
                        "Winorlose TEXT" +
                        ");");
            cursor.close();
        }
    }
    public ArrayList<String> getTables(){ //取得有多少資料表,並以陣列回傳
        Cursor cursor = getWritableDatabase().rawQuery(
                "select DISTINCT tbl_name from sqlite_master", null);
        ArrayList<String> tables = new ArrayList<>();
        while (cursor.moveToNext()){
            String getTab = new String (cursor.getBlob(0));
            if (getTab.contains("android_metadata")){}
            else if (getTab.contains("sqlite_sequence")){}
            else tables.add(getTab.substring(0,getTab.length()-1));
        }
        return tables;
    }
    public void addData(String name, String phone, String hobby, String elseInfo) { //新增資料
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("game type",PACKAGE_NAME);
        values.put("win&lose",PACKAGE_NAME1);
    }
    public ArrayList<HashMap<String, String>> showAll() {  //顯示所有資料
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + TableName, null);
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        while (c.moveToNext()) {
            HashMap<String, String> hashMap = new HashMap<>();

            String Gametype = c.getString(0);
            String Winorlose = c.getString(1);

            hashMap.put("game type", Gametype);
            hashMap.put("win&lose", Winorlose);
        }
        return arrayList;

    }*/