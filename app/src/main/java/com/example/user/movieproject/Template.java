package com.example.user.movieproject;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class Template {
    public static final String TITLE_DETAIL = "title";
    public static final String DATE_AND_TIME_DETAIL = "release_date";
    public static final String OVERVIEW_DETAIL = "overview";
    public static final String IMAGE_DETAIL = "poster_path";

    public static final String AUTHOR = "com.example.user.movieproject";
    public static final String SCHEME = "content";

    private Template() {

    }

    public static final class MovieColumns implements BaseColumns {
        public static String MOVIE_TABLE = "Favorite";
        public static String MOVIE_ID = "id";
        public static String MOVIE_TITLE = "judul";
        public static String MOVIE_OVERVIEW = "deskripsi";
        public static String MOVIE_DATE_TIME = "tanggal";
        public static String MOVIE_IMAGE = "gambar";

        public static final Uri URI_CONTENT = new Uri.Builder().scheme(SCHEME)
       .authority(AUTHOR)
       .appendPath(MOVIE_TABLE)
       .build();
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
}
