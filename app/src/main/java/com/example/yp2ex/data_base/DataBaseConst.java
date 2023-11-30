package com.example.yp2ex.data_base;

public class DataBaseConst {
    public static final String DATA_BASE_NAME = "tamagochi.db";
    public static final int DATA_BASE_VERSION = 1;
    public static final String TABLE_NAME_TAMAGOCHI = "Tamagochi";
    public static final String TAMAGOCHI_ID = "id";
    public static final String TAMAGOCHI_BORE = "bore";
    public static final String TAMAGOCHI_HAPPY = "happy";
    public static final String TAMAGOCHI_HUNGER = "hunger";
    public static final String TAMAGOCHI_TIRED = "tired";
    public static final String TAMAGOCHI_DEAD = "dead";
    public static final String TAMAGOCHI_DAYS = "days";

    public static final String CREATE_TABLE_TAMAGOCHI = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TAMAGOCHI +
            "" + " ( " + TAMAGOCHI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  +
            "" + TAMAGOCHI_BORE + " INTEGER, " + TAMAGOCHI_HAPPY + " INTEGER, " +
            "" + TAMAGOCHI_HUNGER + " INTEGER, " + TAMAGOCHI_TIRED + " INTEGER, " + TAMAGOCHI_DAYS + " INTEGER, " +
            "" + TAMAGOCHI_DEAD + " INTEGER);";

    public static final String DELETE_TABLE_TAMAGOCHI = "DROP TABLE IF EXISTS " + TABLE_NAME_TAMAGOCHI;
}
