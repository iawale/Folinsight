package com.chiragawale.folinsight.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by chira on 7/18/2017.
 */

public class InsightContract {/**
 * To prevent someone from accidentally initializing the class
 */
private InsightContract(){}

    public static final String CONTENT_AUTHORITY = "com.chiragawale.folinsight";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_INVENTORIES = "insight";


    public static final class InsightEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_INVENTORIES);

        public static final String TABLE_NAME = "insight";

        /*
        Unique number for the user id
        Type : INTEGER
         */
        public static final String _ID = BaseColumns._ID;
        /*
        /*
        Unique number for the user id
        Type : INTEGER
         */
        public static final String USER_ID = "user_id";
        /*
        Type : INTEGER
        */
        public static final String COLUMN_TOTAL_POSTS = "total_posts";
        /*
        No of likes
        Type : INTEGER
         */
        public static final String COLUMN_TOTAL_LIKES = "total_likes";
        /*
         No of comments
         Type: INTEGER
         */
        public static final String COLUMN_TOTAL_COMMENTS = "total_comments";
        /*
         Type : INTEGER
         */
        public static final String COLUMN_FAN_COUNT = "fan_count";
        /*
         Type : INTEGER
         */
        public static final String COLUMN_MUTUAL_COUNT = "mutual_count";
        /*
         Type : INTEGER
         */
        public static final String COLUMN_FOLLOWS_COUNT = "follows_count";
        /**
         * For checking date of update
         */
        public static final String COLUMN_UPDATED_DATE = "updated_date";
        /*
        Type : INTEGER
        */
        public static final String COLUMN_FOLLOWS_L_COUNT = "follows_likes_count";
        /*
        Type : INTEGER
        */
        public static final String COLUMN_FOLLOWS_C_COUNT = "follows_comments_count";
        /*
        Type : INTEGER
        */
        public static final String COLUMN_FAN_L_COUNT = "fan_likes_count";
        /*
        Type : INTEGER
        */
        public static final String COLUMN_FAN_C_COUNT = "fan_comments_count";
        /*
        Type : INTEGER
        */
        public static final String COLUMN_MUTUAL_L_COUNT = "mutual_likes_count";
        /*
        Type : INTEGER
        */
        public static final String COLUMN_MUTUAL_C_COUNT = "mutual_comments_count";
        
    }



}

