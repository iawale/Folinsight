package com.chiragawale.folinsight.adapter;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.chiragawale.folinsight.R;
import com.chiragawale.folinsight.data.InsightContract;
import com.jjoe64.graphview.GraphView;

/**
 * Created by chira on 7/18/2017.
 */

public class InsightCursorAdapter extends CursorAdapter {
    public InsightCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_insight,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //Text views for changing their texts
        TextView likesTextView = (TextView) view.findViewById(R.id.total_likes_value);
        TextView commentsTextView = (TextView) view.findViewById(R.id.total_comments_value);
        TextView postsTextView = (TextView) view.findViewById(R.id.total_posts_value);




        //Extracting data from cursor
        String totalLikes = String.valueOf(cursor.getInt(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_TOTAL_LIKES)));
        String totalComments = String.valueOf(cursor.getInt(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_TOTAL_COMMENTS)));
        String totalPosts =String.valueOf( cursor.getInt(cursor.getColumnIndex(InsightContract.InsightEntry.COLUMN_TOTAL_POSTS)));


        //Sets data of text views according to data extracted from cursor
        likesTextView.setText(totalLikes);
        commentsTextView.setText(totalComments);
        postsTextView.setText(totalPosts);

    }
}
