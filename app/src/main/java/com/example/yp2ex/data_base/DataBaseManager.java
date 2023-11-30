package com.example.yp2ex.data_base;

import static com.example.yp2ex.data_base.DataBaseConst.TABLE_NAME_TAMAGOCHI;
import static com.example.yp2ex.data_base.DataBaseConst.TAMAGOCHI_BORE;
import static com.example.yp2ex.data_base.DataBaseConst.TAMAGOCHI_DAYS;
import static com.example.yp2ex.data_base.DataBaseConst.TAMAGOCHI_DEAD;
import static com.example.yp2ex.data_base.DataBaseConst.TAMAGOCHI_HAPPY;
import static com.example.yp2ex.data_base.DataBaseConst.TAMAGOCHI_HUNGER;
import static com.example.yp2ex.data_base.DataBaseConst.TAMAGOCHI_ID;
import static com.example.yp2ex.data_base.DataBaseConst.TAMAGOCHI_TIRED;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yp2ex.data.Tamagochi;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private Context context;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        this.context = context;
        dbHelper = new DataBaseHelper(this.context);
    }

    public void openDb() {
        db = dbHelper.getWritableDatabase();
    }

    public void closeDb() {
        db.close();
    }

    @SuppressLint("Range")
    public List<Tamagochi> getTamogochis() {
        List<Tamagochi> tamagochis = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME_TAMAGOCHI, null);
        while (cursor.moveToNext()) {
            Tamagochi tamagochi = new Tamagochi();
            tamagochi.setId(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_ID)));
            tamagochi.setBore(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_BORE)));
            tamagochi.setHunger(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_HUNGER)));
            tamagochi.setHappy(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_HAPPY)));
            tamagochi.setDays(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_DAYS)));
            tamagochi.setTired(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_TIRED)));
            tamagochi.setDead(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_DEAD)));
            tamagochis.add(tamagochi);
        }
        cursor.close();
        return tamagochis;
    }

    @SuppressLint("Range")
    public Tamagochi getTamogochi(int tamogochiId){
        Tamagochi tamagochi = new Tamagochi();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_TAMAGOCHI + " WHERE " + TAMAGOCHI_ID + " = " + "\"" + tamogochiId + "\"", null);
        if (cursor.moveToFirst()) {
            tamagochi.setId(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_ID)));
            tamagochi.setBore(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_BORE)));
            tamagochi.setHunger(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_HUNGER)));
            tamagochi.setHappy(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_HAPPY)));
            tamagochi.setTired(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_TIRED)));
            tamagochi.setDays(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_DAYS)));
            tamagochi.setDead(cursor.getInt(cursor.getColumnIndex(TAMAGOCHI_DEAD)));
        }
        cursor.close();
        return tamagochi;
    }

    public void addTamogochi(Tamagochi tamagochi){
        ContentValues cv = new ContentValues();
        cv.put(TAMAGOCHI_BORE, tamagochi.getBore());
        cv.put(TAMAGOCHI_TIRED, tamagochi.getTired());
        cv.put(TAMAGOCHI_DEAD, tamagochi.getDead());
        cv.put(TAMAGOCHI_HUNGER, tamagochi.getHunger());
        cv.put(TAMAGOCHI_HAPPY, tamagochi.getHappy());
        cv.put(TAMAGOCHI_DAYS, tamagochi.getDays());
        db.insert(TABLE_NAME_TAMAGOCHI,null, cv);
    }
}
