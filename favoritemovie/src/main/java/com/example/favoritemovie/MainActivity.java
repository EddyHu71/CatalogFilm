package com.example.favoritemovie;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.CursorLoader;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Objects;

import static com.example.favoritemovie.DbContract.FavoriteMovie.URI_CONTENT;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private FavoriteFilmAdapter favoriteFilmAdapter;
    ListView lv_favorit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_favorit = findViewById(R.id.rv_favorit_app);
        favoriteFilmAdapter = new FavoriteFilmAdapter(this, null, true);
        lv_favorit.setAdapter(favoriteFilmAdapter);

        Objects.requireNonNull(getSupportActionBar()).setTitle("List Favorite Film");
        getSupportLoaderManager().initLoader(110, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(110, null, this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(110);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, URI_CONTENT, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        favoriteFilmAdapter.swapCursor(data);
    }


    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        favoriteFilmAdapter.swapCursor(null);
    }
}
