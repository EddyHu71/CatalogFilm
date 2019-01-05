package com.example.user.movieproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.user.movieproject.BuildConfig.MY_KEY;

public class MostPopular extends AppCompatActivity {
    RecyclerView rv_mostpopular;
    private FilmAdapter filmAdapter;
    List<Film> listfilm = new ArrayList<>();
    ProgressDialog progressDialog;
    ApiFilm apiFilm;

    private final String language = "en-US";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_popular);

        rv_mostpopular = findViewById(R.id.rv_most_popular);
        apiFilm = LinkServer.getApiFilm();
        filmAdapter = new FilmAdapter(getApplicationContext(), listfilm);
        refresh();
        rv_mostpopular.setHasFixedSize(true);
        rv_mostpopular.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_mostpopular.setAdapter(filmAdapter);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void refresh() {
    progressDialog = ProgressDialog.show(this, null, "Harap tunggu....", true, false);
    apiFilm.getPopularMovies(MY_KEY, language).enqueue(new Callback<ResponseFilm>() {
        @Override
        public void onResponse(Call<ResponseFilm> call, Response<ResponseFilm> response) {
            if (response.isSuccessful()) {
                progressDialog.dismiss();
                listfilm = response.body().getFilm();
                rv_mostpopular.setAdapter(new FilmAdapter(getApplicationContext(), listfilm));
                filmAdapter.notifyDataSetChanged();
                Log.d("TAG ", "onResponse successfully in mostpopular");

            } else {
                progressDialog.dismiss();
                Log.d("TAG ", "onResponse failed in mostpopular");
            }
        }

        @Override
        public void onFailure(Call<ResponseFilm> call, Throwable t) {
            progressDialog.dismiss();
            Log.d("TAG ", "onFailure executed in MostPopular");
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
