package com.chiragawale.folinsight.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.chiragawale.folinsight.data.InsightContract.InsightEntry;

/**
 * Created by chira on 7/18/2017.
 */

public class InsightProvider extends ContentProvider {
    public static final String LOG_TAG = InsightEntry.class.getSimpleName();
    InsightDbHelper mInsightDbHelper;

    @Override
    public boolean onCreate() {
        mInsightDbHelper = new InsightDbHelper(getContext());
        return true;
    }
    public final static int USERS = 100;
    public final static int USER_ID = 101;

    /**
     * Setting up the Uri Matcher to configure behavior according to request (Single inventory or whole list)
     */
    private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mUriMatcher.addURI(InsightContract.CONTENT_AUTHORITY, InsightContract.PATH_INVENTORIES, USERS);
        mUriMatcher.addURI(InsightContract.CONTENT_AUTHORITY, InsightContract.PATH_INVENTORIES + "/#", USER_ID);
    }



    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //Database for reading data from
        SQLiteDatabase db = mInsightDbHelper.getReadableDatabase();
        //Cursor for storing result set from database
        Cursor cursor = null;
        switch (mUriMatcher.match(uri)) {
            case USERS:
                //Executes a select (Projection) from TABLE; command
                cursor = db.query(InsightEntry.TABLE_NAME, null, selection, selectionArgs, null, null, sortOrder);
                Log.e("Cursor query method","cursor returned");
                break;
            case USER_ID:
                selection = InsightEntry.USER_ID + "=?";
                selectionArgs =  new String [] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(InsightEntry.TABLE_NAME,null,selection,selectionArgs,null,null,sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Invalid query uri");
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    //Returns error if invalid uri is sent when trying to insert
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (mUriMatcher.match(uri)) {
            case USERS:
                return insertUser(uri, values);

            default:
                throw new IllegalArgumentException("Inserstion not supported for " + uri);
        }
    }

    //The method that actually carries out the insert method
    private Uri insertUser(Uri uri, ContentValues values) {
        Log.e("Insert","get database");
        //Gets the database in a writable format
        SQLiteDatabase db = mInsightDbHelper.getWritableDatabase();
        //Executeing the insert command
        long insID = db.insert(InsightEntry.TABLE_NAME, null, values);
        if (insID == -1) {
            Log.e(LOG_TAG, "Insert failed");
            return null;
        }
        //Setting up change notification configuration to notify of changes
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri, insID);
    }

    //Handles the delete case
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
    //Getting the database in editable format
    SQLiteDatabase db = mInsightDbHelper.getWritableDatabase();
    //Executes the delete statement and returns the delete id
    int delId = db.delete(InsightEntry.TABLE_NAME,selection,selectionArgs);
    //Setting up change notification configuration to notify of changes
    getContext().getContentResolver().notifyChange(uri,null);
        return delId;
    }

    //Handles the update case
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mInsightDbHelper.getWritableDatabase();

        int updateId = db.update(InsightEntry.TABLE_NAME,values,selection,selectionArgs);
        //Setting up change notification configuration to notify of changes
        getContext().getContentResolver().notifyChange(uri,null);
        return updateId;
    }
}
