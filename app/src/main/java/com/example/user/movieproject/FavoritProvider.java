package com.example.user.movieproject;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.user.movieproject.Template.AUTHOR;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_TABLE;
import static com.example.user.movieproject.Template.MovieColumns.URI_CONTENT;

public class FavoritProvider extends ContentProvider{
    private static final int FAVORIT = 1;
    private static final int ID_FAVORIT = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHOR, MOVIE_TABLE, FAVORIT);
        uriMatcher.addURI(AUTHOR, MOVIE_TABLE+"/#",
                ID_FAVORIT);
    }

    private FavoritHelper favoritHelper;

    @Override
    public boolean onCreate() {
        favoritHelper = new FavoritHelper(getContext());
        favoritHelper.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case FAVORIT:
                cursor = favoritHelper.queryProvider();
                break;
            case ID_FAVORIT :
                cursor = favoritHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long added;
        switch (uriMatcher.match(uri)) {
            case FAVORIT :
                added = favoritHelper.insertProvider(contentValues);
                break;
                default:
                    added = 0;
                    break;
        }
        if (added > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(URI_CONTENT + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        int delete;
        switch (uriMatcher.match(uri)) {
            case ID_FAVORIT :
                delete = favoritHelper.deleteProvider(uri.getLastPathSegment());
                break;
                default:
                    delete = 0;
                    break;
        }
        if (delete > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return delete;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        int update;
        switch (uriMatcher.match(uri)) {
            case ID_FAVORIT:
                update = favoritHelper.updateProvider(uri.getLastPathSegment(), contentValues);
                break;
            default:
                update = 0;
                break;
        }
        if (update > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return update;
    }
}
