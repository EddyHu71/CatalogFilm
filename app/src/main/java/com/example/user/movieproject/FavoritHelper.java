package com.example.user.movieproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_DATE_TIME;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_IMAGE;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_OVERVIEW;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_TABLE;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_TITLE;

public class FavoritHelper {

    private static String TABLE_DATABASE = MOVIE_TABLE;
    private Context context;
    private DatabaseHelp databaseHelp;

    private SQLiteDatabase sqLiteDatabase;

    public FavoritHelper(Context context) {
        this.context = context;
    }

    public FavoritHelper open() throws SQLException {
        databaseHelp = new DatabaseHelp(context);
        sqLiteDatabase = databaseHelp.getWritableDatabase();
        return this;
    }

    public ArrayList<Favorit> query() {
        ArrayList<Favorit> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_DATABASE
                ,null
                ,null
                ,null
                ,null
                ,null,
                _ID + " DESC"
                ,null);
        cursor.moveToFirst();
        Favorit favorit;
        if (cursor.getCount() > 0) {
            do {
                favorit = new Favorit();
                favorit.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                favorit.setName(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_TITLE)));
                favorit.setImage(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_IMAGE)));
                favorit.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_OVERVIEW)));
                favorit.setDate(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_DATE_TIME)));
                arrayList.add(favorit);
                cursor.moveToNext();
                Log.d("TAG ", "cursor getcount executed"+_ID+" "+MOVIE_TITLE+" "+MOVIE_IMAGE+" "+MOVIE_OVERVIEW+" "+MOVIE_DATE_TIME);
            } while (!cursor.isAfterLast());

        }
        cursor.close();
        return arrayList;
    }
    public Cursor queryByIdProvider(String id) {
        return sqLiteDatabase.query(TABLE_DATABASE, null
        , _ID + " = ?"
        , new String[]{id}
        , null
        , null
        , null
        ,null);
    }

    public Cursor queryProvider() {
        return sqLiteDatabase.query(TABLE_DATABASE
        , null
        , null
        , null
        , null
        , null
        , _ID + " DESC");
    }

    public long insertProvider(ContentValues contentValues) {
        return sqLiteDatabase.insert(TABLE_DATABASE, null, contentValues);
    }

    public int updateProvider(String id, ContentValues contentValues) {
        return sqLiteDatabase.update(TABLE_DATABASE, contentValues, _ID+" = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return sqLiteDatabase.delete(TABLE_DATABASE, _ID +" = ?", new String[]{id});
    }
}
