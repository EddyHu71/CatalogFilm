package com.example.user.movieproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.user.movieproject.BuildConfig.MY_KEY;

public class NowPlaying extends AppCompatActivity {
    RecyclerView rv_nowplaying;
    private FilmAdapter filmAdapter;
    List<Film> listFilm = new ArrayList<>();
    ApiFilm apiFilm;
    ProgressDialog progressDialog;

    private final String language = "en-US";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
    rv_nowplaying = findViewById(R.id.rv_now_playing);
    apiFilm = LinkServer.getApiFilm();
    filmAdapter = new FilmAdapter(getApplicationContext(), listFilm);

    rv_nowplaying.setHasFixedSize(true);
    rv_nowplaying.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    rv_nowplaying.setAdapter(filmAdapter);

    refresh();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void refresh() {
    progressDialog = ProgressDialog.show(this, null, "Harap tunggu....", true, false);
    apiFilm.getNowPlayingMovie(MY_KEY, language).enqueue(new Callback<ResponseFilm>() {
        @Override
        public void onResponse(Call<ResponseFilm> call, Response<ResponseFilm> response) {
            if (response.isSuccessful()) {
                progressDialog.dismiss();
                listFilm = response.body().getFilm();
                rv_nowplaying.setAdapter(new FilmAdapter(getApplicationContext(), listFilm));
                filmAdapter.notifyDataSetChanged();
                Log.d("TAG ", "onResponse successfully in nowplaying");
            } else {
                progressDialog.dismiss();
                Log.d("TAG ", "onResponse not successfully in nowplaying");
            }
        }

        @Override
        public void onFailure(Call<ResponseFilm> call, Throwable t) {
            progressDialog.dismiss();
            Log.d("TAG ", "onFailure executed in nowplaying");
        }
    });
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
