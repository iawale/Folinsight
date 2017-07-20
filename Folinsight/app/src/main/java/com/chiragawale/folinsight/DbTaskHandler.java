package com.chiragawale.folinsight;


import android.app.LoaderManager;
import android.content.ContentValues;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.chiragawale.folinsight.data.InsightContract;
import com.chiragawale.folinsight.entity.Details_ig;
import com.chiragawale.folinsight.keys.GlobalVar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DbTaskHandler extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private String[] projection = {};
    private Cursor cursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Db");
        setContentView(R.layout.db_task_handler_activity);

        //Finding the list view
        ListView listView = (ListView) findViewById(R.id.list_insight);
        //listView.setAdapter(mInsightCursorAdaptor);
        //Inserting data into database
        insert();
        //Kick off the loader
        getLoaderManager().initLoader(0, null, this);


    }

    public void insert() {

        //Setting up the insert statement
        ContentValues values = new ContentValues();
        values.put(InsightContract.InsightEntry.USER_ID, GlobalVar.USER_ID);
        values.put(InsightContract.InsightEntry.COLUMN_TOTAL_LIKES, GlobalVar.mediaDao.getTotalLikes());
        values.put(InsightContract.InsightEntry.COLUMN_TOTAL_COMMENTS, GlobalVar.mediaDao.getTotalComments());
        values.put(InsightContract.InsightEntry.COLUMN_TOTAL_POSTS, GlobalVar.mediaDao.getTotalPosts());
        values.put(InsightContract.InsightEntry.COLUMN_FAN_COUNT, GlobalVar.mediaDao.getFansCount());
        values.put(InsightContract.InsightEntry.COLUMN_MUTUAL_COUNT, GlobalVar.mediaDao.getMutualCount());
        values.put(InsightContract.InsightEntry.COLUMN_FOLLOWS_COUNT, GlobalVar.mediaDao.getFollowsCount());
        values.put(InsightContract.InsightEntry.COLUMN_FAN_L_COUNT, GlobalVar.mediaDao.getFanLikes());
        values.put(InsightContract.InsightEntry.COLUMN_FAN_C_COUNT, GlobalVar.mediaDao.getFanComments());
        values.put(InsightContract.InsightEntry.COLUMN_MUTUAL_L_COUNT, GlobalVar.mediaDao.getMutualLikes());
        values.put(InsightContract.InsightEntry.COLUMN_MUTUAL_C_COUNT, GlobalVar.mediaDao.getMutualComments());
        values.put(InsightContract.InsightEntry.COLUMN_FOLLOWS_L_COUNT, GlobalVar.mediaDao.getFollowsLikes());
        values.put(InsightContract.InsightEntry.COLUMN_FOLLOWS_C_COUNT, GlobalVar.mediaDao.getFollowsComments());
        Calendar calendar = Calendar.getInstance();
        String dateString =String.valueOf(calendar.getTimeInMillis());
        values.put(InsightContract.InsightEntry.COLUMN_UPDATED_DATE, dateString);
        Uri uri = getContentResolver().insert(InsightContract.InsightEntry.CONTENT_URI, values);
        if (uri != null) {
            Toast.makeText(this, "Insert successful", Toast.LENGTH_SHORT).show();
        }
        GlobalVar.dataDao.clearLists();

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, InsightContract.InsightEntry.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        this.cursor = data;
        extractDataFromCursor();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    void extractDataFromCursor() {
        List<Details_ig> followsDataList = new ArrayList<>();
        List<Details_ig> strangerDataList = new ArrayList<>();
        List<Details_ig> followerDataList = new ArrayList<>();
        List<Details_ig> fanDataList = new ArrayList<>();
        List<Details_ig> mutualDataList = new ArrayList<>();
        List<Details_ig> postDataList = new ArrayList<>();


        if (cursor != null) {
            cursor.moveToFirst();

            while (cursor.isAfterLast() == false) {
                int total_posts = cursor.getInt(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_TOTAL_POSTS));
                int total_likes = cursor.getInt(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_TOTAL_LIKES));
                int total_comments = cursor.getInt(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_TOTAL_COMMENTS));
                int follows_l_count = cursor.getInt(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_FOLLOWS_L_COUNT));
                int follows_c_count = cursor.getInt(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_FOLLOWS_C_COUNT));
                int fan_l_count = cursor.getInt(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_FAN_L_COUNT));
                int fan_c_count = cursor.getInt(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_FAN_C_COUNT));
                int mutual_l_count = cursor.getInt(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_MUTUAL_L_COUNT));
                int mutual_c_count = cursor.getInt(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_MUTUAL_C_COUNT));
                int follower_l_count = mutual_l_count + fan_l_count;
                int follower_c_count = mutual_c_count + fan_c_count;
                int stranger_l_count = total_likes - (follower_l_count + follows_l_count);
                int stranger_c_count = total_comments - (follower_c_count + follows_c_count);
                String dateString = cursor.getString(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_UPDATED_DATE));
                Log.e("FROM DB", dateString+"+++++++++++++ASDASDSD+ASD+SD+AS+DAS+D+");
                if (total_posts == 0) {
                    total_posts++;
                }

                Details_ig data_object;
                data_object = new Details_ig(GlobalVar.POSTS_CODE,(double) total_likes/total_posts,(double) total_comments/total_posts);
                data_object.setDate(dateString);
                postDataList.add(data_object);

                data_object = new Details_ig(GlobalVar.FOLLOWER_CODE,(double) follower_l_count/total_posts,(double) follower_c_count/total_posts);
                data_object.setDate(dateString);
                followerDataList.add(data_object);

                data_object = new Details_ig(GlobalVar.FAN_CODE,(double) fan_l_count/total_posts,(double) fan_c_count/total_posts);
                data_object.setDate(dateString);
                fanDataList.add(data_object);

                data_object = new Details_ig(GlobalVar.FOLLOWS_CODE,(double) follows_l_count/total_posts,(double) follows_c_count/total_posts);
                data_object.setDate(dateString);
                followsDataList.add(data_object);

                data_object = new Details_ig(GlobalVar.MUTUAL_CODE,(double) mutual_l_count/total_posts,(double) mutual_c_count/total_posts);
                data_object.setDate(dateString);
                mutualDataList.add(data_object);

                data_object = new Details_ig(GlobalVar.STRANGER_CODE,(double) stranger_l_count/total_posts,(double) stranger_c_count/total_posts);
                data_object.setDate(dateString);
                strangerDataList.add(data_object);
                cursor.moveToNext();
            }

            cursor.close();

            GlobalVar.dataDao.setFanDataList(fanDataList);
            GlobalVar.dataDao.setFollowsDataList(followsDataList);
            GlobalVar.dataDao.setMutualDataList(mutualDataList);
            GlobalVar.dataDao.setPostDataList(postDataList);
            GlobalVar.dataDao.setFollowerDataList(followerDataList);
            GlobalVar.dataDao.setStrangerDataList(strangerDataList);

            finish();
        }
    }
}
