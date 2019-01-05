package com.example.user.movieproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.provider.BaseColumns._ID;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_DATE_TIME;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_ID;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_IMAGE;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_OVERVIEW;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_TABLE;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_TITLE;

public class DatabaseHelp extends SQLiteOpenHelper {

    public static String NAMA_DATABASE = "db_Favorit";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_DATABASE = "CREATE TABLE "+ MOVIE_TABLE +
            " ("+
            MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MOVIE_TITLE + " text not null, " +
            MOVIE_IMAGE + " text not null, " +
            MOVIE_OVERVIEW + " text not null, " +
            MOVIE_DATE_TIME+" text not null );";

    public DatabaseHelp(Context context) {
        super(context, NAMA_DATABASE, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("TAG ", CREATE_DATABASE);
        Log.d("TAG ", _ID+" "+MOVIE_TITLE+" "+MOVIE_IMAGE+" "+MOVIE_OVERVIEW+" "+MOVIE_DATE_TIME);
        sqLiteDatabase.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+MOVIE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
