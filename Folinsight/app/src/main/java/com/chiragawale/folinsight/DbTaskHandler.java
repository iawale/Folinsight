package com.chiragawale.folinsight;


import android.app.LoaderManager;
import android.content.ContentValues;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.chiragawale.folinsight.data.InsightContract;

public class DbTaskHandler extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private String [] projection = {};
    private InsightCursorAdapter mInsightCursorAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Db");
        setContentView(R.layout.db_task_handler_activity);
        mInsightCursorAdaptor = new InsightCursorAdapter(this,null);
        //Finding the list view
        ListView listView = (ListView) findViewById(R.id.list_insight);
        listView.setAdapter(mInsightCursorAdaptor);
        //Inserting data into database
        insert();
        //Kick off the loader
        getLoaderManager().initLoader(0,null,this);

    }

    public  void insert() {

            //Setting up the insert statement
            ContentValues values = new ContentValues();
            values.put(InsightContract.InsightEntry.USER_ID,2314);
            values.put(InsightContract.InsightEntry.COLUMN_TOTAL_LIKES, GlobalVar.mediaDao.getTotalLikes() );
            values.put(InsightContract.InsightEntry.COLUMN_TOTAL_COMMENTS, GlobalVar.mediaDao.getTotalComments());
            values.put(InsightContract.InsightEntry.COLUMN_TOTAL_POSTS, GlobalVar.mediaDao.getTotalPosts());
            values.put(InsightContract.InsightEntry.COLUMN_FAN_COUNT, GlobalVar.mediaDao.getFansCount());
            values.put(InsightContract.InsightEntry.COLUMN_MUTUAL_COUNT, GlobalVar.mediaDao.getMutualCount());
            values.put(InsightContract.InsightEntry.COLUMN_FOLLOWS_COUNT, GlobalVar.mediaDao.getFollowsCount());
            values.put(InsightContract.InsightEntry.COLUMN_FAN_L_COUNT,GlobalVar.mediaDao.getFanLikes());
            values.put(InsightContract.InsightEntry.COLUMN_FAN_C_COUNT, GlobalVar.mediaDao.getFanComments());
            values.put(InsightContract.InsightEntry.COLUMN_MUTUAL_L_COUNT, GlobalVar.mediaDao.getMutualLikes());
            values.put(InsightContract.InsightEntry.COLUMN_MUTUAL_C_COUNT, GlobalVar.mediaDao.getMutualComments());
            values.put(InsightContract.InsightEntry.COLUMN_FOLLOWS_L_COUNT,GlobalVar.mediaDao.getFollowsLikes());
            values.put(InsightContract.InsightEntry.COLUMN_FOLLOWS_C_COUNT, GlobalVar.mediaDao.getFollowsComments());
            values.put(InsightContract.InsightEntry.COLUMN_UPDATED_DATE, "23 Feb");
            Uri uri = getContentResolver().insert(InsightContract.InsightEntry.CONTENT_URI, values);
            if (uri != null) {
                Toast.makeText(this, "Insert successful", Toast.LENGTH_SHORT).show();
            }



    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, InsightContract.InsightEntry.CONTENT_URI,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mInsightCursorAdaptor.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mInsightCursorAdaptor.swapCursor(null);
    }
}
