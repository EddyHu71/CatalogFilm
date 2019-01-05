package com.example.user.movieproject;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.util.Objects;

import static com.example.user.movieproject.Template.MovieColumns.URI_CONTENT;

public class FavoritActivity extends AppCompatActivity {
    RecyclerView rv_favorit;
    ProgressBar progressFav;

    private FavoriteAdapter favoriteAdapter;
    private Cursor listfavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        progressFav = findViewById(R.id.progressFavorit);
        rv_favorit = findViewById(R.id.rv_favorite);
        rv_favorit.setLayoutManager(new LinearLayoutManager(this));
        rv_favorit.setHasFixedSize(true);

        favoriteAdapter = new FavoriteAdapter(this);
        favoriteAdapter.setListFavorite(listfavorite);
        rv_favorit.setAdapter(favoriteAdapter);

        new LoadAsync().execute();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private class LoadAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressFav.setVisibility(View.VISIBLE);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContentResolver().query(URI_CONTENT, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            progressFav.setVisibility(View.GONE);

            listfavorite = cursor;
            favoriteAdapter.setListFavorite(listfavorite);
            favoriteAdapter.notifyDataSetChanged();

            if (listfavorite.getCount() == 0)
            {
                Toast.makeText(getApplicationContext(), "Tidak ada data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
