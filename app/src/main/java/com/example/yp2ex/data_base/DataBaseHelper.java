package com.example.yp2ex.data_base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context) {
        super(context, DataBaseConst.DATA_BASE_NAME, null, DataBaseConst.DATA_BASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DataBaseConst.CREATE_TABLE_TAMAGOCHI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DataBaseConst.DELETE_TABLE_TAMAGOCHI);
        onCreate(sqLiteDatabase);
    }
}
