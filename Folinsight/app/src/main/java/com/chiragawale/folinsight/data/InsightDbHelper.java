package com.chiragawale.folinsight.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.chiragawale.folinsight.data.InsightContract.InsightEntry;

/**
 * Created by chira on 7/18/2017.
 */

public class InsightDbHelper extends SQLiteOpenHelper {

    /*
     Database name
      */
    private static final String DATABASE_NAME = "insight.db";
    /*
    Database version
     */
    private static final int DATABASE_VERSION = 1;

    public InsightDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creates a table if the table is not already created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE "
                + InsightEntry.TABLE_NAME + "("
                + InsightEntry._ID + " INTEGER   PRIMARY KEY AUTOINCREMENT, "
                + InsightEntry.USER_ID + " INTEGER, "
                + InsightEntry.COLUMN_TOTAL_POSTS + " INTEGER, "
                + InsightEntry.COLUMN_TOTAL_LIKES + " INTEGER, "
                + InsightEntry.COLUMN_TOTAL_COMMENTS + " INTEGER, "
                + InsightEntry.COLUMN_FAN_COUNT + " INTEGER, "
                + InsightEntry.COLUMN_MUTUAL_COUNT + " INTEGER, "
                + InsightEntry.COLUMN_FOLLOWS_COUNT + " INTEGER, "
                + InsightEntry.COLUMN_FAN_C_COUNT + " INTEGER, "
                + InsightEntry.COLUMN_FAN_L_COUNT + " INTEGER, "
                + InsightEntry.COLUMN_MUTUAL_C_COUNT + " INTEGER, "
                + InsightEntry.COLUMN_MUTUAL_L_COUNT + " INTEGER, "
                + InsightEntry.COLUMN_FOLLOWS_C_COUNT + " INTEGER, "
                + InsightEntry.COLUMN_FOLLOWS_L_COUNT + " INTEGER, "
                + InsightEntry.COLUMN_UPDATED_DATE + " TEXT); ";
        Log.e("Table:",SQL_CREATE_INVENTORY_TABLE);
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
        Log.e("DB HELPER","Created");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
