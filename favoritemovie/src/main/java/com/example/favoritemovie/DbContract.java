package com.example.favoritemovie;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DbContract {
    public static final String AUTHOR = "com.example.user.movieproject";
    public static final String SCHEME = "content";

    public static final String TITLE_DETAIL_FAVORITE = "title";
    public static final String DATE_AND_TIME_DETAIL_FAVORITE = "release_date";
    public static final String OVERVIEW_DETAIL_FAVORITE = "overview";
    public static final String IMAGE_DETAIL_FAVORITE = "poster_path";

    public static final class FavoriteMovie implements BaseColumns {
        public static String MOVIE_FAVORITE = "Favorite";
        public static String TITLE_FAVORITE = "judul";
        public static String POSTER_FAVORITE = "gambar";
        public static String RELEASE_DATE_FAVORITE = "tanggal";
        public static String OVERVIEW_FAVORITE = "deskripsi";

        public static final Uri URI_CONTENT = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHOR)
                .appendPath(MOVIE_FAVORITE)
                .build();
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
}
