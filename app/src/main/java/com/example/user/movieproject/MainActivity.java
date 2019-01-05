package com.example.user.movieproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.support.v4.view.MenuItemCompat;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.user.movieproject.BuildConfig.MY_KEY;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rv_main;
    private FilmAdapter filmAdapter;
    List<Film> film_main = new ArrayList<>();
    ProgressDialog progressDialog;
    ApiFilm apiFilm;

    private final String language = "en-US";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        rv_main = findViewById(R.id.rv_main);
        apiFilm = LinkServer.getApiFilm();
        filmAdapter = new FilmAdapter(getApplicationContext(), film_main);

        rv_main.setHasFixedSize(true);
        rv_main.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_main.setAdapter(filmAdapter);

        refreshing();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

        searchView.setQueryHint(getResources().getString(R.string.action_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchMovie(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    refreshing();
                }
                return false;
            }
        });
        return true;
    }

    public void searchMovie(String query) {
        apiFilm.getSearchMovie(MY_KEY, language, query).enqueue(new Callback<ResponseFilm>() {
            @Override
            public void onResponse(Call<ResponseFilm> call, Response<ResponseFilm> response) {
                if (response.isSuccessful()) {
                    film_main = response.body().getFilm();

                    rv_main.setAdapter(new FilmAdapter(getApplicationContext(), film_main));
                    filmAdapter.notifyDataSetChanged();
                    Log.d("TAG ", "onResponse searchMovie in MainActivity");
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to fetch data", Toast.LENGTH_LONG).show();
                    Log.d("TAG ", "OH NO, onResponse FAILED");
                }
            }

            @Override
            public void onFailure(Call<ResponseFilm> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to connect internet", Toast.LENGTH_LONG).show();
                Log.d("TAG ", "onFailure searchMovie in MainActivity");
            }
        });
    }

    private void refreshing() {
        progressDialog = ProgressDialog.show(this, null, "Harap tunggu....", true, false);

        apiFilm.getAllMovies(MY_KEY, language).enqueue(new Callback<ResponseFilm>() {
        @Override
        public void onResponse(Call<ResponseFilm> call, Response<ResponseFilm> response) {
            if (response.isSuccessful()) {
                progressDialog.dismiss();
                film_main = response.body().getFilm();
                rv_main.setAdapter(new FilmAdapter(getApplicationContext(), film_main));
                filmAdapter.notifyDataSetChanged();
                Log.d("TAG ", "Response is successfull");
            } else {
                progressDialog.dismiss();
                Log.d("TAG ", "Refresh failed!!!");
                Toast.makeText(getApplicationContext(), "Reponse is failed", Toast.LENGTH_LONG).show();
            }
            Log.d("TAG ", "onResponse executed in MainActivity");
            Log.d("TAG ", MY_KEY+" "+language);
        }

        @Override
        public void onFailure(Call<ResponseFilm> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Failed to connect Internet", Toast.LENGTH_LONG).show();
            Log.d("TAG ", "onFailed executed in MainActivity");
        }
    });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent setting = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(setting);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.top_rated_main) {
            Intent top = new Intent(MainActivity.this, TopRated.class);
            startActivity(top);
            Log.d("TAG ", "MainActivity top rated");
            // Handle the camera action
        } else if (id == R.id.now_playing_main) {
            Intent play = new Intent(MainActivity.this, NowPlaying.class);
            startActivity(play);
            Log.d("TAG ", "MainActivity now playing");
        } else if (id == R.id.favorite_main) {
            Intent favor = new Intent(MainActivity.this, FavoritActivity.class);
            startActivity(favor);
            Log.d("TAG ", "MainActivity favorit");
        } else if (id == R.id.up_coming_main) {
            Intent upcom = new Intent(MainActivity.this, UpComing.class);
            startActivity(upcom);
            Log.d("TAG ", "MainActivity upcoming");
        } else if (id == R.id.popular_main) {
            Intent pop = new Intent(MainActivity.this, MostPopular.class);
            startActivity(pop);
            Log.d("TAG ", "MainActivity popular");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
